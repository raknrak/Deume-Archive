package kr.co.chunjae.contents.error.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Builder
public class JsonValidationErrorResponseDTO {
    private final boolean jsonValidationSuccess = false;
    private final int httpStatusCode;
    private final HttpStatus httpStatusMessage;

    @JsonIgnore
    private final String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<RejectedField> rejectedFieldErrorsList;

    public static JsonValidationErrorResponseDTO of(HttpStatus httpStatus,
                                                    int httpStatusCode,
                                                    String errorMessage,
                                                    BindingResult bindingResult) {
        return JsonValidationErrorResponseDTO.builder()
                .httpStatusMessage(httpStatus)
                .httpStatusCode(httpStatusCode)
                .errorMessage(errorMessage)
                .rejectedFieldErrorsList(RejectedField.of(bindingResult))
                .build();
    }

    @Getter
    public static class RejectedField {
        private final String rejectedFieldName;
        private final Object rejectedFieldValue;
        private final String rejectedFieldMessage;

        private RejectedField(FieldError fieldError) {
            this.rejectedFieldName = fieldError.getField();
            this.rejectedFieldValue = fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue();
            this.rejectedFieldMessage = fieldError.getDefaultMessage();
        }

        public static List<RejectedField> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(RejectedField::new)
                    .collect(Collectors.toList());
        }
    }
}
