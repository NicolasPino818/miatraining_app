package cl.duoc.data_finders.errorHandler.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NotAllowedException extends RuntimeException{
    private HttpStatus status = HttpStatus.FORBIDDEN;
    private String message = "ACTION NOT ALLOWED";
    private String code = "permission:not-allowed";
}
