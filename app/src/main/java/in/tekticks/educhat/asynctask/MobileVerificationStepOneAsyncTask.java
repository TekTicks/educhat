package in.tekticks.educhat.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import in.tekticks.educhat.data.MobileVerificationStepOneWrapper;
import in.tekticks.educhat.utils.Constants;

public class MobileVerificationStepOneAsyncTask extends AsyncTask<String, Void, MobileVerificationStepOneWrapper> {

    public MobileVerificationStepOneAsyncTask(Context context, MobileVerificationStepOneCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    private Context context;
    private MobileVerificationStepOneCallback callback;

    public interface MobileVerificationStepOneCallback {
        public void onStart(boolean status);

        public void onEnd(MobileVerificationStepOneWrapper result);

    }

    @Override
    protected MobileVerificationStepOneWrapper doInBackground(String... params) {
        try {
            Log.v(Constants.LOG, "Mobile Verification Step One Background Process Start");

            StringBuilder builder = new StringBuilder(Constants.MobileVerificationStepOneLink);
            builder.append("?notify=" + Constants.NOTIFY);
            builder.append("&out=JSON");
            builder.append("&cn=IN");
            builder.append("&passkey=" + Constants.passkey);
            builder.append("&e-notify=" + Constants.e_notify);
            builder.append("&mobile=" + params[0].toString());

            Log.v(Constants.LOG, builder.toString());
            URL url = new URL(builder.toString());


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            String response = fromInputStream(inputStream);

            Log.v(Constants.LOG, "Server Response" + response);

            return parseSessionId(response);


        } catch (MalformedURLException e) {
            Log.v(Constants.LOG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.v(Constants.LOG, e.getMessage());
            e.printStackTrace();
        }
        Log.v(Constants.LOG, "Mobile Verification Step One Background Process End");
        return null;
    }

    private MobileVerificationStepOneWrapper parseSessionId(String response) {
        Log.v(Constants.LOG, "Parse Session Id");
        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.v(Constants.LOG, jsonObject.getString("SessionId"));
            return new MobileVerificationStepOneWrapper(jsonObject.getString("SessionId"), jsonObject.getString("VerificationNode"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String fromInputStream(InputStream stream) throws IOException {
        InputStreamReader streamReader = new
                InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        StringBuilder builder = new StringBuilder();
        String tempString = "";
        while ((tempString = bufferedReader.readLine()) != null) {
            builder.append(tempString);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    @Override
    protected void onPreExecute() {
        callback.onStart(true);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(MobileVerificationStepOneWrapper s) {
        callback.onEnd(s);
        super.onPostExecute(s);
    }
}
