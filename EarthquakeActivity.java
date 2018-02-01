package com.nikhilkumar.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;




public class EarthquakeActivity extends AppCompatActivity {
private EarthquakeAdapter adapter;
    private ImageView mEmptyStateImageView;

    private static final String USGS_REQUEST_URL =
                    "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=3&limit=500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        mEmptyStateImageView = (ImageView) findViewById(R.id.empty_view);



       adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        ListView listView = (ListView) findViewById(R.id.quake_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);


                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

        listView.setEmptyView(mEmptyStateImageView);



    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;

        }

       @Override
        protected void onPostExecute(ArrayList<Earthquake> data) {
           View loadingIndicator = findViewById(R.id.loading_indicator);
           loadingIndicator.setVisibility(View.GONE);


           mEmptyStateImageView.setImageResource(R.drawable.error_image);

            // Clear the adapter of previous earthquake data
         adapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
           if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }


        }
    }

}














