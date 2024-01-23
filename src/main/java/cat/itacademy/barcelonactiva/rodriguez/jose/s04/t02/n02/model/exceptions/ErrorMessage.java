package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}