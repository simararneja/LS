package com.example.ls;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.loksabha.R;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class OrgDesigDetail extends Activity {

	public String[] value;
	public ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_org_desig_detail);
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		Intent i = getIntent();
		value = i.getStringArrayExtra("values");
		TextView tv = (TextView)findViewById(R.id.txtOrgDesig);
		tv.setText(value[0]);
		//tv.setTextColor(color.white);
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/MTCORSVA.TTF");
		tv.setTypeface(typeFace);
		tv = (TextView)findViewById(R.id.txtOrgName);
		//tv.setTextColor(color.white);
		tv.setTextSize(19);
		tv.setTextColor(Color.WHITE);
		tv.setTypeface(typeFace);
		TableRow tr = (TableRow)findViewById(R.id.orgheadrow);
		tr.setBackgroundColor(Color.rgb(190, 14, 14));
		LoadItems li = new LoadItems();
		imageView = (ImageView)findViewById(R.id.imgOrgImage);
		if(value[1].equals("s"))
		{
			getOrgFeeds(li.getSpeakerUrl());
		}
		else if(value[1].equals("d"))
		{
			getOrgFeeds(li.getDeputySpeakerURL());
		}
		else{
			getOrgFeeds(li.getSecretaryGeneralURL());
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void BackCompatibility()
	{
		ActionBar abc = getActionBar();
		abc.setHomeButtonEnabled(true);
		abc.setDisplayHomeAsUpEnabled(true);
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {       
		onBackPressed();
	    return true;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.org_desig_detail, menu);
		return true;
	}
	
	
	public void getOrgFeeds(String link)
	{
		GetOrgDesigRSSDataTask task = new GetOrgDesigRSSDataTask();
		task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	private class GetOrgDesigRSSDataTask extends AsyncTask<String, Void, List<OrgDesigRssItem> > {
	    @Override
	    protected List<OrgDesigRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("LoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	        	OrgDesigRssReader rssReader = new OrgDesigRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<OrgDesigRssItem> result) {
	    	
	    	if(value[1].equals("g")){
	    		OrgDesigRssItem ri = result.get(0);
		        String variable;
		         TextView tv = (TextView)findViewById(R.id.txtOrgEduQua);
		        variable = ri.getEducatQualification();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgEmail);
		        variable = ri.getEmailID();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgFName);
		        variable = ri.getFatherName();
		        if(variable!=null){tv.setText(variable);}
		        tv = (TextView)findViewById(R.id.txtOrgSGNO);
		        tv.setText("Office Address");
		        
		        tv = (TextView)findViewById(R.id.txtOrgMName);
		        variable = ri.getMotherName();
		        if(variable!=null){tv.setText(variable);}
		        tv = (TextView)findViewById(R.id.txtOrgMOP);
		        tv.setText("Office Phone #");
		        
		        tv = (TextView)findViewById(R.id.txtOrgName);
		        variable = ri.getName();
		        tv.setText(variable);
		        
		        tv = (TextView)findViewById(R.id.txtOrgPosHeld);
		        variable = ri.getPositionHeld();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgDOB);
		        variable = ri.getPermanentAddress();
		        if(variable!=null){tv.setText(variable);}
		        tv = (TextView)findViewById(R.id.txtOrgSGPhoNO);
		        tv.setText("Permanent Phone #");
		        
		        tv = (TextView)findViewById(R.id.txtOrgSGPAddress);
		        variable = ri.getPermanentphone();
		        if(variable!=null){tv.setText(variable);}
		        tv = (TextView)findViewById(R.id.txtOrgSGPhoNO);
		        tv.setText("Permanent Phone #");
		        String pictureurl = ri.getPictureUrl();
				GetXMLTask task = new GetXMLTask();
		        // Execute the task
		        task.execute(new String[] { "http://164.100.47.132/lssorgchart/photos/anoop.jpg" });
		        
		        
	    	}
	    	else{
	    		OrgDesigRssItem ri = result.get(0);
		        String variable;
		        
		        TextView tv = (TextView)findViewById(R.id.txtOrgDOB);
		        variable = ri.getDOB();
		        if(variable!=null){tv.setText(variable);}
		        
		        /**/tv = (TextView)findViewById(R.id.txtOrgEduQua);
		        variable = ri.getEducatQualification();
		        if(variable!=null){tv.setText(variable);}/**/
		        
		        tv = (TextView)findViewById(R.id.txtOrgEmail);
		        variable = ri.getEmailID();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgFName);
		        variable = ri.getFatherName();
		        if(variable!=null){tv.setText(variable);} 
		        
		        tv = (TextView)findViewById(R.id.txtOrgMName);
		        variable = ri.getMotherName();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgName);
		        variable = ri.getName();
		        tv.setText(variable);
		        
		        /**/tv = (TextView)findViewById(R.id.txtOrgPosHeld);
		        variable = ri.getPositionHeld();
		        if(variable!=null){tv.setText(variable);}
		        
		        tv = (TextView)findViewById(R.id.txtOrgSpouseName);
		        variable = ri.getSpouseName();
		        if(variable!=null){tv.setText(variable);}
		        
		        String pictureurl = ri.getPictureUrl();
				GetXMLTask task = new GetXMLTask();
		        // Execute the task
		        task.execute(new String[] { "http://164.100.47.132/mpimage/photo/3205.jpg" });
		        
	    	}

/*	        OrgDesigRssItem ri = result.get(0);
	        String variable;
	        TextView tv = (TextView)findViewById(R.id.txtOrgDOB);
	        variable = ri.getDOB();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgEduQua);
	        variable = ri.getEducatQualification();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgEmail);
	        variable = ri.getEmailID();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgFName);
	        variable = ri.getFatherName();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgMName);
	        variable = ri.getMotherName();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgName);
	        variable = ri.getName();
	        tv.setText(variable);
	        tv = (TextView)findViewById(R.id.txtOrgPosHeld);
	        variable = ri.getPositionHeld();
	        if(variable!=null){tv.setText(variable);}
	        tv = (TextView)findViewById(R.id.txtOrgSpouseName);
	        variable = ri.getSpouseName();
	        if(variable!=null){tv.setText(variable);}
	        String pictureurl = ri.getPictureUrl();
			GetXMLTask task = new GetXMLTask();
	        // Execute the task
	        task.execute(new String[] { "http://164.100.47.132/lssorgchart/photos/anoop.jpg" });*/
	        
	    }
	}
	private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }
 
        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
 
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
 
            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }
 
        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
 
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
}
