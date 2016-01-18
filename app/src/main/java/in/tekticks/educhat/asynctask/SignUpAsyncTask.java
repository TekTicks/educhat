package in.tekticks.educhat.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import in.tekticks.educhat.utils.Constants;


public class SignUpAsyncTask extends AsyncTask<ArrayList<String>,Void,Boolean>{

    Context context;
    FetchVerificationCodeCallback callback;
    public interface FetchVerificationCodeCallback{

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public SignUpAsyncTask(Context context, FetchVerificationCodeCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {
        try {
            Log.v(Constants.LOG,"Signup Background Process Start");
            Log.v(Constants.LOG,Constants.SignInLink);
            ArrayList<String> result= params[0];
            URL url= new URL(Constants.SignUpLink);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Authorization", Constants.AUTHORIZATION_KEY);
            httpURLConnection.setRequestProperty("Content-Type",Constants.CONTENT_TYPE);
            httpURLConnection.connect();
            DataOutputStream mDataOutputStream=new DataOutputStream(httpURLConnection.getOutputStream());

            JSONObject mJsonObject=new JSONObject();
            mJsonObject.put("username",result.get(1));
            mJsonObject.put("password", result.get(2));

            Log.v(Constants.LOG, mJsonObject.toString());

            mDataOutputStream.writeBytes(mJsonObject.toString());
            mDataOutputStream.flush();
            mDataOutputStream.close();

            int Status_code=httpURLConnection.getResponseCode();
            Log.v(Constants.LOG,"Server Response:"+Status_code);
            if(Status_code==Constants.STATUS_201)
                return true;
            else
                return false;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        callback.onResult(aBoolean);
        super.onPostExecute(aBoolean);
    }
}
