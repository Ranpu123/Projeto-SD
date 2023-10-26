package Requests.Handler;
import Requests.Request;
import Exception.ServerResponseException;

public interface Handler {
    public Request<?> handle(String jsonResponse) throws ServerResponseException;
}
