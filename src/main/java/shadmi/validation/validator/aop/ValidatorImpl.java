package shadmi.validation.validator.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shadmi.validation.validator.controller.Employee;
import shadmi.validation.validator.controller.Person;
import shadmi.validation.validator.controller.RequestDTO;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;


@RequiredArgsConstructor
@Component
public class ValidatorImpl implements ConstraintValidator<ValidatorAnnotation, RequestDTO> {

    public static final int PERSON_CODE = 10;
    public static final int EMPLOYEE_CODE = 11;
    private final Validator validator;
    Map<Integer, Predicate<Object>> map;



    @Override
    public void initialize(ValidatorAnnotation validatorAnnotation) {
        this.map = new HashMap<>();
        map.put(PERSON_CODE, getPersonPredicate());
        map.put(EMPLOYEE_CODE, getEmployeePredicate());
    }


    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if ( requestDTO.getCode() != null ) {
            if (map.containsKey(requestDTO.getCode())) {
                if (requestDTO.getPerson() != null && requestDTO.getCode() == PERSON_CODE && requestDTO.getEmployee() == null) {
                    return map.get(requestDTO.getCode()).test(requestDTO.getPerson());
                } else if (requestDTO.getEmployee() != null && requestDTO.getCode() == EMPLOYEE_CODE && requestDTO.getPerson() == null) {
                    return map.get(requestDTO.getCode()).test(requestDTO.getEmployee());
                } else {
                    // Both Objects are null
                    return false;
                }
            } else{
                // No Such a code available
                return false;
            }
        } else {
            // Code is null
            return false;
        }
    }


    private Predicate<Object> getEmployeePredicate() {
        return (emp) -> {
            Set<ConstraintViolation<Employee>> violations = validator.validate((Employee)emp);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            return true;
        };
    }

    private Predicate<Object> getPersonPredicate() {
        return (person) -> {
            Set<ConstraintViolation<Person>> violations = validator.validate((Person)person);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        return true;
        };
    }
}
