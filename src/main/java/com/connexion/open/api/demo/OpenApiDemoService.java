package com.connexion.open.api.demo;

import cn.hutool.http.HttpRequest;
import com.connexion.open.api.demo.constans.HeaderConstants;
import com.connexion.open.api.demo.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * OpenApi request service example
 * @author connexion
 */
@Slf4j
@Service
public class OpenApiDemoService {

    //Select different domains according to different environments, here is online domain
    private static final String OPEN_API_DOMAIN = "https://openapi.connexion.games";

    /**
     * Get method
     */
    public void requestGetOpenApi(){
        //Interface required
        String url = OPEN_API_DOMAIN.concat("/global/topList/roleLevel");
        Map<String, Object> req = new HashMap<>();
        //Request parameters
        req.put("topNum", "5");
        //Timestamp is required (here is an example). The actual parameter is unix timestamp in seconds
        req.put("timestamp", 1675998834);

        //API_KEY、SECRET
        Map<String, String> headers = new HashMap<>();
        headers.put(HeaderConstants.API_KEY, "fa61655a1aca4804b5e2c3c7a10c6257");
        String signResult = SignUtils.sign((HashMap<String, Object>) req, "a05315753c2842598ee5daca4f7ef399");
        headers.put(HeaderConstants.SIGNATURE_KEY, signResult);
        log.info(signResult);
        //Output result  40883fa6390c7060a874f2a310144af92586e7fe60ea9ae7adea4d4908146f94
        //Request content
        String res = HttpRequest.get(url).form(req).addHeaders(headers).execute().body();
        log.info(res);
    }
}
