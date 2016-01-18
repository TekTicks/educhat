package in.tekticks.educhat.asynctask;

import android.content.Context;
import android.os.AsyncTask;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;


public class SignInUserAsyncTask extends AsyncTask<String,Void,Boolean>{
    Context context;
    SignInUserCallback callback;

    public SignInUserAsyncTask(Context context, SignInUserCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    String responseString;
    public interface SignInUserCallback{

        public void onStart(boolean status);
        public void onResult(boolean result);
    }
    @Override
    protected Boolean doInBackground(String... params) {
        InputStream mInputStream=null;
        try{
            HttpPost httpPost = new HttpPost(params[0]);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpPost);

        }catch(ClientProtocolException ex){

        }
        catch (IOException ex){

        }
        return null;
    }
}
