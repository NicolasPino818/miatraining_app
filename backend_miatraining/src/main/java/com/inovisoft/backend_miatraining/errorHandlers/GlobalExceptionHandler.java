package com.inovisoft.backend_miatraining.errorHandlers;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ArgumentNotValidResponse response = new ArgumentNotValidResponse(request.getRequestURI(),request.getMethod(),errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                            HttpServletRequest request) {

        String msg = "REQUEST PARAMETERS DOES NOT MATCH TYPES";
        String code = "client:parameter-types-mismatch";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParameterException(MissingServletRequestParameterException ex,
                                                                  HttpServletRequest request) {
        String msg = "REQUEST PARAMETERS ARE INVALID";
        String code = "client:missing-parameter";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                        HttpServletRequest request) {
        String msg = "REQUIRED REQUEST BODY IS MISSING";
        String code = "client:message-not-readable";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(HttpRequestMethodNotSupportedException ex,
                                                              HttpServletRequest request) {
        String msg = request.getMethod()+" METHOD IS NOT ALLOWED FOR THIS URI: "+ request.getRequestURI();
        String code = "client:method-not-supported";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(), msg, code);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ExistingUserByEmailException.class)
    public ResponseEntity<Object> handleExistingEmailException(ExistingUserByEmailException ex,
                                                               HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex,
                                                              HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(OtpUserDoesNotMatchException.class)
    public ResponseEntity<Object> handleOtpUserDoesNotMatchException(OtpUserDoesNotMatchException ex,
                                                                     HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();
        response.put("resourceName", request.getRequestURI());
        response.put("method", request.getMethod());
        response.put("time", LocalDateTime.now().toString());
        response.put("success", ex.getVerificationStatus().getSuccess().toString());
        response.put("code", ex.getCode());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(OtpExpiredVerificationException.class)
    public ResponseEntity<Object> handleOtpExpiredVerificationException(OtpExpiredVerificationException ex,
                                                                        HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("resourceName", request.getRequestURI());
        response.put("method", request.getMethod());
        response.put("time", LocalDateTime.now().toString());
        response.put("success", ex.getVerificationStatus().getSuccess().toString());
        response.put("code", ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(PasswordResetNotAllowed.class)
    public ResponseEntity<Object> handlePasswordResetNotAllowed(PasswordResetNotAllowed ex,
                                                                HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(PasswordsDoesNotMatchException.class)
    public ResponseEntity<Object> handlePasswordsDoesNotMatchException(PasswordsDoesNotMatchException ex,
                                                                       HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
                                                              HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(LoginNotAllowedException.class)
    public ResponseEntity<Object> handleLoginNotAllowedException(LoginNotAllowedException ex,
                                                                 HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex,
                                                                HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(), ex.getMessage(), "auth:bad-credentials");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,
                                                              HttpServletRequest request) {
        String code = "auth:insufficient-permissions";
        String msg = "YOU DO NOT HAVE PERMISSION TO ACCESS THIS RESOURCE";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(), msg, code);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<Object> handleInternalErrorException(InternalErrorException ex, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(),ex.getCode());
        return new ResponseEntity<>(response,ex.getStatus());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, HttpServletRequest request) {

        String code = "auth:expired-token";
        String msg = "JWT EXPIRATION DATE EXCEEDED";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, HttpServletRequest request) {

        String code = "auth:invalid-token";
        String msg = "MALFORMED JWT - EXPECTED: 'Bearer TOKEN'";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException ex, HttpServletRequest request) {

        String code = "auth:forged-token";
        String msg = "FORGERY DETECTED - TOKEN SIGNATURE DOES NOT MATCH";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<Object> handleMailAuthenticationException(MailAuthenticationException ex, HttpServletRequest request) {

        String code = "server:internal-mailing-error";
        String msg = "WE ARE EXPERIENCING PROBLEMS SENDING YOUR EMAIL";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(),ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<Object> handleNotAllowedException(NotAllowedException ex, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(),ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Object> handleFileUploadException(FileUploadException ex, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),ex.getMessage(),ex.getCode());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    //DEFAULT EXCEPTION HANDLER
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGeneralException(Exception ex, HttpServletRequest request) {
//        String code = "server:internal-error";
//        String msg = "AN UNEXPECTED ERROR OCCURRED";
//        System.out.println(ex.getMessage());
//        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, HttpServletRequest request) {
        String code = "server:internal-error";
        System.out.println(ex.getMessage());
        System.out.println(ex.getClass());
        String msg = "AN UNEXPECTED ERROR OCCURRED";
        ErrorResponse response = new ErrorResponse(request.getRequestURI(),request.getMethod(),msg,code);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
