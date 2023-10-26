/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

public class UnauthorizedAccessException extends ServerResponseException{
    public UnauthorizedAccessException(){
        super(401, "Unable to authenticate user");
    }
}