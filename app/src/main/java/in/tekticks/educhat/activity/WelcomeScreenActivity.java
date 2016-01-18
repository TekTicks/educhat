package in.tekticks.educhat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.tekticks.educhat.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    Button btlogin,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        btlogin=(Button)findViewById(R.id.button1);
        btnSignUp=(Button)findViewById(R.id.button2);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WelcomeScreenActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WelcomeScreenActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }


}
