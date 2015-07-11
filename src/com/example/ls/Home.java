package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import com.example.ls.CustomMenuItem;
import com.example.ls.CustomMenu.OnMenuItemSelectedListener;
import com.example.ls.SimpleGestureFilter.SimpleGestureListener;
import com.example.rs.R;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener,
		OnMenuItemSelectedListener, SimpleGestureListener {

	private CustomMenu homescreeMenu;
	public static final int MENU_ITEM_1 = 1;
	public static final int MENU_ITEM_2 = 2;
	public static final int MENU_ITEM_3 = 3;
	public static final int MENU_ITEM_4 = 4;
	private int count = 1;
	private SimpleGestureFilter detector;
	public Home local;
	public String Pagelabel;
	ImageButton menu_button;
	Button p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
	TextView paragraph, tv;
	LinearLayout content, menu;
	LinearLayout.LayoutParams contentParams;
	TranslateAnimation slide;
	int marginX, animateFromX, animateToX = 0;
	boolean menuOpen = false;
	String[] paragraphs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		detector = new SimpleGestureFilter(this, this);
		local = this;
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		homescreeMenu = new CustomMenu(this, this, getLayoutInflater());
		homescreeMenu.setHideOnSelect(true);
		homescreeMenu.setItemsPerLineInPortraitOrientation(4);
		homescreeMenu.setItemsPerLineInLandscapeOrientation(8);

		// load the menu items
		loadMenuItems();

		menu = (LinearLayout) findViewById(R.id.menu);

		content = (LinearLayout) findViewById(R.id.content);
		contentParams = (LinearLayout.LayoutParams) content.getLayoutParams();
		contentParams.width = getWindowManager().getDefaultDisplay().getWidth(); // Ensures
																					// constant
																					// width
																					// of
																					// content
																					// during
																					// menu
																					// sliding
		contentParams.leftMargin = -(menu.getLayoutParams().width); // Position
																	// the
																	// content
																	// at the
																	// start of
																	// the
																	// screen
		content.setLayoutParams(contentParams);

		menu_button = (ImageButton) findViewById(R.id.menu_button);
		menu_button.setOnClickListener(this);
		ListView lv = (ListView) findViewById(R.id.listView1);
		LoadItems li = new LoadItems();
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, li.GetItemArray());
		lv.setAdapter(aa);
		lv.setClickable(true);
		Toast.makeText(this, "Welcome to the Lok Sabha Business App",
				Toast.LENGTH_SHORT).show();

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				ReadFeed rf = new ReadFeed(position);
				/*
				 * Toast.makeText(Home.this, "Loading Feeds..Please wait",
				 * Toast.LENGTH_SHORT).show();
				 */

				final LoadItems liitems = new LoadItems();
				TextView tv = (TextView) findViewById(R.id.product_label);
				// tv.setBackgroundColor(Color.LTGRAY);
				String page = liitems.links[position];
				String url = liitems.urlStart + page;
				String Type = liitems.type[position];
				if (Type == "1") {
					HideMenu();
					// We create an Intent which is going to display data
					Intent i = new Intent(Intent.ACTION_VIEW);
					// We have to set data for our new Intent
					i.setData(Uri.parse(url));
					// And start activity with our Intent
					local.startActivity(i);
				} else if ((url.contains("bulletinpart_II.aspx"))
						|| (url.contains("bulletinpart_I.aspx"))
						|| (url.contains("DailySynopsis.aspx"))) {
					/*
					 * //tv.setText(liitems.items[position]); HideMenu();
					 */
					HideMenu();
					GetFeeds(url);
				} else if (url.contains("businessList.aspx")) {
					// tv.setText("List of Business");
					HideMenu();
					GetLOBFeeds(url);
				} else if (url.contains("Notifications.aspx")) {
					// tv.setText(liitems.items[position]);
					HideMenu();
					GetNotiFeeds(url);
				} else if (Type == "2") {
					HideMenu();
					Intent ql = new Intent(local, QuestionList.class);
					local.startActivity(ql);
				} else if (Type == "4") {
					HideMenu();
					Intent todayBirthday = new Intent(local,
							TodaysBirthday.class);
					local.startActivity(todayBirthday);
				}

				else if (url.contains("organisation")) {
					// tv.setText(liitems.items[position]);
					HideMenu();
					ListView lv = (ListView) findViewById(R.id.listFeeds);
					ArrayAdapter<String> aa = new ArrayAdapter<String>(local,
							android.R.layout.simple_list_item_1,
							liitems.organisationDesignations);
					lv.setAdapter(aa);
					lv.setClickable(true);
					lv.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View v,
								int position, long arg3) {
							String memTypeVal = liitems.orgDesignationsValue[position];

							if (memTypeVal == "o") { // "o" for Secretariat
								Intent i = new Intent(v.getContext(),
										OrganisationASJSDirector.class);
								local.startActivity(i);
							} else if (memTypeVal == "s" || memTypeVal == "d") {
								Intent i = new Intent(v.getContext(),
										OrgDesigDetail.class);
								String[] values = null;

								if (memTypeVal == "s")
									values = new String[] {
											"Speaker - Lok Sabha", memTypeVal };
								else if (memTypeVal == "d")
									values = new String[] {
											"Deputy Speaker - Lok Sabha",
											memTypeVal };

								i.putExtra("values", values);
								local.startActivity(i);
							} else {
								String[] values = null;
								Intent intent = new Intent(v.getContext(),
										SecretaryGeneralDetail.class);
								values = new String[] {
										"Secratary General - Lok Sabha",
										memTypeVal };
								intent.putExtra("values", values);
								local.startActivity(intent);

							}
						}
					});

				} else if (url.contains("com")) {
					Intent i = new Intent(local, LokSabhaMembers.class);
					String com[] = new String[] { "comMain" };
					i.putExtra("values", com);
					local.startActivity(i);
				} else {
					HideMenu();
					GetFeeds(url);

				}
			}
		});

	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		homescreeMenu.show(findViewById(R.id.any_old_widget));
	}

	public void showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(local).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);
		alertDialog.show();
	}

	// changing
	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			str = "Swipe Right";
			ShowMenu();
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";
			HideMenu();
			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			str = "Swipe Up";
			break;

		}
		// Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		// Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		if (contentParams.leftMargin == -(menu.getLayoutParams().width)) {
			// Menu is hidden (slide out parameters)

			animateFromX = 0;
			animateToX = (menu.getLayoutParams().width);
			marginX = 0;
			menuOpen = true;
		} else { // Menu is visible (slide in parameter)
			animateFromX = 0;
			animateToX = -(menu.getLayoutParams().width);
			marginX = -(menu.getLayoutParams().width);
			menuOpen = false;
		}
		slideMenuIn(animateFromX, animateToX, marginX);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (menuOpen) {
				slideMenuIn(0, -(menu.getLayoutParams().width),
						-(menu.getLayoutParams().width)); // Pass slide in
															// paramters
				menuOpen = false;
				return true;
			}

		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			doMenu();
			return true; // always eat it!
		}
		return super.onKeyDown(keyCode, keyEvent);
	}

	/**
	 * Load up our menu on Home screen.
	 */
	private void loadMenuItems() {
		// This is kind of a tedious way to load up the menu items.
		// Am sure there is room for improvement.
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi = new CustomMenuItem();
		cmi.setCaption("Members");
		cmi.setImageResourceId(R.drawable.icon1);
		cmi.setId(MENU_ITEM_1);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Live TV");
		cmi.setImageResourceId(R.drawable.icon2);
		cmi.setId(MENU_ITEM_2);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Websites");
		cmi.setImageResourceId(R.drawable.icon3);
		cmi.setId(MENU_ITEM_3);
		menuItems.add(cmi);
		cmi = new CustomMenuItem();
		cmi.setCaption("Members Login");
		cmi.setImageResourceId(R.drawable.icon4);
		cmi.setId(MENU_ITEM_4);
		menuItems.add(cmi);
		if (!homescreeMenu.isShowing())
			try {
				homescreeMenu.setMenuItems(menuItems);
			} catch (Exception e) {
				/*
				 * AlertDialog.Builder alert = new AlertDialog.Builder(this);
				 * alert.setTitle("Egads!"); alert.setMessage(e.getMessage());
				 * alert.show();
				 */
			}
	}

	/**
	 * Toggle our menu on user pressing the menu key.
	 */
	private void doMenu() {
		if (homescreeMenu.isShowing()) {
			// mMenu.hide();
		} else {
			// Note it doesn't matter what widget you send the menu as long as
			// it gets view.
			homescreeMenu.show(findViewById(R.id.any_old_widget));
		}
	}

	/**
	 * For the demo just toast the item selected.
	 */

	@Override
	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		final LoadItems liitems = new LoadItems();
		ListView listView;
		TextView textView;
		ArrayAdapter<String> aa;
		switch (selection.getId()) {
		case 1:
			listView = (ListView) findViewById(R.id.listFeeds);
			textView = (TextView) findViewById(R.id.product_label);
			// textView.setText("Lok Sabha Members");
			// Adds all the member types to the adapter List
			aa = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, liitems.membertypes);
			listView.setAdapter(aa);
			listView.setClickable(true);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View v,
						int position, long arg3) {
					Intent i = new Intent(v.getContext(), LokSabhaMembers.class);
					// We have to set data for our new Intent
					String memTypeVal = liitems.membertypevalues[position];
					String[] values;
					if (memTypeVal.equalsIgnoreCase("p"))
						values = new String[] { "party", memTypeVal };
					else if (memTypeVal.equalsIgnoreCase("s"))
						values = new String[] { "states", memTypeVal };
					else if (memTypeVal.equalsIgnoreCase("b"))
						values = new String[] { "bday", memTypeVal };
					else
						values = new String[] { "members", memTypeVal };
					i.putExtra("values", values);

					local.startActivity(i);
				}
			});
			break;

		case 2:
			listView = (ListView) findViewById(R.id.listFeeds);
			textView = (TextView) findViewById(R.id.product_label);
			//textView.setText("Live TV");
			aa = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, liitems.livebottommenu);
			listView.setAdapter(aa);
			listView.setClickable(true);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					String url = liitems.livebottommenulinks[position];
					Intent i = new Intent(Intent.ACTION_VIEW);
					// We have to set data for our new Intent
					i.setData(Uri.parse(url));
					// And start activity with our Intent
					local.startActivity(i);
				}
			});
			// return true;
			break;

		case 3:
			listView = (ListView) findViewById(R.id.listFeeds);
			textView = (TextView) findViewById(R.id.product_label);
			//textView.setText("Websites");
			aa = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,
					liitems.websitesbottommenu);
			listView.setAdapter(aa);
			listView.setClickable(true);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					String url = liitems.websitesbottommenulinks[position];
					Intent i = new Intent(Intent.ACTION_VIEW);
					// We have to set data for our new Intent
					i.setData(Uri.parse(url));
					// And start activity with our Intent
					local.startActivity(i);
				}
			});
			// return true;
			break;

		case 4:
			// We create an Intent which is going to display data
			Intent i = new Intent(Intent.ACTION_VIEW);
			// We have to set data for our new Intent
			i.setData(Uri.parse("http://mpls.nic.in"));
			// And start activity with our Intent
			local.startActivity(i);
			// return true;
			break;

		default:
			// return super.onOptionsItemSelected(item);
			break;
		}
		HideMenu();
	}

	public void slideMenuIn(int animateFromX, int animateToX, final int marginX) {
		slide = new TranslateAnimation(animateFromX, animateToX, 0, 0);
		slide.setDuration(300);
		slide.setFillEnabled(true);
		slide.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				// Make movement
				// of content
				// permanent
				// after
				// animation has
				// completed
				contentParams.setMargins(marginX, 0, 0, 0); // by positioning
															// its left margin
				content.setLayoutParams(contentParams);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
		content.startAnimation(slide); // Slide menu in or out
	}

	public void SetItems(List<RssItem> result) {
		ListView lv = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<RssItem> aa = new ArrayAdapter<RssItem>(this,
				android.R.layout.simple_list_item_1, result);
		lv.setAdapter(aa);
		Toast.makeText(Home.this, "Loaded", Toast.LENGTH_SHORT).show();
	}

	public void ShowMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG);
	}

	public void GetLoMFeeds(String link) {
		HideMenu();
		GetLoMRSSDataTask task = new GetLoMRSSDataTask();

		// Start download RSS task
		// task.execute( "http://164.100.47.5/IPADFeeds/Bulletin1.aspx");
		// task.execute( "http://164.100.47.5/AndroidFeeds/BusinessFeeds.aspx");
		task.execute(link);

		// Debug the thread name
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void GetLOBFeeds(String link) {
		HideMenu();
		GetLOBRSSDataTask task = new GetLOBRSSDataTask();
		task.execute(link);
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void GetNotiFeeds(String link) {
		// HideMenu();
		GetNotiRSSDataTask task = new GetNotiRSSDataTask();
		task.execute(link);
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void GetFeeds(String link) {
		// HideMenu();
		GetRSSDataTask task = new GetRSSDataTask();
		task.execute(link);

		// Debug the thread name
		Log.d("RssReader", Thread.currentThread().getName());
	}

	public void HideMenu() {
		if (contentParams.leftMargin == -(menu.getLayoutParams().width)) { // Menu
																			// is
																			// hidden
																			// (slide
																			// out
																			// parameters)
			animateFromX = 0;
			animateToX = (menu.getLayoutParams().width);
			marginX = 0;
			menuOpen = true;
			// slideMenuIn(animateFromX, animateToX, marginX);
		} else { // Menu is visible (slide in parameter)
			animateFromX = 0;
			animateToX = -(menu.getLayoutParams().width);
			marginX = -(menu.getLayoutParams().width);
			menuOpen = false;
			slideMenuIn(animateFromX, animateToX, marginX);
		}
	}

	public void ShowMenu() {
		if (contentParams.leftMargin == -(menu.getLayoutParams().width)) { // Menu
																			// is
																			// hidden
																			// (slide
																			// out
																			// parameters)
			animateFromX = 0;
			animateToX = (menu.getLayoutParams().width);
			marginX = 0;
			menuOpen = true;
			slideMenuIn(animateFromX, animateToX, marginX);
		} else { // Menu is visible (slide in parameter)
			animateFromX = 0;
			animateToX = -(menu.getLayoutParams().width);
			marginX = -(menu.getLayoutParams().width);
			menuOpen = false;
			// slideMenuIn(animateFromX, animateToX, marginX);
		}
	}

	// Provisional calendar: invokes RSS feed class
	private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
		@Override
		protected List<RssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("RssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				RssReader rssReader = new RssReader(urls[0]);
				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<RssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.listFeeds);
			// Create a list adapter
			// ArrayAdapter<RssItem> adapter = new
			// ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1,
			// result);
			customlistadpater_feeds listadap = new customlistadpater_feeds(
					local, R.layout.customlistadapter_feeds, result);
			// Set list adapter for the ListView
			lv.setAdapter(listadap);

			// Set list view item click listener
			lv.setOnItemClickListener(new ListListener(result, local));
			// HideMenu();

			// Home hm = new Home();
			// hm.SetItems(result);
		}
	}

	private class GetLoMRSSDataTask extends
			AsyncTask<String, Void, List<LoMRssItem>> {
		@Override
		protected List<LoMRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("LoMRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				ListOfMemberRSSReader rssReader = new ListOfMemberRSSReader(
						urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<LoMRssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.listFeeds);

			// Create a list adapter
			final ArrayAdapter<LoMRssItem> adapter = new ArrayAdapter<LoMRssItem>(
					local, android.R.layout.simple_list_item_1, result);
			// Set list adapter for the ListView
			lv.setAdapter(adapter);
			/*
			 * EditText inputSearch =
			 * (EditText)findViewById(R.id.txtSearchList);
			 * inputSearch.addTextChangedListener(new TextWatcher() {
			 * 
			 * @Override public void onTextChanged(CharSequence cs, int arg1,
			 * int arg2, int arg3) { // When user changed the Text
			 * adapter.getFilter().filter(cs); }
			 * 
			 * @Override public void beforeTextChanged(CharSequence arg0, int
			 * arg1, int arg2, int arg3) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * @Override public void afterTextChanged(Editable arg0) { // TODO
			 * Auto-generated method stub } });
			 */
			// Set list view item click listener
			lv.setOnItemClickListener(new LoMListListener(result, local));

			// HideMenu();
			// Home hm = new Home();
			// hm.SetItems(result);
		}
	}

	private class GetLOBRSSDataTask extends
			AsyncTask<String, Void, List<LOBRssItem>> {
		@Override
		protected List<LOBRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("LobRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				LOBRssReader rssReader = new LOBRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<LOBRssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.listFeeds);

			// Create a list adapter
			customlistadpater_feeds listadap = new customlistadpater_feeds(
					local, R.layout.customlistadapter_feeds, result);
			lv.setAdapter(listadap);
			// Set list view item click listener
			lv.setOnItemClickListener(new LOBListListener(result, local));
		}
	}

	private class GetNotiRSSDataTask extends
			AsyncTask<String, Void, List<NotiRssItem>> {
		@Override
		protected List<NotiRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("LoMRssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				NotiRssReader rssReader = new NotiRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<NotiRssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.listFeeds);

			// Create a list adapter
			ArrayAdapter<NotiRssItem> adapter = new ArrayAdapter<NotiRssItem>(
					local, android.R.layout.simple_list_item_1, result);
			// Set list adapter for the ListView
			if (adapter.getCount() > 0)
				lv.setAdapter(adapter);
			else {
				String[] ary = new String[] { "No new notification" };
				ArrayAdapter<String> aa = new ArrayAdapter<String>(local,
						android.R.layout.simple_list_item_1, ary);
				lv.setAdapter(aa);

			}
			lv.setOnItemClickListener(new NotiListListener(result, local));
			// HideMenu();
			// Home hm = new Home();
			// hm.SetItems(result);
		}
	}

}
