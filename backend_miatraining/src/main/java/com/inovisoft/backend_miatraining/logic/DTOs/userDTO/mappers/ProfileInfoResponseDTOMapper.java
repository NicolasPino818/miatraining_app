package com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.ProfileInfoResponseDTO;
import com.inovisoft.backend_miatraining.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileInfoResponseDTOMapper implements Function<UserModel, ProfileInfoResponseDTO> {
    @Override
    public ProfileInfoResponseDTO apply(UserModel userModel) {
        return ProfileInfoResponseDTO
                .builder()
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .fullName(userModel.getName() + " " +userModel.getSurname())
                .email(userModel.getEmail())
                .RegistrationDate(userModel.getRegistrationDate())
                .pictureUrlString(userModel.getPictureUrlString())
                .build();
    }
}
