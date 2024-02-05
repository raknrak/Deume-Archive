package kr.co.chunjae.contents.error.handler;

import kr.co.chunjae.aws.controller.PresignedUrlController;
import kr.co.chunjae.contents.controller.ContentsController;
import kr.co.chunjae.contents.error.dto.JsonValidationErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(assignableTypes = {ContentsController.class, PresignedUrlController.class})
public class ContentsErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(JsonValidationErrorResponseDTO.of(HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        ex.getBindingResult()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("jsonValidationSuccess", false);
        resultMap.put("httpStatusCode", HttpStatus.BAD_REQUEST.value());
        resultMap.put("httpStatusMessage", HttpStatus.BAD_REQUEST);
        resultMap.put("errorMessage", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(resultMap);
    }
}
