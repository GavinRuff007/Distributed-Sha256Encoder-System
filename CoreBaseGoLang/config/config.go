package config

import (
	"gopkg.in/yaml.v3"
	"os"
)

type Config struct {
	Nodes map[string]string `yaml:"nodes"`
}

func LoadConfig(path string) (*Config, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}

	var cfg Config
	err = yaml.Unmarshal(data, &cfg)
	if err != nil {
		return nil, err
	}

	return &cfg, nil
}
