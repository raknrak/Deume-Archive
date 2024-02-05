package kr.co.chunjae.contents.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ListValidator.class}
)
public @interface ValidList {

    String message() default "List can not be null or empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
