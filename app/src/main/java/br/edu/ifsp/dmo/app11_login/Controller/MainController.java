package br.edu.ifsp.dmo.app11_login.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.EditText;

import br.edu.ifsp.dmo.app11_login.Constant.Constants;
import br.edu.ifsp.dmo.app11_login.DAO.IUserDAO;
import br.edu.ifsp.dmo.app11_login.DAO.UserDAOImpl;
import br.edu.ifsp.dmo.app11_login.DAO.UserDAOJson;
import br.edu.ifsp.dmo.app11_login.Exception.IllegalInputException;
import br.edu.ifsp.dmo.app11_login.Exception.UserNotFoundException;
import br.edu.ifsp.dmo.app11_login.Model.User;
import br.edu.ifsp.dmo.app11_login.View.WorkingActivity;

public class MainController {
    private Context context;
    private IUserDAO data;

    public MainController(Context context){
        this.context = context;
        // data = UserDAOImpl.getInstance();
        data = new UserDAOJson(context);
    }

    public void checkPreferences(EditText user, EditText pass, CheckBox remember){
        String username, password;
        boolean savePrefs;

        SharedPreferences preferences = context.getSharedPreferences(Constants.FILE_LOGIN_PREFS, Context.MODE_PRIVATE);
        savePrefs = preferences.getBoolean(Constants.KEY_PREF, false);
        if(savePrefs){
            username = preferences.getString(Constants.KEY_USER, "");
            password = preferences.getString(Constants.KEY_PASSW, "");
            user.setText(username);
            pass.setText(password);
        }
        remember.setChecked(savePrefs);
    }

    public void login(String username, String password, boolean savePreference) throws UserNotFoundException, IllegalInputException {
        if(username.isEmpty() || password.isEmpty()){
            throw new IllegalInputException("Illegal data input to login.");
        }
        User loginUser = new User(username, password);
        User search = data.findByUsername(username);
        if(search == null){
            throw new UserNotFoundException(username);
        }

        if(User.autenticate(search, loginUser)){
            remember(username, password, savePreference);
            openWorkingActivity(search);
        } else{
            throw new IllegalInputException("Illegal data input to login.");
        }
    }

    private void openWorkingActivity(User u){
        Intent intent = new Intent(context, WorkingActivity.class);
        intent.putExtra(Constants.KEY_USER, u.getUsername());
        context.startActivity(intent);
    }

    private void remember(String username, String password, boolean savePreference){
        SharedPreferences preferences = context.getSharedPreferences(Constants.FILE_LOGIN_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if(savePreference){
            editor.putString(Constants.KEY_USER, username);
            editor.putString(Constants.KEY_PASSW, password);
        } else{
            editor.putString(Constants.KEY_USER, ""); // apagar o que foi salvo, o usuário tirou o check Save
            editor.putString(Constants.KEY_PASSW, "");
        }
        editor.putBoolean(Constants.KEY_PREF, savePreference);
        editor.commit();
    }
}
