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

    // ArrayList Collections
    public ArrayList<TourInfo> mtours = new ArrayList<TourInfo>();

    // Initial run check bool for Spinner
    private boolean intialRun = true;


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
    public void orientationRun() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // execute the layout for Landscape Orientation
            landLayout();
        } else {
            // execute the layout for Portrait Orientation
            portLayout();
        }
    }

    // Landscape Layout with listView
    public void landLayout() {
        // populate mTours arrayList with custom data
        setTourList();
        // References
        mTitleText = (TextView) findViewById(R.id.listTitle);
        mListView = (ListView) findViewById(R.id.listView);
        // Create array adapter for listView
        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        // Add artist names to adapter
        addArtistName(listAdapter);
        // set adapter to listView
        mListView.setAdapter(listAdapter);
        Log.d(TAG, "this is landscape");

        // listView implementation
        setListView();
    }

    // Portrait Layout with spinner
    public void portLayout() {
        // set arrayList
        setTourList();
        // References
        mTitleText = (TextView) findViewById(R.id.listTitle);
        mSpinner = (Spinner) findViewById(R.id.Spinner);
        // create ArrayAdapter for Spinner
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Add artist names to adapter
        addArtistName(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Connect ListView and Adapter
        mSpinner.setAdapter(adapter);
        // add listeners to Spinner
        addListeners();
        Log.d(TAG, "this is landscape");
    }

    // set TourInfo
    public void setTourList() {
        // populate custom TourInfo objects
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
    }

    // Add Listeners to Spinner
    public void addListeners() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // check for initial run if true default text will display
                if (intialRun) {
                    populateText(getResources().getString(R.string.list_title));
                    intialRun = false;
                }
                else
                {
                    // populate text view
                    populate(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populateText(getResources().getString(R.string.list_title));
            }
        });
    }

    // set ListView
    public void setListView()
    {
        // ListView Implementation
        final ListView listView = (ListView) findViewById(R.id.listView);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Position: " + position);
                // populate detail textViews
                populate(position);
            }
         });
    }

    // Add Artist Name
    public void addArtistName(ArrayAdapter adapterName)
    {
        // add each artist name from mTours to adapter
        for (TourInfo info : mtours) {
            adapterName.add(info.getArtistName());
        }
    }

    // Populate detail view
    public void populate(int selPosition)
    {
        // create string based on the selected adapterView position
        TourInfo infoSelected = mtours.get(selPosition);
        String data = ("Artist: " + infoSelected.getArtistName() +
                "\nTour Name: " + infoSelected.getTourName() +
                "\nGross: " + infoSelected.getTourGross());
        //populate textView
        populateText(data);
    }

    // populate textViews
    public void populateText(String data)
    {
        // set titleText TextView
        mTitleText.setText(data);
    }

    // set initial run boolean
    //public void setIntialrun(boolean intialRun)
   // {

    //}

}
