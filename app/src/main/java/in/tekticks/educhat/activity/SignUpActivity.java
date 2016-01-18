package in.tekticks.educhat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.tekticks.educhat.R;
import in.tekticks.educhat.utils.Constants;

public class SignUpActivity extends AppCompatActivity {

    private EditText mPhoneNumber,mName,mPassword;
    private Button mSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Log.v(Constants.LOG, "Signup Activity");
        findViews();
        setView();
    }

    private void findViews(){
        mName=(EditText)findViewById(R.id.sign_up_name);
        mPassword=(EditText)findViewById(R.id.sign_up_password);
        mPhoneNumber=(EditText)findViewById(R.id.sign_up_mobile_number);
        mSignUpButton=(Button)findViewById(R.id.sign_up_button);
    }

    private void setView(){
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,MobileVerificationActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("PhoneNumber",mPhoneNumber.getText().toString());
                bundle.putString("Name",mName.getText().toString());
                bundle.putString("Password",mPassword.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }

}
