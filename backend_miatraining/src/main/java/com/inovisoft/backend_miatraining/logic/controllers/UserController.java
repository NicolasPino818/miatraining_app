package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.AuthenticationResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.*;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.UserDetailsFormDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.UserDetailsFormSubmissionDTO;
import com.inovisoft.backend_miatraining.logic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/user-details-options")
    public ResponseEntity<UserDetailsFormDTO> getUserDetailsOptions(){
        return ResponseEntity.ok(userService.getUserDetailsOptions());
    }

    @PostMapping(value = "/{email}/user-details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthenticationResponseDTO> createUserDetails(@PathVariable("email") String email,
                                                                       @RequestParam("age") Integer age,
                                                                       @RequestParam("weight") Integer weight,
                                                                       @RequestParam("height") Integer height,
                                                                       @RequestParam("gender") Boolean gender,
                                                                       @RequestParam("dietID") Long dietID,
                                                                       @RequestParam("trainingTypeID") Long trainingTypeID,
                                                                       @RequestParam("trainingExperienceID") Long trainingExperienceID,
                                                                       @RequestParam("objectiveID") Long objectiveID,
                                                                       @RequestParam("bodyTypeID") Long bodyTypeID,
                                                                       @RequestParam("frontalPhoto") MultipartFile frontalPhoto,
                                                                       @RequestParam("sidePhoto") MultipartFile sidePhoto,
                                                                       @RequestParam("backPhoto") MultipartFile backPhoto) throws IOException {

        UserDetailsFormSubmissionDTO dto = UserDetailsFormSubmissionDTO
                .builder()
                .bodyTypeID(bodyTypeID)
                .objectiveID(objectiveID)
                .dietID(dietID)
                .experienceID(trainingExperienceID)
                .trainingTypeID(trainingTypeID)
                .gender(gender)
                .age(age)
                .weight(weight)
                .height(height)
                .frontalPhoto(frontalPhoto)
                .sidePhoto(sidePhoto)
                .backPhoto(backPhoto)
                .build();
        System.out.println("Despues de DTO");
        return ResponseEntity.ok(userService.createUserDetails(email, dto));
    }
}
