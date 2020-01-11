package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class CreateEvent extends AppCompatActivity {


	final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);
	private static final int PICK_IMAGE = 100;
	private TextView usr;
	private String loc;
	private TextView cat;
	private TextView desc;

	private DatePicker date;
	private TimePicker time;

	private ImageView imageView;

	private Button next;

	private byte[] imgByteArray;
	private Bitmap imgBitmap;

	private static String TAG;//= AutocompleteFromFragmentActivity.class.getSimpleName();


	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);

		usr = findViewById(R.id.eventNameText);
		cat = findViewById(R.id.catTextArea);
		desc = findViewById(R.id.descTextArea);
		imageView = findViewById(R.id.createImageView);

		date = findViewById(R.id.date);
		time = findViewById(R.id.time);

		if (!Places.isInitialized()) {
			Places.initialize(getApplicationContext(), getString(R.string.api_key));
		}
		AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
				getSupportFragmentManager().findFragmentById(R.id.map_autocomplete_fragment);
		autocompleteFragment.setCountry("GR");
		autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

		autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
			@Override
			public void onPlaceSelected(@NonNull Place place) {
				loc = place.getLatLng().toString();
				System.out.println(place.getLatLng().toString());
			}

			@Override
			public void onError(@NonNull Status status) {

			}
		});

		next = findViewById(R.id.done);
		next.setOnClickListener((View view) -> {
					Executor myExecutor = Executors.newSingleThreadExecutor();
					myExecutor.execute(() ->
					{

						Event event = new Event(usr.getText().toString(), loc
								, cat.getText().toString(), desc.getText().toString()
								, date.getAutofillValue().getDateValue(), (time.getHour() + ":" + time.getMinute())
								, imgByteArray);


						db.eventDao().insertEvent(event);
					});

					Intent intent = new Intent(view.getContext(), MainActivity.class);
					startActivity(intent);
					setResult(RESULT_OK, intent);
					finish();
				}

		);
		Button up = findViewById(R.id.upload);
		up.setOnClickListener((View view) -> selectPhoto());


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

		Executor myExecutor = Executors.newSingleThreadExecutor();

		if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
			Uri imageUri = data.getData();
			imageView.setImageURI(imageUri);
			try {
				imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				myExecutor.execute(() -> {
					imgBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
					imgByteArray = bos.toByteArray();
				});
			} catch (Exception e) {
				System.out.println(e);
			}
			imageView.setVisibility(View.VISIBLE);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1080));
		}
	}
}