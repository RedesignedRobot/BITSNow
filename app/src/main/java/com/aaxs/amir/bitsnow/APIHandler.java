package com.aaxs.amir.bitsnow;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.IFlexible;

import static com.aaxs.amir.bitsnow.Constants.apiEndpoint;

/**
 * Created by NitroV17 on 10/13/2017.
 */

public class APIHandler extends AsyncTask<Void, Void, Void> {

    private ArrayList<EventModel> arrayList = new ArrayList<>();
    public List<IFlexible> list = new ArrayList<>();

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
                    arrayList.add(new EventModel(id, title, desc));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        for(EventModel em : arrayList){
            list.add(new EventAdapter(em.getId(),em.getTitle(),em.getDesc()));
        }
    }
}
