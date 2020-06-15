package com.tianlong.api.encrypt.service.impl;


import com.tianlong.api.encrypt.domain.KeyRequest;
import com.tianlong.api.encrypt.domain.KeyResponse;
import com.tianlong.api.encrypt.domain.RSAResponse;
import com.tianlong.api.encrypt.service.EncryptOpenService;
import com.tianlong.common.encryptutils.RSAUtils;
import org.springframework.beans.factory.annotation.Value;

public class EncryptOpenServiceImpl implements EncryptOpenService {
    @Value("${rsa.publicKey}")
    private String publicKey;
    @Value("$ {rsa.privateKey}")
    private String privateKey;
    @Value("${api.encrypt.key}")
    private String key;
    @Override
    public RSAResponse getRSA() {
        RSAResponse response = RSAResponse.options().setServerPublicKey(publicKey).build();
        return response;
    }
    @Override
    public KeyResponse getKey(KeyRequest request)throws Exception {
        String clientPublicKey = RSAUtils.privateDecrypt(request.getClientEncryptPublicKey(), RSAUtils.getPrivateKey(privateKey));
        String encryptKey = RSAUtils.publicEncrypt(key,RSAUtils.getPublicKey(clientPublicKey));
        KeyResponse response = KeyResponse.options().setKey(encryptKey) .build();
        return response;
    }
}
