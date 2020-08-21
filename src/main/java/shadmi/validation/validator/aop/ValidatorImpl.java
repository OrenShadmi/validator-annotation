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
    Map<Integer, Predicate<RequestDTO>> map;



    @Override
    public void initialize(ValidatorAnnotation validatorAnnotation) {
        this.map = new HashMap<>();
        map.put(PERSON_CODE, getPersonPredicate());
        map.put(EMPLOYEE_CODE, getEmployeePredicate());
    }


    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (requestDTO.getCode() == null) return false;
        if (!map.containsKey(requestDTO.getCode())) return false;
        if (requestDTO.getPerson() == null && requestDTO.getEmployee() == null) return false;

        return this.map.get(requestDTO.getCode()).test(requestDTO);


    }


    private Predicate<RequestDTO> getEmployeePredicate() {
        return (dto) -> {
            if(dto.getPerson() != null  && dto.getEmployee() == null && dto.getCode() != EMPLOYEE_CODE ) return false;
            Set<ConstraintViolation<Employee>> violations = validator.validate(dto.getEmployee());
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            return true;
        };
    }

    private Predicate<RequestDTO> getPersonPredicate() {
        return (dto) -> {
            if(dto.getPerson() == null && dto.getEmployee() != null && dto.getCode() != PERSON_CODE) return false;
            Set<ConstraintViolation<Person>> violations = validator.validate(dto.getPerson());
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        return true;
        };
    }
}
