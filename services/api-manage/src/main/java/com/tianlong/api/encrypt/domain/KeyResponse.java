package com.tianlong.api.encrypt.domain;

/**
 * 私钥输出参数
 */
public class KeyResponse {
    /**
     * 整个系统所有加密算法共用的密钥
     */
    private String key;

    public static class Builder {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public KeyResponse build() {
            return new KeyResponse(this);
        }
    }

    public static Builder options() {
        return new Builder();
    }

    private KeyResponse(Builder builder) {
        this.key = builder.key;
    }

    public String getKey() {
        return key;
    }
}