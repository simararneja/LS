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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class TabMemberDetails extends Activity {

	public TabMemberDetails local;
	public ImageView imageView;
	private float lastX;
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_member_details);
		tabHost = (TabHost) findViewById(R.id.tabHost);
		tabHost.setup();
		if (CompatibilityManager.isIceCreamSandwich()) {
			BackCompatibility();
		}

		TabSpec spec1 = tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Bio Data");

		TabSpec spec2 = tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Debates");
		spec2.setContent(R.id.tab2);

		TabSpec spec3 = tabHost.newTabSpec("Tab 3");
		spec3.setIndicator("Questions");
		spec3.setContent(R.id.tab3);

		TabSpec spec4 = tabHost.newTabSpec("Tab 4");
		spec4.setIndicator("Govt Bills");
		spec4.setContent(R.id.tab4);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
		tabHost.addTab(spec4);

		tabHost.getChildAt(0).setBackgroundColor(Color.rgb(247, 195, 0));
		local = this;
		LoadItems li = new LoadItems();
		Intent i = getIntent();
		String mp_code = i.getStringExtra("MP_Code");
		TableRow tr = (TableRow) findViewById(R.id.headrow);
		tr.setBackgroundColor(Color.rgb(44, 140, 15));
		TextView textname = (TextView) findViewById(R.id.txtMemName);
		textname.setTextColor(Color.WHITE);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/MTCORSVA.TTF");
		textname.setTypeface(typeFace);
		String pictureurl = li.getMemberPictureURL() + mp_code + ".jpg";
		ImageView iv = (ImageView) findViewById(R.id.imgMemImage);
		imageView = iv;
		GetXMLTask task = new GetXMLTask();
		// Execute the task
		task.execute(new String[] { pictureurl });

		String BioDataURL = li.GetMemBioDataURL(mp_code);
		GetBioDataFeeds(BioDataURL);
		String QuestionURL = li.getMemQuestionsURL(mp_code);
		GetQuestionFeeds(QuestionURL);
		getDebateFeeds(li.getDebateUrl(mp_code));
		getGovtBillsFeeds(li.getGovtBillsUrl(mp_code));

	}

	@Override
	public boolean onTouchEvent(MotionEvent touchevent) {
		switch (touchevent.getAction()) {
		// when user first touches the screen to swap
		case MotionEvent.ACTION_DOWN: {
			lastX = touchevent.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float currentX = touchevent.getX();

			// if left to right swipe on screen
			if (lastX < currentX) {

				switchTabs(false);
			}

			// if right to left swipe on screen
			if (lastX > currentX) {
				switchTabs(true);
			}

			break;
		}
		}
		return false;
	}

	private void switchTabs(boolean direction) {
		if (direction) // true = move left
		{
			if (tabHost.getCurrentTab() == 0)
				tabHost.setCurrentTab(tabHost.getTabWidget().getTabCount() - 1);
			else
				tabHost.setCurrentTab(tabHost.getCurrentTab() - 1);
		} else
		// move right
		{
			if (tabHost.getCurrentTab() != (tabHost.getTabWidget()
					.getTabCount() - 1))
				tabHost.setCurrentTab(tabHost.getCurrentTab() + 1);
			else
				tabHost.setCurrentTab(0);
		}

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void BackCompatibility() {
		ActionBar abc = getActionBar();
		abc.setHomeButtonEnabled(true);
		abc.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_member_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		onBackPressed();
		return true;
	}

	private void getGovtBillsFeeds(String govtBillsUrl) {
		GetGovtBillDataTask task = new GetGovtBillDataTask();
		task.execute(govtBillsUrl);
		Log.d("GovtBillsReader", Thread.currentThread().getName());

	}

	private class GetGovtBillDataTask extends
			AsyncTask<String, Void, List<GovtBillRssItem>> {
		@Override
		protected List<GovtBillRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("GovtBillsReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				GovtBillRssReader rssReader = new GovtBillRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<GovtBillRssItem> result) {

			if (result != null) {

				// Get a ListView from main view
				ListView lv = (ListView) findViewById(R.id.lstgovtbills);

				// Create a list adapter
				final ArrayAdapter<GovtBillRssItem> adapter = new ArrayAdapter<GovtBillRssItem>(
						local, android.R.layout.simple_list_item_1, result);
				// Set list adapter for the ListView
				if (adapter.getItem(0).getClass() != null) {
					lv.setAdapter(adapter);

					// Set list view item click listener
					/*lv.setOnItemClickListener(new AssuListListener(result,
							local));*/
					EditText inputSearch = (EditText) findViewById(R.id.txtSearchAssu);
					inputSearch.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence cs, int arg1,
								int arg2, int arg3) {
							// When user changed the Text
							adapter.getFilter().filter(cs);
						}

						@Override
						public void beforeTextChanged(CharSequence arg0,
								int arg1, int arg2, int arg3) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable arg0) {
							// TODO Auto-generated method stub
						}
					});
				}
			}

		}
	}

	public void GetQuestionFeeds(String link) {
		GetMQLRSSDataTask task = new GetMQLRSSDataTask();

		task.execute(link);
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void getDebateFeeds(String link) {
		GetDebateRssDataTask task = new GetDebateRssDataTask();
		task.execute(link);
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void GetBioDataFeeds(String link) {
		GetMemberBioDataRSSDataTask task = new GetMemberBioDataRSSDataTask();
		task.execute(link);
		Log.d("MemberBioDataFeeds", Thread.currentThread().getName());
	}

	private class GetMemberBioDataRSSDataTask extends
			AsyncTask<String, Void, List<MBRssItem>> {
		@Override
		protected List<MBRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("MemberBioDataRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				MemberBioDataRssReader rssReader = new MemberBioDataRssReader(
						urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("MemberBioDataRssReaderError", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<MBRssItem> result) {
			if (result != null) {

				MBRssItem ri = result.get(0);
				String variable;
				TextView tv = (TextView) findViewById(R.id.txtMemDOB);
				variable = ri.getDOB();
				if (variable != null) {
					tv.setText(variable);
				}
				/**/tv = (TextView) findViewById(R.id.txtMemEduQua);
				variable = ri.getEducatQualification();
				if (variable != null) {
					tv.setText(variable);
				}/**/
				tv = (TextView) findViewById(R.id.txtMemEmail);
				variable = ri.getEmailID();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemFName);
				variable = ri.getFatherName();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemMName);
				variable = ri.getMotherName();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemName);
				variable = ri.getName();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemParty);
				variable = ri.getPartyName();
				if (variable != null) {
					tv.setText(variable);
				}
				/**/tv = (TextView) findViewById(R.id.txtMemPosHeld);
				variable = ri.getPositionHeld();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemProfession);
				variable = ri.getProfession();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemSocialAct);
				variable = ri.getSocialActivity();
				if (variable != null) {
					tv.setText(variable);
				}/**/
				tv = (TextView) findViewById(R.id.txtMemSpouseName);
				variable = ri.getSpouseName();
				if (variable != null) {
					tv.setText(variable);
				}
				tv = (TextView) findViewById(R.id.txtMemState);
				variable = ri.getStateName();
				if (variable != null) {
					tv.setText(variable);
				}
			}

		}
	}

	private class GetMQLRSSDataTask extends
			AsyncTask<String, Void, List<MQLRssItem>> {
		@Override
		protected List<MQLRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("LoMRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				MQLRssReader rssReader = new MQLRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<MQLRssItem> result) {

			if (result != null) {

				// Get a ListView from main view
				ListView lv = (ListView) findViewById(R.id.lstQuestions);

				// Create a list adapter
				final ArrayAdapter<MQLRssItem> adapter = new ArrayAdapter<MQLRssItem>(
						local, android.R.layout.simple_list_item_1, result);
				// Set list adapter for the ListView
				if (adapter.getItem(0).getqid() != null) {
					lv.setAdapter(adapter);

					// Set list view item click listener
					lv.setOnItemClickListener(new MQLListListener(result, local));
					EditText inputSearch = (EditText) findViewById(R.id.txtSearchQues);
					inputSearch.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence cs, int arg1,
								int arg2, int arg3) {
							// When user changed the Text
							adapter.getFilter().filter(cs);
						}

						@Override
						public void beforeTextChanged(CharSequence arg0,
								int arg1, int arg2, int arg3) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable arg0) {
							// TODO Auto-generated method stub
						}
					});
				}
			}
		}
	}

	private class GetDebateRssDataTask extends
			AsyncTask<String, Void, List<DebateRssItem>> {
		@Override
		protected List<DebateRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("LoMRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				DebateRssReader rssReader = new DebateRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<DebateRssItem> result) {

			if (result != null) {

				// Get a ListView from main view
				ListView lv = (ListView) findViewById(R.id.lstAssurances);

				// Create a list adapter
				final ArrayAdapter<DebateRssItem> adapter = new ArrayAdapter<DebateRssItem>(
						local, android.R.layout.simple_list_item_1, result);
				// Set list adapter for the ListView
				if (adapter.getItem(0).getDebateid() != null) {
					lv.setAdapter(adapter);

					// Set list view item click listener
					lv.setOnItemClickListener(new AssuListListener(result,
							local));
					EditText inputSearch = (EditText) findViewById(R.id.txtSearchAssu);
					inputSearch.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence cs, int arg1,
								int arg2, int arg3) {
							// When user changed the Text
							adapter.getFilter().filter(cs);
						}

						@Override
						public void beforeTextChanged(CharSequence arg0,
								int arg1, int arg2, int arg3) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable arg0) {
							// TODO Auto-generated method stub
						}
					});
				}
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
				bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
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
