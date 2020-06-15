package com.tianlong.api.encrypt.service;


import com.tianlong.api.encrypt.domain.KeyRequest;
import com.tianlong.api.encrypt.domain.KeyResponse;
import com.tianlong.api.encrypt.domain.RSAResponse;

public interface EncryptOpenService {
    /**
     * 生成RSA公私钥
     *
     * @return
     */
    RSAResponse getRSA();

    /**
     * 获得加解密用的密钥
     *
     * @param request
     * @return
     */
    KeyResponse getKey(KeyRequest request) throws Exception;
}
