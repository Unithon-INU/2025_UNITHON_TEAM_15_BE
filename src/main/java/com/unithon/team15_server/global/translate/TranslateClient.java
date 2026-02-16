package com.unithon.team15_server.global.translate;

import com.unithon.team15_server.global.translate.dto.TranslateReq;
import com.unithon.team15_server.global.translate.dto.TranslateRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TranslateClient", url = "https://api-free.deepl.com")
public interface TranslateClient {

    @PostMapping(value = "/v2/translate", consumes = "application/json")
    TranslateRes translate(@RequestHeader("Authorization") String key, @RequestBody TranslateReq translateReq);
}
