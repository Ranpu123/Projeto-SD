package Exception;

import Response.ErrorResponse;
import Response.IntoResponse;

public class ServerResponseException extends Exception implements IntoResponse{
    private final int errorCode;

    public ServerResponseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorResponse intoResponse() {
        return new ErrorResponse(errorCode, getMessage());
    }
}
