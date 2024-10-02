package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.UserDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.UserPageResponseDTO;
import com.inovisoft.backend_miatraining.logic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserPageResponseDTO> getUsers(@RequestParam(name = "page",defaultValue = "0") int page) {
        return ResponseEntity.ok(userService.getUsersByPage(page));
    }

}
