package in.tekticks.educhat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.tekticks.educhat.R;
import in.tekticks.educhat.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView signUp;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(Constants.LOG,"Login Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setViews();
    }

    private void findViews() {
        btnLogin = (Button) findViewById(R.id.ButtonLogin);
        signUp = (TextView) findViewById(R.id.signUp);
    }

    private void setViews() {
        btnLogin.setOnClickListener(listner);
        signUp.setOnClickListener(listner);
    }

    View.OnClickListener listner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ButtonLogin:loginNow();break;
                case R.id.signUp:signUpNow();break;
            }
        }
    };

    private void signUpNow() {
        i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    private void loginNow() {
        i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}
