package com.aaxs.amir.bitsnow;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import static com.aaxs.amir.bitsnow.Constants.apiEndpoint;

/**
 * The main core class used by the app. All object/ui initialization happens here.4
 * It is the first java class to be loaded by the app.
 */


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    protected ArrayList<EventModel> arrayList = new ArrayList<>();
    protected List<IFlexible> list = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FlexibleAdapter<IFlexible> adapter;
    private boolean isAllowed=true;

    /**
     * onCreate method where the view is inflated with the layout xml file. Views are
     * are also initiated and are linked to the view in the layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new APIHandler().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        new APIHandler().execute();
    }

    /**
     * Method used for populating the myItems list with the data from the api call and
     * for setting the adapter of the recyclerView.
     */
    public void init(){
        List<IFlexible> myItems = list;
        adapter = new FlexibleAdapter<>(myItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /**
     * Class used for making the API call as a separate thread from the main (UI) thread.
     */
    class APIHandler extends AsyncTask<Void, Void, Void> {

        /**
         * Making the call via the HttpHandler class. Creating and assigning parms of the
         * JsonObject.
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(apiEndpoint);
            try {
                if (jsonStr != null) {
                    JSONArray items = new JSONArray(jsonStr);
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject c = items.getJSONObject(i);
                        String id = c.getString("id");
                        String eTitle = c.getString("eTitle");
                        String eDesc = c.getString("eDesc");
                        String cName = c.getString("cName");
                        String cId = c.getString("cId");
                        String eStartDate = c.getString("eStartDate");
                        if(eStartDate.isEmpty())
                            eStartDate="NULL";
                        String eEndDate = c.getString("eEndDate");
                        if(eEndDate.isEmpty())
                            eStartDate="NULL";
                        filterData(new EventModel(id, eTitle, eDesc, cName, cId, eStartDate,
                                eEndDate));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * Initial filetr to prevent duplicate elements to be added to the list. More filters
         * can be added later.
         * @param eventModel
         */
        private void filterData(EventModel eventModel) {    // TODO: 10/13/2017 ADD MORE PARAMS

            for(EventModel em : arrayList){
                if (em.getId() == eventModel.getId()){
                    isAllowed=false;
                }
            }
            if(isAllowed){
                arrayList.add(eventModel);
            }

        }

        /**
         * In this method, the json object's parms are used to create the EventModel object after
         * filtering. After the object is made, the init() method is called to refresh and reset the
         * recyclerview adapter
         * @param result
         */
        @Override
        protected void onPostExecute(Void result) {
            list.clear();
            for (EventModel em : arrayList) {
                list.add(new EventAdapter(em.getId(), em.geteTitle(), em.geteDesc(), em.getcName(),
                        em.getcId(), em.geteStartDate(), em.geteEndDate()));
            }
            init();
        }

    }
}
