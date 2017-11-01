package com.aaxs.amir.bitsnow;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by NitroV17 on 10/13/2017.
 */

public class EventAdapter extends AbstractFlexibleItem<EventAdapter.EventViewHolder>{

    private String id;
    private String eTitle;
    private String eDesc;
    private String cName;
    private String cId;
    private String eStartDate;
    private String eEndDate;


    public EventAdapter(String id, String eTitle, String eDesc) {
        this.id = id;
        this.eTitle = eTitle;
        this.eDesc = eDesc;
    }

    public EventAdapter(String id, String eTitle, String eDesc, String cName, String cId, String eStartDate, String eEndDate) {
        this.id = id;
        this.eTitle = eTitle;
        this.eDesc = eDesc;
        this.cName = cName;
        this.cId = cId;
        this.eStartDate = eStartDate;
        this.eEndDate = eEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EventAdapter) {
            EventAdapter inItem = (EventAdapter) o;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.event_item;
    }

    @Override
    public EventAdapter.EventViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new EventViewHolder(view,adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, EventAdapter.EventViewHolder holder, int position, List payloads) {
        // TODO: 10/13/2017 USE THE ID PARM FOR DISP?
        holder.eventTitle.setText(eTitle);
        holder.eventDesc.setText(eDesc);
        holder.clubName.setText(cName);
        holder.clubId.setText(cId);
        holder.eventStartDate.setText(eStartDate);
        holder.eventEndDate.setText(eEndDate);

    }

    class EventViewHolder extends FlexibleViewHolder{

        public TextView eventTitle;
        public TextView eventDesc;
        public TextView clubName;
        public TextView clubId;
        public TextView eventStartDate;
        public TextView eventEndDate;

        public EventViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            eventTitle =(TextView) view.findViewById(R.id.etitle);
            eventDesc =(TextView) view.findViewById(R.id.edesc);
            clubName =(TextView) view.findViewById(R.id.cname);
            clubId =(TextView) view.findViewById(R.id.cid);
            eventStartDate =(TextView) view.findViewById(R.id.estartdate);
            eventEndDate =(TextView) view.findViewById(R.id.eenddate);
        }
    }

}

