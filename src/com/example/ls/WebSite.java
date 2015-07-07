package com.example.ls;

import com.example.rs.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class WebSite extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_site);
		VideoView vw = (VideoView) findViewById(R.id.videoView1);
	      //vw.setVideoPath("http://164.100.31.234/hls-live/livepkgr/_definst_/rstvlive/rstvlive.m3u8");
		vw.setVideoURI(Uri.parse("http://164.100.31.234/hds-live/livepkgr/_definst_/rstvlive.f4m"));
	        vw.setMediaController(new MediaController(this));
	        vw.requestFocus();
	        vw.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_site, menu);
		return true;
	}

}
