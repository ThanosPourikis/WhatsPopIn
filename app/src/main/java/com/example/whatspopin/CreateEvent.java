package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.Event;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class CreateEvent extends AppCompatActivity {

	final FirebaseStorage storage = FirebaseStorage.getInstance();
	final FirebaseDatabase database = FirebaseDatabase.getInstance();
	DatabaseReference myRef = database.getReference().child("Events").push();
	private static final int PICK_IMAGE = 100;
	private TextView usr;
	private double loc[] = {0, 0};
	private AutoCompleteTextView cat;
	private TextView desc;

	private DatePicker date;
	private TimePicker time;

	private ImageView imageView;

	private Button next;

	Uri imageUri;
	private String imagePath;
	private byte[] imgByteArray;
	private Bitmap imgBitmap;

	private static String TAG;//= AutocompleteFromFragmentActivity.class.getSimpleName();

	private FirebaseAuth mAuth;


	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);
		StorageReference ref = storage.getReference();
		usr = findViewById(R.id.eventNameText);
		cat = findViewById(R.id.catTextArea);
		desc = findViewById(R.id.descTextArea);
		imageView = findViewById(R.id.createImageView);
		date = findViewById(R.id.date);
		time = findViewById(R.id.time);

		mAuth = FirebaseAuth.getInstance();
		FirebaseUser user = mAuth.getCurrentUser();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.catories));
		cat = findViewById(R.id.catTextArea);
		cat.setAdapter(adapter);

		if (!Places.isInitialized()) {
			Places.initialize(getApplicationContext(), getString(R.string.api_key));
		}
		AutocompleteSupportFragment autocompleteFragment =
				(AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.map_autocomplete_fragment);
		autocompleteFragment.setCountry("GR");
		autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

		autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
			@Override
			public void onPlaceSelected(@NonNull Place place) {
				try {
					loc[0] = place.getLatLng().latitude;
					loc[1] = place.getLatLng().longitude;
					System.out.println(loc[0] + "," + loc[1]);
					System.out.println(place.getLatLng().toString());

				} catch (Exception e) {
					System.out.println(e);
				}
			}

			@Override
			public void onError(@NonNull Status status) {
				System.out.println(status);

			}
		});

		next = findViewById(R.id.done);
		next.setOnClickListener((View view) -> {
			StorageReference img =	ref.child("events/"+imageUri.getLastPathSegment());
			UploadTask upload = img.putFile(imageUri);

			upload.addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					Log.d("PIC",e.toString());
				}
			}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
				@Override
				public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
					imagePath = img.getName();
						Event event = new Event(usr.getText().toString(), loc.toString()
								, cat.getText().toString(), desc.getText().toString()
								, date.getAutofillValue().getDateValue(), (time.getHour() + ":" + time.getMinute())
								,user.getUid(), imagePath);
						myRef.setValue(event);
				}
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
			imageUri = data.getData();
			imageView.setImageURI(imageUri);

			imageView.setVisibility(View.VISIBLE);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1080));
		}
	}
}