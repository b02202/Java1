// Robert Brooks
// Java 1
// Project 3
// MainActivity.java

package com.robertbrooks.advanced_views;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import TourInfo.TourInfo;


public class MainActivity extends ActionBarActivity {

    // Log String
    final String TAG = "DEV3";

    //Spinner listView and TextView
    public ListView mListView;
    public TextView mTitleText;
    public Spinner mSpinner;

    // Adapters
    public ArrayAdapter<String> mSpinnerAdapter;
    public ArrayAdapter<String> mListAdapter;

    // ArrayList Collections
    public ArrayList<String> topList ;;
    public ArrayList<String> spinList = new ArrayList<String>();
    public ArrayList<TourInfo> mtours = new ArrayList<TourInfo>();

    // Initial run check bool for Spinner
    private boolean intialrun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check orientation and set up elements and data accordingly
        orientationRun();
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

    // Landscape or Portrait layout implementation
    public void orientationRun()
    {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            // execute the layout for Landscape Orientation
            landLayout();
        }
        else
        {
            // execute the layout for Portrait Orientation
            portLayout();
        }
    }
    // Landscape Layout with listView
    public void landLayout()
    {
        mTitleText = (TextView) findViewById(R.id.listTitle);
        setTourList();
        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter listadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for(TourInfo info : mtours) {
            listadapter.add(info.getArtistName());
        }
        mListView.setAdapter(listadapter);
        Log.d(TAG, "this is landscape");

        // listView Implementation
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Position: " + position);
                TourInfo infoSelected = mtours.get(position);

                mTitleText.setText("Artist: " + infoSelected.getArtistName() +
                        "\nTour Name: " + infoSelected.getTourName() +
                        "\nGross: " + infoSelected.getTourGross());
            }
        });
    }

    // Portrait Layout with spinner
    public void portLayout()
    {
        mTitleText = (TextView) findViewById(R.id.listTitle);


        setTourList();
        // Attach Adapter to Spinner
        mSpinner = (Spinner) findViewById(R.id.Spinner);

        //spinList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.spinArray)));
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        for(TourInfo info : mtours) {
            adapter.add(info.getArtistName());
            Log.d(TAG, info.getArtistName());
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        addListeners();
        Log.d(TAG, "this is landscape");
    }

    // set TourInfo
    public void setTourList()
    {
        TourInfo stones1 = new TourInfo("The Rolling Stones", "Voodoo Lounge", "$449,5269,710");
        TourInfo stones2 = new TourInfo("The Rolling Stones", "Bridges to Babylon Tour", "$396,455,288");
        TourInfo pinkFloyd = new TourInfo("Pink Floyd", "The Division Bell Tour", "$394,788,505");
        TourInfo u2 = new TourInfo("U2", "Popmart Tour", "$252,212,913");
        TourInfo michaelJackson = new TourInfo("Michael Jackson", "History World Tour", "$246,518,544");
        // add TourInfo objects to mtours ArrayList
        mtours.add(stones1);
        mtours.add(stones2);
        mtours.add(pinkFloyd);
        mtours.add(u2);
        mtours.add(michaelJackson);

        //String test =   mtours.get(0).getArtistName();

        //Log.d(TAG, test + "");
    }

    // Add Listeners to Spinner
    public void addListeners() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                TourInfo spinSelected = mtours.get(position);
                if(intialrun) {
                    mTitleText.setText(getResources().getString(R.string.list_title));
                    intialrun = false;
                } else
                    mTitleText.setText("Artist: " + spinSelected.getArtistName() +
                            "\nTourName: " + spinSelected.getTourName() +
                            "\nGross: " + spinSelected.getTourGross());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTitleText.setText(getResources().getString(R.string.list_title));
            }
        });
    }
}
