package com.example.ls;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.rs.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SecretaryGeneralDetail extends Activity{
	public ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_org_sg_details);
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		Intent i = getIntent();
		String[] value = i.getStringArrayExtra("values");
		
		TextView tv = (TextView)findViewById(R.id.txtOrgSG);
		tv.setText(value[0]);
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/MTCORSVA.TTF");
		tv.setTypeface(typeFace);
		
		tv = (TextView)findViewById(R.id.txtOrgNameSG);
		
		tv.setTextSize(19);
		tv.setTextColor(Color.WHITE);
		tv.setTypeface(typeFace);
		
		TableRow tr = (TableRow)findViewById(R.id.orgheadrowSG);
		tr.setBackgroundColor(Color.rgb(190, 14, 14));
		
		LoadItems li = new LoadItems();
		imageView = (ImageView)findViewById(R.id.imgOrgImageSG);
		if(value[1].equals("g")){
			
			getSecretaryGeneralFeeds(li.getSecretaryGeneralURL());
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
	
	public void getSecretaryGeneralFeeds(String link){
		GetSecretaryGeneralDataTask task = new GetSecretaryGeneralDataTask();
		task.execute();
		Log.d("SecretaryGeneralFeeds", Thread.currentThread().getName());
	}
	private class GetSecretaryGeneralDataTask extends AsyncTask<String, Void, List<SecretaryGeneralRssItem>>{

		@Override
		protected List<SecretaryGeneralRssItem> doInBackground(String... urls) {
			try{
				SecretaryGeneralRssReader rssReader = new SecretaryGeneralRssReader(urls[0]);
				
				// Parse RSS, get items
				return rssReader.getItems();
			}catch(Exception e){
				Log.e("SecretaryGeneralRssItemError", e.getMessage());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(List<SecretaryGeneralRssItem> result) {
			if (result!=null){
				
			
			SecretaryGeneralRssItem ri = result.get(0);
	        String variable;
	        TextView tv = (TextView)findViewById(R.id.txtOrgFNameSG);
	        
	        variable = ri.getName();
	        if(variable!=null)
	        	tv.setText(variable);
	        
	        
	        tv = (TextView)findViewById(R.id.txtOrgEduQuaSG);
	        variable = ri.getEducationQual();
	        if(variable!=null)
	        	tv.setText(variable);
	        
	        
	        tv = (TextView)findViewById(R.id.txtOrgEmailSG);
	        variable = ri.getEmailID();
	        if(variable!=null)
	        	tv.setText(variable);
	        
	        tv = (TextView)findViewById(R.id.txtPAddressSG);
	        variable = ri.getPermanentAddress();
	        if(variable!=null){tv.setText(variable);}
	        
	        tv = (TextView)findViewById(R.id.txtPermPhoneSG);
	        variable = ri.getPermanentphone();
	        if(variable!=null){tv.setText(variable);}
	        
	        /*tv = (TextView)findViewById(R.id.txtOrgName);
	        variable = ri.getName();
	        tv.setText(variable);*/
	        
	        tv = (TextView)findViewById(R.id.txtOfficeAddressSG);
	        variable = ri.getOfficeAddress1();
	        if(variable!=null){tv.setText(variable);}
	        
	        tv = (TextView)findViewById(R.id.txtOfficePhoneSG);
	        variable = ri.getOfficephone1();
	        if(variable!=null){tv.setText(variable);}
	        
			GetXMLTask task = new GetXMLTask();
	        // Execute the task
	        task.execute(ri.getPictureUrl());
		}
			else{
				Toast.makeText(getBaseContext(), "fee empty",
						Toast.LENGTH_SHORT).show();
			}

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


