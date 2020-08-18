package shadmi.validation.validator.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shadmi.validation.validator.aop.ValidatorAnnotation;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
public class ValidatorController {

    @PostMapping(
            path= "/test-validator",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity testValidator(@RequestBody @ValidatorAnnotation RequestDTO request){
        return new ResponseEntity("Valid", HttpStatus.OK);
    }
}
