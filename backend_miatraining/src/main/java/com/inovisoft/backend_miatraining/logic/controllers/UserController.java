package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.*;
import com.inovisoft.backend_miatraining.logic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/role")
    public ResponseEntity<ArrayList<RoleDTO>> getRoles(){
        return ResponseEntity.ok(userService.getRoles());
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserById(@Valid @PathVariable("userID") Long userID){
        return ResponseEntity.ok(userService.getUserDTOById(userID));
    }

    @GetMapping("/filter-email")
    public ResponseEntity<UserDTO> getUserByEmail(@Valid @RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserDTOByEmail(email));
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<ProfileInfoResponseDTO> getProfileInfoByEmail(
            @Valid @PathVariable("email") String email){
        return ResponseEntity.ok(userService.getProfileInfoByEmail(email));
    }

    @PutMapping("/profile/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void saveProfileInfo(@Valid @PathVariable("email") String email,
                                @RequestBody ProfileInfoUpdateDTO updateDTO){
        userService.saveProfileInfo(email,updateDTO);
    }

    @GetMapping()
    public ResponseEntity<UserPageResponseDTO> getUsers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "100") int pageSize,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "role", required = false, defaultValue = "") String role) {

        UserPageResponseDTO users = userService.getUsersByPage(pageNumber, pageSize,search, role);
        return ResponseEntity.ok(users);
    }


}
