package com.kbstar.enc;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest
class EncTests2 {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("mykey");


        String phone = "01099999999";
        String addr = "서울시 성동구 성수동";

        String encPhone = pbeEnc.encrypt(phone);
        log.info("-------------------------Enc Phone:" + encPhone);

        String encAddr = pbeEnc.encrypt(addr);
        log.info("-------------------------Enc Addr:" + encAddr);

        phone = pbeEnc.decrypt(encPhone);
        addr = pbeEnc.encrypt(encAddr);

        log.info("-------------------------Phone:" + phone);
        log.info("-------------------------Addr:" + addr);


    }

}