package com.robertbrooks.project_4;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    final String TAG = "DEV-4";

    // Butterknife
    @InjectView(R.id.userText) TextView muserText;
    @InjectView(R.id.textView) TextView mresultText;
    @InjectView(R.id.userButton) Button muserButton;
    @InjectView(R.id.progressBar) ProgressBar progBar;



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
    public void runTask()
    {
        // Run Async Task
        ATask task = new ATask();
        task.execute("Data 1", "Data 2", "Data 3");
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

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i < params.length; i++)
            {
                publishProgress("Processing " + params[i]);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Complete";

        }

        @Override
        protected void onPostExecute(String result) {
            progBar.setVisibility(View.INVISIBLE);
            updateDisplay(result);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }
    }

    // Custom Functions

    // Update Display
    public void updateDisplay(String testMessage)
    {
        mresultText.append(testMessage + "\n");

    }

}
