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

public class Event extends AbstractFlexibleItem<Event.EventViewHolder>{

    private String name;
    private String desc;
    private String id;

    public Event(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event inItem = (Event) o;
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
    public Event.EventViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new EventViewHolder(view,adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, Event.EventViewHolder holder, int position, List payloads) {
        holder.title.setText(name);
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

