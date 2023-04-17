package br.edu.ifsp.dmo.app11_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.dmo.app11_login.Controller.MainController;
import br.edu.ifsp.dmo.app11_login.Exception.IllegalInputException;
import br.edu.ifsp.dmo.app11_login.Exception.UserNotFoundException;
import br.edu.ifsp.dmo.app11_login.R;

public class MainActivity extends AppCompatActivity {
    
    private MainController controller;
    private Button mButton;
    private CheckBox mCheckBox;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        setListener();
        getSupportActionBar().hide();
        controller = new MainController(this);
    }

    @Override
    protected void onStop() {
        mUsernameEditText.setText("");
        mPasswordEditText.setText("");
        super.onStop();
    }

    private void findById(){
        mButton = findViewById(R.id.button_login);
        mCheckBox = findViewById(R.id.check_remember_login);
        mUsernameEditText = findViewById(R.id.editText_username);
        mPasswordEditText = findViewById(R.id.editText_password);
        mFloatingActionButton = findViewById(R.id.btn_add_user);
    }

    private void setListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    
    private void login(){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        try{
            controller.login(username, password, mCheckBox.isChecked());
        } catch (UserNotFoundException e) {
            Toast.makeText(this, "Usuário não cadastrado", Toast.LENGTH_SHORT).show();
        } catch (IllegalInputException e) {
            Toast.makeText(this, "Dados de login inválidos", Toast.LENGTH_SHORT).show();
        }

    }

    private void addUser(){
        Intent intent = new Intent(this, UserAddActivity.class);
        startActivity(intent);
    }
}