package com.project.bushop.bushop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class PersonListAdapter extends ArrayAdapter<Person>{

    private static final String TAG="personListAdapter";
    private Context mContext;
int mResource;

    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String bus_no=getItem(position).getBus_no();
        String source=getItem(position).getSource();
        String destination=getItem(position).getDestination();
        String time=getItem(position).getTime();


        Person person=new Person(bus_no,source,destination,time);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView busno=(TextView)convertView.findViewById(R.id.bus_no);
        TextView src=(TextView)convertView.findViewById(R.id.source);
        TextView dest=(TextView)convertView.findViewById(R.id.destination);
        TextView tme=(TextView)convertView.findViewById(R.id.time);

        busno.setText(bus_no);
        src.setText(source);
        dest.setText(destination);
        tme.setText(time);

        return convertView;
    }
}
