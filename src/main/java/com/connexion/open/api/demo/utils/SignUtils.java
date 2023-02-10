package com.connexion.open.api.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * sign utils
 * @author connextion
 */
@Slf4j
public class SignUtils {

    private static final String ALGORITHM = "HmacSHA256";

    /**
     * 校验签名，
     * 1、所有参数按照key的字母升序排序
     * 2、对所有字段进行key=value&key1=value1拼接
     * 5、最后对生成的字符串进行HmacSHA256签名
     *
     * @param params
     * @param secret
     * @param signature
     * @return
     */
    public static boolean valid(HashMap<String, Object> params, String secret, String signature) {
        return signature != null && signature.equals(sign(params, secret));
    }

    /**
     * 签名方法
     * @param params
     * @param secret
     * @return
     */
    public static String sign(HashMap<String, Object> params, String secret) {
        String message = joinParams(params);
        try {
            Mac hmac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            hmac.init(secret_key);
            byte[] bytes = hmac.doFinal(message.getBytes());
            return byteArrayToHexString(bytes);
        } catch (Exception ex) {
            log.error("sign error：", ex);
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder hs = new StringBuilder();
        String tempStr;
        for (int index = 0; bytes != null && index < bytes.length; index++) {
            tempStr = Integer.toHexString(bytes[index] & 0XFF);
            if (tempStr.length() == 1){
                hs.append('0');
            }
            hs.append(tempStr);
        }
        return hs.toString().toLowerCase();
    }

    /**
     *
     * @param params
     * @return
     */
    public static String joinParams(HashMap<String, Object> params) {
        Map<String, Object> sortedParams = new TreeMap<>(params);
        ArrayList<String> p = new ArrayList<>();
        for (Map.Entry<String, Object> param : sortedParams.entrySet()) {
            p.add(param.getKey() + "=" + param.getValue());
        }
        return StringUtils.join(p, "&");
    }
}
