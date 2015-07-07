package com.example.ls;

import java.util.ArrayList;
import java.util.HashMap;
 

import com.example.rs.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    @Override
	public int getCount() {
        return data.size();
    }
 
    @Override
	public Object getItem(int position) {
        return position;
    }
 
    @Override
	public long getItemId(int position) {
        return position;
    }
 
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.todaysbday, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // Member's name
        TextView constituencyAndState = (TextView)vi.findViewById(R.id.ConstituencyAndState); // consituency
        TextView DOB = (TextView)vi.findViewById(R.id.dob);  //Member's DOB
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        TextView email = (TextView)vi.findViewById(R.id.emailID);
        TextView partyName = (TextView)vi.findViewById(R.id.partyName); 
        
        
 
        HashMap<String, String> MemberInfoHashmap = new HashMap<String, String>();
        MemberInfoHashmap = data.get(position);
 
        // Setting all values in listview
        title.setText(MemberInfoHashmap.get(TodaysBirthday.KEY_TITLE));
        constituencyAndState.setText(MemberInfoHashmap.get(TodaysBirthday.KEY_CONSTITUENCY));
        imageLoader.DisplayImage(MemberInfoHashmap.get(TodaysBirthday.KEY_THUMB_URL), thumb_image);
        DOB.setText(MemberInfoHashmap.get(TodaysBirthday.KEY_DOB));
        email.setText(MemberInfoHashmap.get(TodaysBirthday.KEY_EMAIL));
        partyName.setText(MemberInfoHashmap.get(TodaysBirthday.KEY_PARTYNAME));
        return vi;
    }
}
