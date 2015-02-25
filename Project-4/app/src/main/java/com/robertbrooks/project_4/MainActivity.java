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

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    final String TAG = "DEV-4";

    // Butterknife
    @InjectView(R.id.userText) TextView userText;
    @InjectView(R.id.textView) TextView resultText;
    @InjectView(R.id.userButton) Button userButton;
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
        resultText.setMovementMethod(new ScrollingMovementMethod());

        // set progress bar to invisible
        progBar.setVisibility(View.INVISIBLE);

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
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

}
