package in.tekticks.educhat.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import in.tekticks.educhat.utils.Constants;

public class MobileVerificationStepTwoAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private MobileVerificationStepTwoCallback callback;

    @Override
    protected void onPreExecute() {
        callback.onStart(true);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        callback.onEnd(s);
        super.onPostExecute(s);
    }

    public MobileVerificationStepTwoAsyncTask(Context context, MobileVerificationStepTwoCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public interface MobileVerificationStepTwoCallback {
        public void onStart(boolean status);
        public void onEnd(String result);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.v(Constants.LOG, "Mobile Verification Step Two Background Process Start");

            StringBuilder builder=new StringBuilder(Constants.MobileVerificationStepTwoLink);
            builder.append("?SID="+params[0]);

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

            Log.v(Constants.LOG,"Server Response"+response);

            return parseVerificationStatus(response);


        } catch (MalformedURLException e) {
            Log.v(Constants.LOG,e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.v(Constants.LOG,e.getMessage());
            e.printStackTrace();
        }
        Log.v(Constants.LOG,"Mobile Verification Step Two Background Process End" );
        return null;
    }

    private String parseVerificationStatus(String response) {
        Log.v(Constants.LOG,"Pare Verification Status");
        try {
            JSONObject jsonObject = new JSONObject(response);
            String result = jsonObject.getString("VerificationStatus");
            Log.v(Constants.LOG,result);
            return result;
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
}
