package com.robertbrooks.project1_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    final String TAG = "Main Activity";
    private TextView mUserText;
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;
    private ArrayList<String> mTestList = new ArrayList<String>();
    private TextView mNumberEntries;
    private String word;
    private TextView mAvgText;
    private String add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References to user, numberEntries, averageLength text fields
        mUserText = (TextView) findViewById(R.id.userText);
        mNumberEntries = (TextView) findViewById(R.id.numberEntries);
        mAvgText = (TextView) findViewById(R.id.averageLength);

        // Reference for listView
        mListView = (ListView) findViewById(R.id.listView);

        // Create adapter for listView
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mTestList);
        mListView.setAdapter(mArrayAdapter);

        // user button implementation
        Button button = (Button) findViewById(R.id.userButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text from user input
                add = mUserText.getText().toString();
                word = mUserText.getText().toString();
                // run duplicate check
                dupCheck();
                // notifyDataSetChanged
                mArrayAdapter.notifyDataSetChanged();
                // set number of entries text view
                updateText();
                // Call calcAverage Function
                calcAverage();

                // ListView Implementation
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView selected = (TextView) view;
                        final String alertText = selected.getText().toString();


                        // create AlertDialog
                        AlertDialog.Builder userAlert = new AlertDialog.Builder(MainActivity.this);
                        // Set Title
                        userAlert.setTitle(R.string.user_alert);
                        // message
                        userAlert.setMessage(alertText);
                        // Ok button
                        userAlert.setPositiveButton(R.string.ok_alert, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // code here for "ok" button if desired
                            }
                        });
                        // remove button
                        userAlert.setNegativeButton(R.string.remove_alert, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTestList.remove(alertText);
                                // notifyDataSetChanged
                                mArrayAdapter.notifyDataSetChanged();
                                // calculate average and update
                                calcAverage();
                                // update mNumberEntries
                                updateText();
                            }
                        });
                        // display userAlert
                        userAlert.show();
                    }
                });
            }
        });
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

   // Custom Functions

    // Calculate Average
    public void calcAverage()
    {
        int sum = 0;
        double average;
        for (String word : mTestList)
            sum += word.length();
        average = (double) sum/mTestList.size();

        // change text back to default if there are no entries in arrayList
        if (average > 0)
        {
           mAvgText.setText(average + "");
        }
        else
        {
            mAvgText.setText(R.string.avg_length);
        }
    }
    // update mNumberEntries Text
    public void updateText()
    {
        int num = mTestList.size();
        // change text back to default if there are no entries in arrayList
        if (num > 0)
        {
            mNumberEntries.setText(num + "");
        }
        else
        {
            mNumberEntries.setText(R.string.num_entries);
        }
    }
    // Duplicate Check w/ empty text validation
    public void dupCheck()
    {
        //Check for duplicates to ensure only unique values are stored
        if(!mTestList.contains(add) && word.trim().length() > 0) {
            mTestList.add(word);
            mUserText.setText("");
        }
    }
}
