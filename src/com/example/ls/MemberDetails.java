package com.example.ls;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.example.loksabha.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MemberDetails extends Activity {

	ImageView  imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_member_details);
		Intent i = getIntent();
		String MP_Code = i.getStringExtra("MP_Code");
		LoadItems li = new LoadItems();
		String url = li.GetMemBerDetailURL(MP_Code);
		GetFeeds(url);
		String pictureurl = "http://164.100.47.132/mpimage/photo/"+MP_Code+".jpg";
		ImageView iv = (ImageView)findViewById(R.id.imgMemImage);
		imageView = iv;
		GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { pictureurl });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member_details, menu);
		return true;
	}
	
	public void GetFeeds(String link)
	{
		GetMDRSSDataTask task = new GetMDRSSDataTask();
        
		task.execute(link);
         
        // Debug the thread name
        Log.d("RssReader", Thread.currentThread().getName());
	}
	
	
	private class GetMDRSSDataTask extends AsyncTask<String, Void, String[] > {
	    @Override
	    protected String[] doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("MDRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            MDRssReader rssReader = new MDRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(String[] result) {
	        // Get a ListView from main view
	        //ListView lv = (ListView)findViewById(R.id.listFeeds);
	    	TextView tv = (TextView)findViewById(R.id.txtName);
	    	tv.setText(result[0]);
	    	tv = (TextView)findViewById(R.id.txtState);
	    	tv.setText(result[1]);
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
