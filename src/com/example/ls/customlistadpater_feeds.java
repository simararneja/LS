package com.example.ls;

import java.util.List;

import com.example.loksabha.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class customlistadpater_feeds extends ArrayAdapter {

	private Context mContext;
	private int id;
	private List<?> items;

	public customlistadpater_feeds(Context context, int textViewResourceId,
			List<?> list) {
		super(context, textViewResourceId, list);
		mContext = context;
		id = textViewResourceId;
		items = list;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View mView = v;
		if (mView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mView = vi.inflate(id, null);
		}

		TextView text = (TextView) mView.findViewById(R.id.txtList);

		if (items.get(position) != null) {
			text.setText(items.get(position).toString());

			text.setTextColor(Color.rgb(54, 163, 54));
			text.setTextSize(20);
			text.setBackgroundColor(Color.TRANSPARENT);
			

		}
		

		return mView;
	}

}