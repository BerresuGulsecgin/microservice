package com.berre.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Mutlaka tüm modellere serileştirilmeldir.
//Ayrıca paket ismi dahil bu sınıfın aynısı iletilen serviste de olmalıdır

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
