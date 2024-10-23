package com.berre.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody //geri dönüşü json olsun diye
//    public ResponseEntity<ErrorMesaj> handleRuntimeException(RuntimeException exception){
//        return ResponseEntity.ok(ErrorMesaj.builder()
//                        .code(9999)
//                        .mesaj("Beklenmeyen runtime hatası  "+exception.getMessage())
//                .build());
//    }
    @ExceptionHandler(UserProfileServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMesaj> handleDemoException(UserProfileServiceException exception){
        return ResponseEntity
                .status(exception.getErrorType().getHttpStatus())
                .body(ErrorMesaj.builder()
                        .code(exception.getErrorType().getCode())
                        .mesaj(exception.getErrorType().getMesaj())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMesaj> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorType errorType = ErrorType.PARAMETER_NOT_VALID;
        List<String> fields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e-> fields.add(e.getField()+": " + e.getDefaultMessage()));
        ErrorMesaj errorMessage=createError(errorType,ex);
        errorMessage.setFields(fields);
        return  new ResponseEntity<>(errorMessage,errorType.getHttpStatus());
    }

    private ErrorMesaj createError(ErrorType errorType, Exception e){
        return ErrorMesaj.builder()
                .code(errorType.getCode())
                .mesaj(errorType.getMesaj())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorMesaj> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.ok(ErrorMesaj.builder()
                .code(6666)
                .mesaj("Değerler uyuşmuyor  "+exception.getMessage())
                .build());
    }

}
