package com.example.whatspopin;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	ImageView bmImage;

	public DownloadImageTask(ImageView bmImage) {
		this.bmImage = bmImage;
	}

	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
		Bitmap mIcon11 = null;
		String to = urls[2] + "/events/";//Cache directory + events dir

		new File(to).mkdirs();

		try {
			to += urls[1] + ".jpg";
			File file = new File(to);

			if (file.exists()) {
				mIcon11 = BitmapFactory.decodeFile(file.getAbsolutePath());
			} else {
				InputStream in = new java.net.URL(urldisplay).openStream();
				Files.copy(in, file.toPath());
				mIcon11 = BitmapFactory.decodeFile(file.getAbsolutePath());
			}
		} catch (Exception e) {
			Log.d("PIC", e.getMessage());
			e.printStackTrace();
		}
		return mIcon11;
	}

	protected void onPostExecute(Bitmap result) {
		bmImage.setImageBitmap(result);
	}
}