package com.noc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class DisplayCamera extends Activity {
	private Bitmap bmpM;
	private boolean newImage = false;

	/** Called when the activity is first created. */
	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ImageView myView = new ImageView(this);
		// myView.setImageBitmap(bmpM);
		// setContentView(myView);

		// status.setImageBitmap(bmpM);
		// setContentView(R.layout.noclayout);

	}

	private void showImage(String locnURL) {

		ImageThread imgThread = new ImageThread();
		imgThread.setLocationURL(locnURL);
		imgThread.start();

		while (imgThread.isAlive()) {
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ImageView myView = new ImageView(this);
		myView.setImageBitmap(imgThread.getBitmap());
		setContentView(myView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.destinationmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item01:
			this
					.showImage("http://www.rta.nsw.gov.au/trafficreports/cameras/camera_images/harbourbridge.jpg");
			break;
		case R.id.item02:
			this
					.showImage("http://www.rta.nsw.gov.au/trafficreports/cameras/camera_images/anzacbr.jpg");
			break;
		case R.id.item03:
			this
					.showImage("http://www.rta.nsw.gov.au/trafficreports/cameras/camera_images/georgest.jpg");
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
