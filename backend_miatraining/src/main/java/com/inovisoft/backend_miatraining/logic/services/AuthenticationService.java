package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ExistingUserByEmailException;
import com.inovisoft.backend_miatraining.errorHandlers.exceptions.LoginNotAllowedException;
import com.inovisoft.backend_miatraining.errorHandlers.exceptions.UserNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.AuthenticationRequestDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.AuthenticationResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.TokenRefreshRequestDTO;
import com.inovisoft.backend_miatraining.models.UserModel;
import com.inovisoft.backend_miatraining.repositories.IUserRepo;
import com.inovisoft.backend_miatraining.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    IUserRepo userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequest) {

        //Verifica el usuario en la base de datos
        var user = userRepository.findByEmailIgnoreCase(authenticationRequest.getEmail())
                .orElseThrow(
                        UserNotFoundException::new);

        //Verifica que el usuario tenga permitido hacer login
        if(!user.isEnabled()) throw new LoginNotAllowedException();

        //Verifica las credenciales usando Spring Security
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword());

        //Autenticación en el sistema
        authenticationManager.authenticate(authenticationToken);
        //Respuesta con los tokens de acceso y refresco
        return generateTokens(authenticationRequest.getEmail());
    }

    //Genera nuevos tokens de acceso y refresco para mantener activa la sesión del usuario
    public AuthenticationResponseDTO refreshToken(TokenRefreshRequestDTO tokenRefreshRequestDTO){
        String token = tokenRefreshRequestDTO.getRefresh_token();
        String email = JwtService.extractUsername(token);
        var user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(UserNotFoundException::new);
        return generateTokens(user.getUsername());
    }

    //Genera ambos token de acceso y refresco
    private AuthenticationResponseDTO generateTokens(String email){
        var user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(UserNotFoundException::new);

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorization",user.getRole().getRoleName());
        claims.put("firstLogin", user.getFirstLogin());
        claims.put("fullName", user.getName().toUpperCase()+ " " + user.getSurname().toUpperCase());
        claims.put("enabled", user.getEnabled());
        var jwtToken = jwtService.generateToken(claims,user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        return  AuthenticationResponseDTO
                .builder()
                .access_token(jwtToken)
                .refresh_token(jwtRefreshToken)
                .build();
    }

    public void setFirstLoginFalse(String email){
        UserModel userModel = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(UserNotFoundException::new);
        userModel.setFirstLogin(false);
        userRepository.save(userModel);
    }

    //Verifica que los campos unique en la base de datos se respeten
    public void findExistingUser(String email){
        var userOptional = userRepository.findByEmailIgnoreCase(email);
        if(userOptional.isPresent()) {
            throw new ExistingUserByEmailException();
        }
    }
}
