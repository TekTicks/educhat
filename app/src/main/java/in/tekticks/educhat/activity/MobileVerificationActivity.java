package in.tekticks.educhat.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.asynctask.MobileVerificationStepOneAsyncTask;
import in.tekticks.educhat.asynctask.MobileVerificationStepTwoAsyncTask;
import in.tekticks.educhat.asynctask.SignUpAsyncTask;
import in.tekticks.educhat.data.MobileVerificationStepOneWrapper;
import in.tekticks.educhat.utils.Constants;

public class MobileVerificationActivity extends AppCompatActivity {

    private Intent intent;
    private TextView verificationStatus, numberVerify;
    private Button callButton;
    private String Session_id;
    private ArrayList<String> arrayList;
    private String[] temp = null;
    private Handler mHandler;
    private Dialog dialog;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        Log.v(Constants.LOG, "Mobile Verification Activity");
        getPreviousActivityData();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.verify_phone_layout);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                new MobileVerificationStepOneAsyncTask(getApplicationContext(), new MobileVerificationStepOneAsyncTask.MobileVerificationStepOneCallback() {
                    @Override
                    public void onStart(boolean status) {

                    }

                    @Override
                    public void onEnd(MobileVerificationStepOneWrapper result) {
                        numberVerify.setText(MobileVerificationStepOneWrapper.getVerificationNode());
                        Session_id = MobileVerificationStepOneWrapper.getSessionId();
                    }
                }).execute(arrayList.get(1).toString());
            }
        });

        dialog.show();
        findViews();
    }

    private void getPreviousActivityData() {
        intent = getIntent();
        arrayList = new ArrayList<String>();
        arrayList.add(intent.getStringExtra("Name").toString());
        arrayList.add(intent.getStringExtra("PhoneNumber").toString());
        arrayList.add(intent.getStringExtra("Password").toString());
    }

    private void findViews() {
        verificationStatus = (TextView) dialog.findViewById(R.id.verification_status);
        numberVerify = (TextView) dialog.findViewById(R.id.numberVerify);
        callButton = (Button) dialog.findViewById(R.id.verify_call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numberVerify.getText().toString()));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        mobileVerificationStepTwo(Session_id);
        super.onRestart();
    }

    private void mobileVerificationStepTwo(String result) {
        new MobileVerificationStepTwoAsyncTask(getApplicationContext(), new MobileVerificationStepTwoAsyncTask.MobileVerificationStepTwoCallback() {
            @Override
            public void onStart(boolean status) {
            }

            @Override
            public void onEnd(String result) {

                if (result == "VERIFIED") {
                    Log.v(Constants.LOG, "Mobile Verification Step Two Verified");
                    verificationStatus.setText(result);
                    dialog.dismiss();
                    //signUpTask(result);
                } else {
                    Log.v(Constants.LOG, "Mobile Verification Step Two UnVerified");
                    verificationStatus.setText(result);
                }
                Log.v(Constants.LOG, "Mobile Verification Step Two End");
            }
        }).execute(result);
    }

    private void signUpTask(String result) {
        new SignUpAsyncTask(getApplicationContext(), new SignUpAsyncTask.FetchVerificationCodeCallback() {
            @Override
            public void onStart(boolean status) {
                Log.v(Constants.LOG, "Signup Task Start");
                mProgressDialog = new ProgressDialog(MobileVerificationActivity.this);
                mProgressDialog.setTitle(Constants.APP_NAME);
                mProgressDialog.setMessage("Stay with us");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            }

            @Override
            public void onResult(boolean result) {
                mProgressDialog.dismiss();
                if (result == true) {
                    Log.v(Constants.LOG, "Signup Result" + result);
                    intent = new Intent(MobileVerificationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.v(Constants.LOG, "Signup Result" + result);
                }
                Log.v(Constants.LOG, "Signup Task End");
            }
        }).execute(arrayList);
    }
}
