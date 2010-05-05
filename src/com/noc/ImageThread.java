package com.noc;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageThread extends Thread {

	public ImageThread() {
		super();
	}

	private Bitmap bmp;
	private String locationURL;

	public void setLocationURL(String locn) {
		this.locationURL = locn;
	}

	public Bitmap getBitmap() {
		return bmp;
	}

	public void run() {
		HttpUriRequest request = null;
		HttpResponse resp = null;
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			// Build the request
			request = new HttpGet(locationURL);
			// Execute it using the default //HTTP Client settings;
			resp = client.execute(request); // Pull out the entity

			// if (resp.getStatusLine().getStatusCode() !=
			// DefaultHttpClient.)

			HttpEntity entity = resp.getEntity();

			InputStream is = entity.getContent();
			BufferedInputStream bis = new BufferedInputStream(is, 8 * 1024);
			bmp = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();

		} catch (Exception e) {
			Log.e("Load image", "FAIL!");
		}
	}
}
