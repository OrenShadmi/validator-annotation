package shadmi.validation.validator.aop;



import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.Validator;
import java.lang.annotation.*;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = ValidatorImpl.class)
@Target( { PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorAnnotation {

    Validator validator = null;


    String message() default "default";

    Class<?>[] groups() default { };

    int value() default 0;

    Class<? extends Payload>[] payload() default { };

}


