package com.berre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "yetkicollection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Yetki extends BaseEntity {

    @MongoId
    String id;
    String userprofileid;
    String yetki;

}
