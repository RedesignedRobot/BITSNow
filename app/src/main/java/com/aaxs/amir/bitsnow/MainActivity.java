package com.aaxs.amir.bitsnow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.davidea.flexibleadapter.items.IFlexible;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    ListAdapter adapter;


    SwipeRefreshLayout swipeRefreshLayout;

    // URL to get contacts JSON
    private static String url = Constants.URL;

    ArrayList<HashMap<String, String>> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsList = new ArrayList<>();
        List<IFlexible> myItems = itemsList;
        //swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);// TODO: 10/13/2017 CHECK FOR REFRESH ON SWIPE WITH FV

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemsList.clear();
                new GetContacts().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //lv = (ListView) findViewById(R.id.list);  // TODO: 10/13/2017 LV TO FV

        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONArray items = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject c = items.getJSONObject(i);

                        String name = c.getString("name");
                        String title = c.getString("title");

                        // tmp hash map for single contact
                        HashMap<String, String> item = new HashMap<>();

                        // adding each child node to HashMap key => value
                        item.put("name", name);
                        item.put("title",title);

                        // adding contact to contact list
                        itemsList.add(item);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            /*
            adapter = new SimpleAdapter(
                    MainActivity.this, itemsList, R.layout.list_item, new String[]{"name", "title"}, new int[]{R.id.name, R.id.title});

            lv.setAdapter(adapter);
            */  // TODO: 10/13/2017 UPDATE LISTVIEW TO FLEXVIEW
        }

    }
}
