package br.edu.ifsp.dmo.app11_login.Exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String username){
        super("User Not Found" + username);
    }
}
