package com.berre.controller;

import static com.berre.constant.EndPoints.*;

import com.berre.dto.request.GetProfileFromTokenRequestDto;
import com.berre.dto.request.UserProfileSaveRequestDto;
import com.berre.dto.request.UserProfileUpdateRequestDto;
import com.berre.dto.response.UserProfileResponseDto;
import com.berre.repository.entity.UserProfile;
import com.berre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){
        return ResponseEntity.ok(userProfileService.saveDto(dto));
    }

    @PostMapping(GETFROMTOKEN)
    public ResponseEntity<UserProfileResponseDto> getProfileFromToken(@RequestBody GetProfileFromTokenRequestDto dto){
        return ResponseEntity.ok(userProfileService.getProfileFromToken(dto));
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("/getuppercase")
    public ResponseEntity<String> getUpperName(String name){
        return ResponseEntity.ok(userProfileService.getUpper(name));
    }

    @GetMapping("/clearcache")
    public ResponseEntity<Void> clearCache(){
        userProfileService.clearCache();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{name}")
    public ResponseEntity<Void> removeName(@PathVariable String name) {
        userProfileService.removeName(name);
        return ResponseEntity.ok().build();
    }
}
