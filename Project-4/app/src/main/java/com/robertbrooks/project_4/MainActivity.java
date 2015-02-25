package com.robertbrooks.project_4;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    final String TAG = "DEV-4";

    // Butterknife
    @InjectView(R.id.userText) TextView muserText;
    @InjectView(R.id.textView) TextView mresultText;
    @InjectView(R.id.userButton) Button muserButton;
    @InjectView(R.id.progressBar) ProgressBar progBar;

    List<ATask> tasks;




    /*public TextView mUserText;
    private ArrayAdapter mArrayAdapter;
    private ArrayList<String> mTestList = new ArrayList<String>();
    private ProgressBar progressBar;*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // BK Inject
        ButterKnife.inject(this);

        // set up textView Scrolling
        mresultText.setMovementMethod(new ScrollingMovementMethod());

        // set progress bar to invisible
        progBar.setVisibility(View.INVISIBLE);

    }
    @OnClick(R.id.userButton)
        public void run()
        {
            String inputText = muserText.getText().toString();
            // Network Check
            if (isOnline())
            {

            } else {
                Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
            }


           // URL searchURL = null;
            /*try {
                String redditUrl = "http://api.reddit.com/r/mlb/search.json";
                String redditSearch = inputText;
                URL searchURL = new URL(redditUrl + "?q=" + inputText + "&restrict_sr=on");
                new ATask().execute(searchURL);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Invalid query");
            }*/



            //http://www.reddit.com/r/volvo/search.xml?q=wagon&restrict_sr=on


            //runTask();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ATask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            progBar.setVisibility(View.VISIBLE);
            updateDisplay("Begin Task");
        }

        //@TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            progBar.setVisibility(View.INVISIBLE);
            //MLB result = new MLB(apiData)
            //updateDisplay(result);
        }

        /*@Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }*/
    }

    // Custom Functions

    // Update Display
    public void updateDisplay(String testMessage)
    {
        mresultText.append(testMessage + "\n");

    }

    // run AsyncTask
    public void runTask()
    {
        // Run Async Task
        ATask task = new ATask();
        //task.execute("Data 1", "Data 2", "Data 3");
    }

    // Network Check

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
        {
            return true;
        } else {

            return false;
        }
    }

}
