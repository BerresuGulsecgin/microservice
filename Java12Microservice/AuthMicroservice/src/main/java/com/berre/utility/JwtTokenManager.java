package com.berre.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${authserviceconfig.secrets.secret-key}")
    String secretKey;
    @Value("${authserviceconfig.secrets.issuer}")
    String issuer;
    Long expTime=1000L*15*60; //15 dk lık süre

    //1. token generate et - üret
    //Claim objesi içine yazacağınız bilgiler herkes tarafından görülür
    //O yüzden email ve password gibi bilgiler burada olmamalı
    public Optional<String> createToken(Long id){
        try {

            return Optional.of(JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withClaim("service", "AuthMicroservice")
                    .withClaim("ders", "Java JWT")
                    .withClaim("grup", "Java 12")
                    .withIssuer(issuer)  //jwt token oluşturan
                    .withIssuedAt(new Date(System.currentTimeMillis())) //jwt token oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis() + expTime))  //token süresinin dolma zamanı
                    .sign(Algorithm.HMAC512(secretKey))) ;
        }catch (Exception e){
            return Optional.empty();
        }

    }

    //2. token verify et - doğrula
    public Boolean verifyToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null){
                return false;
            }

        }catch (Exception e){
            return false;
        }

        return true;
    }

    //3. tokendecode et - bilgi çıkarımı yap
    public Optional<Long> decodeToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            //Eğere decoded jwt null gelirse
            if (decodedJWT==null){
                return Optional.empty();
            }
            //eğer decoded jwt dolu ise içinden çıkarım yapabiliriz
            Long id = decodedJWT.getClaim("id").asLong(); //claim içindeki valuenin tipini as ile yazıyoruz
            String service = decodedJWT.getClaim("service").asString();
            System.out.println("tokenin oluşturduğu service : "+service);
            return Optional.of(id);

        }catch (Exception e){
            return Optional.empty();
        }
    }
}
