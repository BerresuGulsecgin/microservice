package com.berre.repository;

import com.berre.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    //email daha önce kulllanılmış mı
    Boolean existsByEmail(String email);

    //email ve şifre vt de kayıtlı ise o bilgiyi döndür
    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);


}
