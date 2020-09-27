package com.emporium.auth.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@FeignClient(name = "token-provider", url = "${external-urls.token-provider}")
public interface TokenProviderClient {

    @PostMapping("/oauth/token")
    @Headers("Content-Type: application/json")
    String getBearerToken(@RequestHeader("Authorization") String basicAuthHeader,
                          @RequestParam("grant_type") String grantType,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password);
}
