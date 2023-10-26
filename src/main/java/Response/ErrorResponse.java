/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

/**
 *
 * @author vinic
 */
public record ErrorResponse(Payload error) implements Response<ErrorResponse.Payload> {
    public ErrorResponse(int code, String message) {
        this(new ErrorResponse.Payload(code, message));
    }

    @Override
    public Payload payload() {
        return error;
    }


    public record Payload(int code, String message) {
    }
}