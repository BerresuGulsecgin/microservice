package com.berre.mapper;

import com.berre.dto.request.UserProfileSaveRequestDto;
import com.berre.dto.response.UserProfileResponseDto;
import com.berre.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE= Mappers.getMapper(IUserProfileMapper.class);

    UserProfile dtoToUserProfile(UserProfileSaveRequestDto dto);

    UserProfileResponseDto fromUserProfile(UserProfile userProfile);


}
