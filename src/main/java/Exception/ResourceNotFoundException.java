package Exception;

public class ResourceNotFoundException extends ServerResponseException {
    public ResourceNotFoundException(final String message) {
        super(404, "Unable to find '%s'.".formatted(message));
    }
}
