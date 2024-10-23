package com.berre.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserProfile extends BaseEntity {

    @MongoId
    String id; //document nosql db lerde id uuid tarafından oluşturulur.Bu da stringe karşılık gelir
    Long authid;
    String username;
    String email;
    String photo;
    String phone;
    String website;
}
