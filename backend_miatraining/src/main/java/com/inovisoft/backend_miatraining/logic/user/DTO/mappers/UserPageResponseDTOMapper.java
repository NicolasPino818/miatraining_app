package com.inovisoft.backend_miatraining.logic.user.DTO.mappers;

import com.inovisoft.backend_miatraining.logic.user.DTO.UserDTO;
import com.inovisoft.backend_miatraining.logic.user.DTO.UserPageResponseDTO;
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
