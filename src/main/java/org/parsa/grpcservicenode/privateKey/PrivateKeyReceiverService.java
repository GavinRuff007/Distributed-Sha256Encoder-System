package org.parsa.grpcservicenode.privateKey;

import io.grpc.stub.StreamObserver;
import org.parsa.grpcservicenode.email.EmailService;
import org.parsa.grpcservicenode.util.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class PrivateKeyReceiverService extends PrivateKeyServiceGrpc.PrivateKeyServiceImplBase
        implements ApplicationContextAware {

    private ConfigurableApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (applicationContext instanceof ConfigurableApplicationContext) {
            this.context = (ConfigurableApplicationContext) applicationContext;
        }
    }

    @Override
    public void receivePrivateKey(PrivateKeyMessage request, StreamObserver<Ack> responseObserver) {
        String privateKey = request.getPrivateKey();
        System.out.println("Received PrivateKey: " + privateKey);

        try {

            String jdbcUrl = "jdbc:mysql://127.0.0.1:3307/PublicDB";
            String username = "root";
            String password = "P@ssw0rd!2023";
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            String lastKey = null;
            String sql = "SELECT aes_key FROM PublicKey ORDER BY id DESC LIMIT 1";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    lastKey = rs.getString("aes_key");
                }
            }
            conn.close();

            if (lastKey == null) {
                System.out.println("No publicKey found in PublicDB.");
            } else {
                System.out.println("Last publicKey (encrypted): " + lastKey);
                String decrypted = decryptSHA256(lastKey, privateKey);
                String decoded = URLDecoder.decode(decrypted, "UTF-8");
                if (decoded.endsWith("=")) {
                    decoded = decoded.substring(0, decoded.length() - 1);
                }

                System.out.println("Decrypted text: " + decoded);
                EmailService.sendDecryptedTextEmail(decoded, lastKey, 1745635625);
                SpringContextHolder.restartApp();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Ack ack = Ack.newBuilder().setSuccess(true).build();
        responseObserver.onNext(ack);
        responseObserver.onCompleted();
        SpringContextHolder.restartApp();

    }

    private String decryptSHA256(String encrypted, String privateKeyHex) {
        try {
            byte[] key = hexToBytes(privateKeyHex);
            return aesDecrypt(encrypted, key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "[ERROR] " + ex.getMessage();
        }
    }

    private static String aesDecrypt(String fullHex, byte[] key) throws Exception {
        int blockSize = 16;
        if (fullHex.length() < blockSize * 2) {
            throw new IllegalArgumentException("invalid ciphertext format");
        }
        String ivHex = fullHex.substring(0, blockSize * 2);
        String cipherHex = fullHex.substring(blockSize * 2);

        byte[] iv = hexToBytes(ivHex);
        byte[] ciphertext = hexToBytes(cipherHex);

        if (ciphertext.length % blockSize != 0) {
            throw new IllegalArgumentException("ciphertext is not a multiple of block size");
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] plainPadded = cipher.doFinal(ciphertext);
        int padding = plainPadded[plainPadded.length - 1] & 0xFF;
        if (padding <= 0 || padding > blockSize) {
            throw new IllegalArgumentException("invalid padding");
        }
        int plainLen = plainPadded.length - padding;
        return new String(plainPadded, 0, plainLen, java.nio.charset.StandardCharsets.UTF_8);
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] out = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            out[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return out;
    }

}
