/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Misc;

import Model.Header;
import Exception.*;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class ValidateToken {
    public static void check(Header header) throws ServerResponseException{
        String token = header.getToken();
        if (token == null){
            throw new UnauthorizedAccessException();
        }
        DecodedJWT jwt;
        
        try {
            jwt = JwtHelper.verify(token);
        } catch (JWTVerificationException e) {
            throw  new UnauthorizedAccessException();
        }
        
        Claim registro = jwt.getClaim("userId");
        Claim isAdmin = jwt.getClaim("isAdmin");
        if(registro.isMissing()||registro.isNull()||isAdmin.isNull()||isAdmin.isMissing()){
            throw new UnauthorizedAccessException();
        }
    }
}
