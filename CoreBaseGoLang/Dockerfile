# ساده‌ترین حالت بدون stage دوم
FROM golang:1.24.3

WORKDIR /app

COPY go.mod ./
COPY go.sum ./
RUN go mod download

COPY . .

RUN go build -o core-app ./cmd

EXPOSE 8080 50051 6000

ENTRYPOINT ["./core-app"]
