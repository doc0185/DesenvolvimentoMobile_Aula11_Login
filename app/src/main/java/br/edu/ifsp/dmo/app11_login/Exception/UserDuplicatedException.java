package br.edu.ifsp.dmo.app11_login.Exception;

public class UserDuplicatedException extends Exception{

    public UserDuplicatedException(String username) {
        super("Duplicated User on database: " + username);
    }
}
