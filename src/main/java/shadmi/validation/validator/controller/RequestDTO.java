package shadmi.validation.validator.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    Integer code;
    Person person;
    Employee employee;
}
