package org.petero.droidfish.qr;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.regex.Pattern;

/**
 * Created by lordvulkan on 7/03/17.
 */

public class AsyncPGNParser extends AsyncTask<String, Void, String> {
    public static final String EXTRA_MESSAGE = "com.example.lordvulkan.qrtest.MESSAGE";

    public final int QR_SIZE = 1024;

    public Context context = null;

    public AsyncPGNParser(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        if(params[0]!=null){
            String qr_text = params[0];
            Pattern pattern = Pattern.compile("\\d+\\.\\s+");
            result = pattern.matcher(qr_text).replaceAll("");
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result) {
        //Intent intent = new Intent(context, ResultActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, result);
        //context.startActivity(intent);
    }
}
