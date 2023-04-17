package br.edu.ifsp.dmo.app11_login.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.app11_login.Constant.Constants;
import br.edu.ifsp.dmo.app11_login.Exception.UserDuplicatedException;
import br.edu.ifsp.dmo.app11_login.Model.User;

public class UserDAOJson implements IUserDAO{

    private Context context;
    private List<User> dataset;

    public UserDAOJson(Context context){
        this.context = context;
        dataset = new ArrayList<>();
        readDataset();

    }
    @Override
    public boolean create(User user) throws UserDuplicatedException {
        if(user != null){
            User inDataset = dataset.stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findAny().orElse(null);
            if(inDataset != null){
                throw new UserDuplicatedException(user.getUsername());
            }
            dataset.add(user);

            writeDataset();
            readDataset();

            return true;
        }
        return false;
    }


    @Override
    public User findByUsername(String username) {
        return dataset.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return dataset;
    }

    private void readDataset(){
        SharedPreferences preferences;
        String json;

        User user;
        JSONObject jsonObject;
        JSONArray jsonArray;

        preferences = context.getSharedPreferences(Constants.DATABASE_FILE_NAME, Context.MODE_PRIVATE);
        json = preferences.getString(Constants.TABLE_NAME, "");
        if(!json.isEmpty()){
            dataset.clear();
            try{
                jsonArray = new JSONArray(json);
                for(int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    user = new User();
                    user.setUsername(jsonObject.getString(Constants.ATTR_USERNAME));
                    user.setPassword(jsonObject.getString(Constants.ATTR_PASSWORD));
                    dataset.add(user);
                }
            } catch(JSONException e){
                Log.e("UserDAOJson", e.getMessage());
            }
        } else{
            Log.e("UserDAOJson", "Sem dados para recuperar do JSON");
        }
    }

    private void writeDataset(){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        JSONObject jsonObject;
        JSONArray jsonArray;

        jsonArray = new JSONArray();
        for(User u : dataset){
            jsonObject = new JSONObject();
            try{
                jsonObject.put(Constants.ATTR_USERNAME, u.getUsername());
                jsonObject.put(Constants.ATTR_PASSWORD, u.getPassword());
                jsonArray.put(jsonObject);
            } catch(JSONException e){
                Log.e("UserDAOJson", e.getMessage());
            }
        }

        preferences = context.getSharedPreferences(Constants.DATABASE_FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(Constants.TABLE_NAME, jsonArray.toString());
        editor.commit();
    }
}
