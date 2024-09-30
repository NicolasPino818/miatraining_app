package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.AuthenticationRequestDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.AuthenticationResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.TokenRefreshRequestDTO;
import com.inovisoft.backend_miatraining.logic.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    /**
     * VERIFIES THE USERNAME AND PASSWORD OF THE USER AND RETURNS THE ACCESS TOKEN AND REFRESH TOKEN
     * */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO authenticationDTO){
        return ResponseEntity.ok(authenticationService.login(authenticationDTO));
    }

    /**
     * GETS THE REFRESH TOKEN AND SENDS A NEW SET OF ACCESS TOKEN AND REFRESH TOKEN.
     * <br>
     * ORIGINAL REFRESH TOKEN MUST BE SENT IN BOTH AUTHORIZATION HEADER AS BEARER TOKEN AND REQUEST BODY
     * */
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponseDTO> refreshToken(@Valid @RequestBody TokenRefreshRequestDTO request){
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
