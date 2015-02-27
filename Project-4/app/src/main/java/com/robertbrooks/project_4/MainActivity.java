// Robert Brooks
// MainActivity.java
package com.robertbrooks.project_4;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    final String TAG = "DEV-4";

    // Butterknife
    @InjectView(R.id.userText) TextView muserText;
   // @InjectView(R.id.textView1) TextView mresultText;
    @InjectView(R.id.userButton) Button muserButton;
    @InjectView(R.id.progressBar) ProgressBar progBar;
    @InjectView(R.id.listView) ListView mListView;

    List<ATask> tasks;
    List<MLB> mlbList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // BK Inject
        ButterKnife.inject(this);

        // set progress bar to invisible
        progBar.setVisibility(View.INVISIBLE);

        // set ListView to invisible
        mListView.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

    }
    // ButterKnife Button Implementation
    @OnClick(R.id.userButton)
        public void run()
        {
            // get user input text
            String inputText = muserText.getText().toString();

            // Network Check
            if (isOnline())
            {
                try {
                    // Create Query String
                    String redditUrl = "http://api.reddit.com/r/mlb/search.json";
                    String searchURL = (redditUrl + "?q=" + inputText + "&restrict_sr=on");
                    // Run AsyncTask
                    runTask(searchURL);

                } catch (Exception e) {
                    e.printStackTrace();
                    // Alert user
                    Toast.makeText(this, "Invalid Query", Toast.LENGTH_LONG).show();
                }
            } else {
                // Alert user
                Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
            }
        }

    // Figure out how to keep listView
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
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

    // AsyncTask Implementation
    private class ATask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
           // Set progress bar to visible
            if (tasks.size() == 0)
            {
                progBar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            // Get string from HttpURLConnection
            String content = ManageHttp.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            // Parse JSON
            mlbList = JSONParse.parse(result);
            // Update ListView
            updateDisplay();
            // Set user input text back to default
            muserText.setText("");
            tasks.remove(this);
            // Set progress bar to invisible when AsyncTask is done
            if (tasks.size() == 0)
            {
                progBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    // Custom Functions

    // Update Display
    public void updateDisplay()
    {
        // create array adapter for listView
        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        String title = "";
        if (mlbList != null)
        {
            // Loop through JSON and populate listAdapter
            for (MLB mlb : mlbList)
            {
                String author = mlb.getAuthor();
                String domain = mlb.getDomain();
                title = mlb.getTitle();
                String outputString = ("\n" + title + "\n" + "Author: " + author + "\n" + domain + "\n");
                listAdapter.add(outputString);
            }
            // Check to see if there are no results
            if (title == "")
            {
                // Alert user and set ListView to invisible
                Toast.makeText(this, "Sorry, there are no search results", Toast.LENGTH_LONG).show();
                mListView.setVisibility(View.INVISIBLE);
            } else{
                // Set ListView to visible and set adapter to populate ListView
                mListView.setVisibility(View.VISIBLE);
                mListView.setAdapter(listAdapter);
            }
        }
    }

    // Run AsyncTask
    public void runTask(String urlSearch)
    {
        ATask task = new ATask();
        task.execute(urlSearch);
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
