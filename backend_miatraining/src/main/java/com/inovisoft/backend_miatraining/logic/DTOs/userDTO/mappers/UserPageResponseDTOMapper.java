package com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.UserDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.UserPageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserPageResponseDTOMapper implements Function<Page<UserDTO>, UserPageResponseDTO> {
    @Override
    public UserPageResponseDTO apply(Page<UserDTO> userDTOS) {
        return UserPageResponseDTO
                .builder()
                .pageNumber(userDTOS.getNumber())
                .pageSize(userDTOS.getSize())
                .users(userDTOS.toList())
                .build();
    }
}
