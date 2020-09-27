package com.emporium.auth.client;

import com.emporium.lib.auth.UserBasicDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@FeignClient(name = "personal-area", url = "${external-urls.personal-area}")
public interface PersonalAreaClient {

    @PostMapping("/personal-area/register")
    String register(@RequestHeader("Authorization") String bearerToken, @RequestBody UserBasicDTO dto);
}
