package br.edu.ifsp.dmo.app11_login.Controller;

import android.content.Context;

import br.edu.ifsp.dmo.app11_login.DAO.IUserDAO;
import br.edu.ifsp.dmo.app11_login.DAO.UserDAOImpl;
import br.edu.ifsp.dmo.app11_login.DAO.UserDAOJson;
import br.edu.ifsp.dmo.app11_login.Exception.IllegalInputException;
import br.edu.ifsp.dmo.app11_login.Exception.UserDuplicatedException;
import br.edu.ifsp.dmo.app11_login.Model.User;

public class UserAddController {

    private Context context;
    private IUserDAO data;

    public UserAddController(Context context){
        this.context = context;
        //data = UserDAOImpl.getInstance();
        data = new UserDAOJson(context);
    }

    public boolean insertUser(String username, String password, String confirm) throws UserDuplicatedException, IllegalInputException {
        if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            throw new IllegalInputException("Existem dados não preenchidos");
        }

        if(!password.equals(confirm)){
            throw new IllegalInputException("Senhas não conferem");
        }

        User user = new User(username, password);
        try{
            return data.create(user);
        } catch (UserDuplicatedException e){
            throw e;
        }
    }
}
