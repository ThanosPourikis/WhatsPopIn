package com.example.whatspopin;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateEvent extends Activity {


	final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);
	private static final int PICK_IMAGE = 100;
	private TextView usr;
	private TextView loc;
	private TextView cat;
	private TextView desc;

	private ImageView imageView;

	private Button next;

	private Bitmap imgBitmap;
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);

		usr = findViewById(R.id.eventNameText);
		loc = findViewById(R.id.locTextArea);
		cat = findViewById(R.id.catTextArea);
		desc = findViewById(R.id.descTextArea);
		imageView = findViewById(R.id.createImageView);

		next = findViewById(R.id.done);
		next.setOnClickListener((View view) -> {
					Executor myExecutor = Executors.newSingleThreadExecutor();
					myExecutor.execute(() ->
					{
						Event event = new Event(usr.getText().toString(), loc.getText().toString(), cat.getText().toString(), desc.getText().toString());
						db.eventDao().insertEvent(event);
					});

					Intent intent = new Intent(view.getContext(), MainActivity.class);
					startActivity(intent);
					setResult(RESULT_OK, intent);
					finish();
				}

		);
		Button up = findViewById(R.id.upload);
		up.setOnClickListener((View view) -> {
			selectPhoto();

		});
	}

	private void selectPhoto() {
		Intent gallery =
				new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(gallery, PICK_IMAGE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
			Uri imageUri = data.getData();
			imageView.setImageURI(imageUri);
			try {
				imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
			}catch (Exception e){
				System.out.println(e);
			}
			imageView.setVisibility(View.VISIBLE);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1080));
		}
	}
}