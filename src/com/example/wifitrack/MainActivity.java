package com.example.wifitrack;

import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private WifiInfo wifiInfo = null; // The obtained Wifi information making
	private WifiManager wifiManager = null; // The Wifi manager making
	private Handler handler;
	private ImageView wifi_image; // Signal picture making
	private int level; // Signal strength value making
	private Toast toast = null;

	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// The picture control initialization making
		wifi_image = (ImageView) findViewById(R.id.wifi_image);
		// WifiManager making
		wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		// timer, every 5 seconds to get a signal strength value making
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				wifiInfo = wifiManager.getConnectionInfo();
				// Received signal strength value making
				level = wifiInfo.getRssi();
				// According to the obtained signal intensity of transmitted
				// information making
				if (level <= 0 && level >= -50) {
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} else if (level <= -51 && level >= -56) {
					Message msg = new Message();
					msg.what = 2;
					handler.sendMessage(msg);
				} else if (level <= -57 && level >= -60) {
					Message msg = new Message();
					msg.what = 3;
					handler.sendMessage(msg);
				} else if (level <= -61 && level >= -70) {
					Message msg = new Message();
					msg.what = 4;
					handler.sendMessage(msg);
				} else if (level <= -71 && level >= -82) {
					Message msg = new Message();
					msg.what = 5;
					handler.sendMessage(msg);
				} else if (level <= -83 && level >= -88) {
					Message msg = new Message();
					msg.what = 6;
					handler.sendMessage(msg);
				} else if (level < -89 && level >= -98) {
					Message msg = new Message();
					msg.what = 7;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = 8;
					handler.sendMessage(msg);
				}

			}

		}, 1000, 3000);
		// implementation using Handler between UI thread and Timer thread
		// information transmission, every 5 seconds to tell UI thread wifiInto
		// making
		handler = new Handler() {

			@SuppressWarnings("static-access")
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				// if receive the correct message on the acquisition of
				// WifiInfo, change the picture and display the signal strength
				// making
				case 1:
					wifi_image.setImageResource(R.drawable.wifi7);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + " bestes   Signal",
							toast.LENGTH_SHORT).show();

					break;
				case 2:
					wifi_image.setImageResource(R.drawable.wifi6);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "   sehr gutes Signal",
							toast.LENGTH_SHORT).show();

					break;
				case 3:
					wifi_image.setImageResource(R.drawable.wifi5);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "  gutes signal",
							toast.LENGTH_SHORT).show();

					break;
				case 4:
					wifi_image.setImageResource(R.drawable.wifi4);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "   normales Signal",
							toast.LENGTH_SHORT).show();

					break;
				case 5:
					wifi_image.setImageResource(R.drawable.wifi3);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "   mittleres Signal",
							toast.LENGTH_SHORT).show();

					break;
				case 6:
					wifi_image.setImageResource(R.drawable.wifi2);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "schwaches signal",
							toast.LENGTH_SHORT).show();

					break;
				case 7:
					wifi_image.setImageResource(R.drawable.wifi1);
					toast.makeText(MainActivity.this,
							"Signalstärke: " + level + "sehr schwaches signal",
							toast.LENGTH_SHORT).show();

					break;
				default:
					wifi_image.setImageResource(R.drawable.wifi0);
					toast.makeText(MainActivity.this, "kein Signal",
							toast.LENGTH_SHORT).show();

				}
			}

		};
		
	

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		//handler.wait();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			closeActivity();
		}
		return super.onOptionsItemSelected(item);
	}

	public void closeActivity() {
		System.exit(0);
		// this.finish();
	}

}
