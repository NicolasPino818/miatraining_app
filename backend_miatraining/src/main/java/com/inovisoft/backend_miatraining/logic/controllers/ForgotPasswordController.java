package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.*;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.OtpVerificationStatusResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.PasswordResetRequestDTO;
import com.inovisoft.backend_miatraining.mailing.DTO.MailBody;
import com.inovisoft.backend_miatraining.mailing.services.EmailService;
import com.inovisoft.backend_miatraining.models.ForgotPasswordModel;
import com.inovisoft.backend_miatraining.models.UserModel;
import com.inovisoft.backend_miatraining.repositories.IForgotPasswordRepo;
import com.inovisoft.backend_miatraining.repositories.IUserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auth/forgot-password")
public class ForgotPasswordController {
    @Autowired
    IUserRepo userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    IForgotPasswordRepo forgotPasswordRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * STEP 1: FIRST THE USER NEEDS TO SEND HIS EMAIL AND VERIFY
     * THAT HE HAS ACCESS TO THAT EMAIL BY GETTING THE ONE-TIME-PASSWORD (OTP)
     * */
    @PostMapping("/verify/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OtpVerificationStatusResponseDTO> forgotPassword(@Valid @PathVariable("email") String email){

        //FINDS THE USER BY EMAIL OR RETURNS A 404 RESPONSE
        UserModel user = userRepository.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);

        if(!user.isEnabled()) throw new PasswordResetNotAllowed();

        Integer otp = this.otpGenerator();
        MailBody mailBody = MailBody.builder().to(email)
                .text("Código de verificación de correo electrónico: " + otp)
                .subject("MiaTraining: Solicitud de cambio de contraseña")
                .build();

        ForgotPasswordModel fp = ForgotPasswordModel
                .builder()
                .otp(otp)
                .exp(new Date(System.currentTimeMillis() + 1000*60*10 ))//EXP TIME SET TO 10 MINUTES
                .user(user)
                .build();
        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.deleteAll();
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok(new OtpVerificationStatusResponseDTO(true));
    }

    @PostMapping("/verify/{email}/{otp}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OtpVerificationStatusResponseDTO> verifyOTP(@Valid @PathVariable("otp") Integer otp,
                                                                      @Valid @PathVariable("email") String email,
                                                                      @Valid @RequestBody PasswordResetRequestDTO passwordResetRequest){
        //FINDS THE USER BY EMAIL OR RETURNS A 404 RESPONSE
        UserModel user = userRepository.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);

        ForgotPasswordModel fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(OtpUserDoesNotMatchException::new);

        if(fp.getExp().before(Date.from(Instant.now()))){ //IF THE EXPIRATION DATE IS BEFORE "NOW"
            forgotPasswordRepository.deleteById(fp.getFpID()); //DELETE FROM DE DB
            throw new OtpExpiredVerificationException();
        }

        forgotPasswordRepository.deleteById(fp.getFpID()); //DELETE FROM DE DB
        if(!Objects.equals(
                passwordResetRequest.getPassword(),
                passwordResetRequest.getRepeat_password())){
            throw new PasswordsDoesNotMatchException();
        }

        String encodedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());
        userRepository.updatePasswordByEmail(email, encodedPassword);

        return ResponseEntity.ok(new OtpVerificationStatusResponseDTO(true));
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(267_893, 972_850);
    }
}
