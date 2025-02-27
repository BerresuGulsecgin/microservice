package com.berre.repository;

import com.berre.repository.entity.Yetki;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YetkiRepository extends MongoRepository<Yetki, String> {
    List<Yetki> findAllByUserprofileid(String userprfileid);
}
