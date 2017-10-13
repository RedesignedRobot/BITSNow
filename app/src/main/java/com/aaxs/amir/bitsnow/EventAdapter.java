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
    private String title;
    private String desc;

    public EventAdapter(String id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
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
        holder.title.setText(title);
        holder.desc.setText(desc);

    }

    class EventViewHolder extends FlexibleViewHolder{

        public TextView title;
        public TextView desc;

        public EventViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            title =(TextView) view.findViewById(R.id.title);
            desc =(TextView) view.findViewById(R.id.desc);
        }
    }

}

