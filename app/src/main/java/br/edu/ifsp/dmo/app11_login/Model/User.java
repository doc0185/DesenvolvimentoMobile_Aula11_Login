package br.edu.ifsp.dmo.app11_login.Model;

import androidx.annotation.Nullable;

public class User {
    private static final String TAG = User.class.getSimpleName();
    private String username;
    private String password;

    public User(){}

    public User(String username, String password){
        this.username = username;
        generatePassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        User other = (User) obj;
        boolean equals = false;
        if(other.getUsername().equals(this.username)){
            if(other.getPassword().equals(this.password)){
                equals = true;
            }
        }
        return equals;
    }

    private void generatePassword(String password){

    }
}
