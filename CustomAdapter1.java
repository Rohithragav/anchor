package com.developer.rohithragav.anchor;

/**
 * Created by ROHITH RAGAV on 9/8/2017.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter1 extends BaseAdapter {

    private ArrayList<Feeds> obj = new ArrayList<Feeds>();

    public CustomAdapter1(ArrayList<Feeds> d){
        this.obj = d;
    }

    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public Object getItem(int position) {
        return obj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            LayoutInflater inf = LayoutInflater.from(parent.getContext());
            convertView = inf.inflate(R.layout.singlefeed,parent,false);
        }
        Feeds feed = obj.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.ftitle);
        TextView date = (TextView) convertView.findViewById(R.id.fdate);
        TextView time = (TextView) convertView.findViewById(R.id.ftime);

        time.setText(feed.getTime());
        date.setText(feed.getDate());
        title.setText(feed.getTitle());
        return convertView;
    }
}
