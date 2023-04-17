package br.edu.ifsp.dmo.app11_login.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmo.app11_login.Controller.UserAddController;
import br.edu.ifsp.dmo.app11_login.Exception.IllegalInputException;
import br.edu.ifsp.dmo.app11_login.Exception.UserDuplicatedException;
import br.edu.ifsp.dmo.app11_login.R;

public class UserAddActivity extends AppCompatActivity {

    private UserAddController controller;
    private EditText username;
    private EditText password;
    private EditText passwordConfirm;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        controller = new UserAddController(this);
        findById();
        setListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void findById(){
        username = findViewById(R.id.editText_newUsername);
        password = findViewById(R.id.editText_newPassword);
        passwordConfirm = findViewById(R.id.editText_newConfirmPassword);
        mButton = findViewById(R.id.btn_add_user);
    }

    private void saveUser(){
        String user, pass, confirm;
        user = username.getText().toString();
        pass = username.getText().toString();
        confirm = username.getText().toString();
        boolean result = false;

        try{
            result = controller.insertUser(user, pass, confirm);
        } catch (UserDuplicatedException e) {
            Toast.makeText(this, "Usuário já cadastrado no aplicativo", Toast.LENGTH_SHORT);
        } catch (IllegalInputException e) {
            Toast.makeText(this, "Foram informados dados inválidos", Toast.LENGTH_SHORT);
        }

        if(result){
            Toast.makeText(this, "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT);
            finish();
        } else{
            username.setText("");
            password.setText("");
            passwordConfirm.setText("");
        }

    }

    private void setListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // saveUser();
            }
        });
    }
}