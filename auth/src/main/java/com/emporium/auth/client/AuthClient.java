package com.emporium.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@FeignClient(name = "auth", url = "http://localhost:9998")
public interface AuthClient {

    @PostMapping("/oauth/token")
    String getBearerToken(@RequestParam("grant_type") String grantType,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password);
}
