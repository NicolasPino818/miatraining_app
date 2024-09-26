package com.inovisoft.backend_miatraining.logic.user.controller;

import com.inovisoft.backend_miatraining.logic.user.DTO.UserDTO;
import com.inovisoft.backend_miatraining.logic.user.DTO.UserPageResponseDTO;
import com.inovisoft.backend_miatraining.logic.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserById(@Valid @PathVariable("userID") Long userID){
        return ResponseEntity.ok(userService.getUserDTOById(userID));
    }

    @GetMapping("/filter-email")
    public ResponseEntity<UserDTO> getUserByEmail(@Valid @RequestParam("email") String email){
        return ResponseEntity.ok(userService.getUserDTOByEmail(email));
    }

    @GetMapping()
    public UserPageResponseDTO getUsers(@RequestParam(name = "page",defaultValue = "0") int page) {
        return userService.getUsersByPage(page);
    }

}
