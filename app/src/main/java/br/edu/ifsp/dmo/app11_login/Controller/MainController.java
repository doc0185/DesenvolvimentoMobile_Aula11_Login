package br.edu.ifsp.dmo.app11_login.Controller;

import android.content.Context;

import br.edu.ifsp.dmo.app11_login.DAO.IUserDAO;
import br.edu.ifsp.dmo.app11_login.DAO.UserDAOImpl;
import br.edu.ifsp.dmo.app11_login.Exception.IllegalInputException;
import br.edu.ifsp.dmo.app11_login.Exception.UserNotFoundException;
import br.edu.ifsp.dmo.app11_login.Model.User;

public class MainController {
    private Context context;
    private IUserDAO data;

    public MainController(Context context){
        this.context = context;
        data = UserDAOImpl.getInstance();
    }

    public void login(String username, String password) throws UserNotFoundException, IllegalInputException {
        if(username.isEmpty() || password.isEmpty()){
            throw new IllegalInputException("Illegal data input to login.");
        }
        User loginUser = new User(username, password);
        User search = data.findByUsername(username);
        if(search == null){
            throw new UserNotFoundException(username);
        }

        if(User.autenticate(search, loginUser)){
            //TODO ABRIR OUTRA ACTIVITY
        } else{
            throw new IllegalInputException("Illegal data input to login.");
        }
    }
}
