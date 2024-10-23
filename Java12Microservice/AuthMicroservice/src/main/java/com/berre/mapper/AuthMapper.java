package com.berre.mapper;

import com.berre.dto.request.RegisterRequestDto;
import com.berre.dto.request.UserProfileSaveRequestDto;
import com.berre.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    Auth RegisterRequestDtotoAuth(RegisterRequestDto dto);

//    @Mappings({
//            @Mapping(source = "id", target = "authid"), //eşleşecek alanları belirtmek için
//            @Mapping(source = "id", target = "authid")
//    })
    @Mapping(source = "id", target = "authid")
    UserProfileSaveRequestDto fromAuth(Auth auth);
}
