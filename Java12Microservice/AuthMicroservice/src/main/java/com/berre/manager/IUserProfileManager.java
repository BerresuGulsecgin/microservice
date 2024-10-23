package com.berre.manager;

import com.berre.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.berre.constant.EndPoints.*;

/*
@FeignClient : bu anatasyonla bir Feing Client tanımı yapılır
name değeri eşsiz olmalı
url kısmına vereceğiniz adres istek atılacak olan sınıfın requestMaping adresi olmalıdır
decode404 kısmı çok gerekli değil hata durumunda 404 yönlendirmesi yapar
 */
@FeignClient(name = "user-profile-manager", url = "http://localhost:9091/api/v1/user", decode404 = true)
public interface IUserProfileManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);
}
