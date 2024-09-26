package com.inovisoft.backend_miatraining.logic.user.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.UserNotFoundException;
import com.inovisoft.backend_miatraining.logic.user.DTO.UserDTO;
import com.inovisoft.backend_miatraining.logic.user.DTO.UserPageResponseDTO;
import com.inovisoft.backend_miatraining.logic.user.DTO.mappers.UserDTOMapper;
import com.inovisoft.backend_miatraining.logic.user.DTO.mappers.UserPageResponseDTOMapper;
import com.inovisoft.backend_miatraining.models.UserModel;
import com.inovisoft.backend_miatraining.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    UserDTOMapper userDTOMapper;
    @Autowired
    UserPageResponseDTOMapper userPageResponseDTOMapper;

    public UserModel getUserById(Long id){
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public UserDTO getUserDTOById(Long id){
        UserModel userModel = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        return userDTOMapper.apply(userModel);
    }

    public UserDTO getUserDTOByEmail(String email){
        UserModel userModel = userRepo.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);
        return userDTOMapper.apply(userModel);
    }

    public ArrayList<UserDTO> getUsers(){
        ArrayList<UserModel> models = userRepo.findAllUsers();
        ArrayList<UserDTO> dtos = new ArrayList<>();
        models.forEach(model->{
            dtos.add(userDTOMapper.apply(model));
        });
        return dtos;
    }

    public UserPageResponseDTO getUsersByPage(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<UserDTO> dtoPage = userRepo.findAll(pageable).map(userDTOMapper);
        return userPageResponseDTOMapper.apply(dtoPage);
    }
}

