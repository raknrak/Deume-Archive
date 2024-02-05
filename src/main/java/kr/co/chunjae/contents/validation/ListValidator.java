package kr.co.chunjae.contents.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListValidator implements ConstraintValidator<ValidList, List> {
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if (list == null || list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
