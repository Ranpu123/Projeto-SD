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
public class ValidateAdmin {
    public static void check(Header header) throws ServerResponseException{
        try {
            DecodedJWT jwt = JwtHelper.decode(header.getToken());
            Claim isAdmin = jwt.getClaim("isAdmin");
            Claim registro = jwt.getClaim("userId");
            if(!isAdmin.asBoolean()){
                if(!registro.isMissing()){
                    throw new ForbiddenAccessException(registro.asLong());
                }
                throw new ForbiddenAccessException();
            }
        } catch (JWTVerificationException e) {
            throw new ForbiddenAccessException();
        }
    }
}
