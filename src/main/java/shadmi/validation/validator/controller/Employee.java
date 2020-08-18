package shadmi.validation.validator.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Size(min = 7 , max = 7)
    String id;
    @Max(value = 3)
    int age;

}
