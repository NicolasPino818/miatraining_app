package com.inovisoft.backend_miatraining.logic.user.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.UserNotFoundException;
import com.inovisoft.backend_miatraining.models.UserModel;
import com.inovisoft.backend_miatraining.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    public UserModel getUserById(Long id){
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

}

