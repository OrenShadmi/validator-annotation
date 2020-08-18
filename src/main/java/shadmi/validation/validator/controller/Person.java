package shadmi.validation.validator.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {




    @Size(min = 3, max = 3)
    String id;
    @Size(min = 4, max = 4)
    String name;
}
