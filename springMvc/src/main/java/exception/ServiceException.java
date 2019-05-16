package exception;

import java.util.ArrayList;
import java.util.List;

public class ServiceException extends RuntimeException  {

    private static final long serialVersionUID = 1L;
    private List<ErrorCode> errorCodes;

    public ServiceException(ErrorCode error) {
        super();
        this.errorCodes = new ArrayList<>();
        errorCodes.add(error);
    }

    public ServiceException(ErrorCode error, Throwable cause) {
        super(cause);
        errorCodes.add(error);
    }

    public ServiceException(List<ErrorCode> errorCodes) {
        super();
        this.errorCodes = errorCodes;
    }

    public ServiceException(Exception e) {
        super();
    }

    public List<ErrorCode> getErrorCodes() {
        return errorCodes;
    }
}
