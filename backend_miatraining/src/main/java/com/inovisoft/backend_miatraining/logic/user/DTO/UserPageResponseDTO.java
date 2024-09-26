package com.inovisoft.backend_miatraining.logic.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResponseDTO {
    private List<UserDTO> users;
    private int pageNumber;
    private int pageSize;
}
