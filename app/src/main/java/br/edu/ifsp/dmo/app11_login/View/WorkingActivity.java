package br.edu.ifsp.dmo.app11_login.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.edu.ifsp.dmo.app11_login.Constant.Constants;
import br.edu.ifsp.dmo.app11_login.R;

public class WorkingActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString(Constants.KEY_USER);

        mTextView.findViewById(R.id.text_user_logged);
        mTextView.setText("Olá " + name);
    }
}