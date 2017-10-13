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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    protected ArrayList<EventModel> arrayList = new ArrayList<>();
    protected List<IFlexible> list = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FlexibleAdapter<IFlexible> adapter;
    private boolean isAllowed=true;

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

    public void init(){
        List<IFlexible> myItems = list;
        adapter = new FlexibleAdapter<>(myItems);
        recyclerView.setAdapter(adapter);
    }


    class APIHandler extends AsyncTask<Void, Void, Void> {

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
                        String title = c.getString("name");    //title
                        String desc = c.getString("title");      //desc
                        insertData(new EventModel(id, title, desc));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private void insertData(EventModel eventModel) {

            for(EventModel em : arrayList){
                if (em.getId() == eventModel.getId()){
                    isAllowed=false;
                }
            }
            if(isAllowed){
                arrayList.add(eventModel);
            }

        }

        @Override
        protected void onPostExecute(Void result) {
            list.clear();
            for (EventModel em : arrayList) {
                list.add(new EventAdapter(em.getId(), em.getTitle(), em.getDesc()));
            }
            init();
        }
    }
}
