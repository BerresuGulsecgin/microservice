package com.berre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {


    /*

    NotNull : Null olamaz
    NotBlank: Null olamaz + boş kalamaz. en az bir karakter olmalı(boşluk bile olabilir)
    NotBlank: Null olamaz + boş olamaz + sadece boşluk kabul etmez



    Regex kısası "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s])(?!.*\\s).{8,32}$"
    En az bir özel karakter (\w alfa sayısal karakterler, \d rakamlar, \s boşluk karakterleri dışında) içermesi gerektiğini belirtir.
     */
    @NotBlank(message = "kullanıcı adı boş bırakılamaz")
    @Size(min = 3, max = 20,message = "username 3-20 arası olmalıdır")
    String username;
    @Email
    String email;
    @NotBlank(message = "şifre alanı boş geçilemez")
    @Size(min = 8,max = 32,message = "şifre 8-32 arası olmalıdır")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!#.,^():;+=]).{8,32}$"
            ,message = "En az 8 karakter olsun, 1 rakam, 1 Büyük karakter, 1 küçük karakter, 1 özel karakter barındırmalıdır.")
    String password;
    String repassword;
}
