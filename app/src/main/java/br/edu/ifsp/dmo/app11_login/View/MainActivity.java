package br.edu.ifsp.dmo.app11_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.dmo.app11_login.R;

public class MainActivity extends AppCompatActivity {

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
                //login();
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addUser();
            }
        });
    }
}