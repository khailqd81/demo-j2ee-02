package exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> validates = exception.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> v : validates) {
            errors.add(v.getPropertyPath() +" " +  v.getMessage());
        }
        return Response
                .status(400)
                .entity(errors)
                .build();
    }
}