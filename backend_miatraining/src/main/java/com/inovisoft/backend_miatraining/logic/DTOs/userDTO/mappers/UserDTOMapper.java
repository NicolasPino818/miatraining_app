package com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.UserDTO;
import com.inovisoft.backend_miatraining.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<UserModel, UserDTO> {
    @Override
    public UserDTO apply(UserModel userModel) {
        return UserDTO
                .builder()
                .id(userModel.getUserID())
                .fullName(userModel.getName().toUpperCase() + " " + userModel.getSurname().toUpperCase())
                .email(userModel.getEmail())
                .enabled(userModel.getEnabled())
                .firstLogin(userModel.getFirstLogin())
                .registrationDate(userModel.getRegistrationDate())
                .role(userModel.getRole().getRoleName())
                .build();
    }
}
