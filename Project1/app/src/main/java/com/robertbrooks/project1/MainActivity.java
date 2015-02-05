package com.robertbrooks.project1;

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

import org.w3c.dom.Text;

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
                String add = mUserText.getText().toString();
                word = mUserText.getText().toString();

                //Check for duplicates to ensure only unique values are stored
                if(!mTestList.contains(add))
                    mTestList.add(word);
                // notifyDataSetChanged
                mArrayAdapter.notifyDataSetChanged();

                // set number of entries text view
                int num = mTestList.size();
                mNumberEntries.setText("" + num);

                // ListView Implementation
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView selected = (TextView) view;
                        selected.getText().toString();
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

    // Calculate average
}
