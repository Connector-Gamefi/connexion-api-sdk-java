package com.connexion.open.api.demo;

import cn.hutool.http.HttpRequest;
import com.connexion.open.api.demo.constans.HeaderConstants;
import com.connexion.open.api.demo.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * OpenApi 请求service示例
 * @author connextion
 */
@Slf4j
@Service
public class OpenApiDemoService {

    //根据不同环境选择不同domain，此处为线上domain
    private static final String OPEN_API_DOMAIN = "https://openapi.connexion.games";

    /**
     * Get 方式请求
     */
    public void requestGetOpenApi(){
        //需要请求的接口
        String url = OPEN_API_DOMAIN.concat("/global/topList/roleLevel");
        Map<String, Object> req = new HashMap<>();
        //请求参数
        req.put("topNum", "5");
        //时间戳必填(此处是一个示例)实际参数为unix timestamp 秒级
        req.put("timestamp", 1675998834);

        //API_KEY、SECRET实际值请联系客服
        Map<String, String> headers = new HashMap<>();
        headers.put(HeaderConstants.API_KEY, "fa61655a1aca4804b5e2c3c7a10c6257");
        String signResult = SignUtils.sign((HashMap<String, Object>) req, "a05315753c2842598ee5daca4f7ef399");
        headers.put(HeaderConstants.SIGNATURE_KEY, signResult);
        log.info(signResult);
        //输出结果  40883fa6390c7060a874f2a310144af92586e7fe60ea9ae7adea4d4908146f94
        //请求内容
        String res = HttpRequest.get(url).form(req).addHeaders(headers).execute().body();
        log.info(res);
    }
}
