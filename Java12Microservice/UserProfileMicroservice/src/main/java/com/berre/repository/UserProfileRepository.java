package com.berre.repository;

import com.berre.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile,String> {
    Optional<UserProfile> findOptionalByAuthid(Long id);
}
