package com.tienpv.petcare.application.exception;

import com.tienpv.petcare.application.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponseDTO = new ApiResponse();
        apiResponseDTO.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponseDTO.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponseDTO);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponseDTO = new ApiResponse();
        apiResponseDTO.setCode(errorCode.getCode());
        apiResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponseDTO);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumkey = exception.getBindingResult().getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumkey);
        ApiResponse apiResponseDTO = new ApiResponse();
        apiResponseDTO.setCode(errorCode.getCode());
        apiResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponseDTO);
    }
}
