package com.example.whatspopin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public final class ScrollViewFill extends AppCompatActivity {


	public static void fill(LinearLayout ls, DataSnapshot snapshot, int flag) {
		Context context = ls.getContext();
		final StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("events");
		FirebaseAuth auth = FirebaseAuth.getInstance();
		DatabaseReference databaseRefAtt = FirebaseDatabase.getInstance().getReference().child("Attends");
		DatabaseReference databaseRefEv = FirebaseDatabase.getInstance().getReference().child("Events");
		if (snapshot.hasChildren()) {
			for (DataSnapshot snap : snapshot.getChildren()) {

				Event i = snap.getValue(Event.class);

				LinearLayout li = new LinearLayout(context);
				li.setOrientation(LinearLayout.HORIZONTAL);
				ImageView img = new ImageView(context);



				/*storageRef.child(i.getImagePath()).getFile(temp)
							.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
								@Override
								public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
									Log.d("PIC", "Bytes : " + taskSnapshot.getBytesTransferred());

									img.setImageBitmap(BitmapFactory.decodeFile(temp.getPath()));
								}
							}).addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							Log.d("PIC", e.toString());
							img.setImageResource(R.drawable.icon);

						}
					});*/
				storageRef.child(i.getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

					@Override
					public void onSuccess(Uri uri) {
						//Log.d("PIC",uri.toString());
						new DownloadImageTask(img).execute(uri.toString(), i.getImagePath(), context.getCacheDir().getAbsolutePath());

					}
				}).addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						img.setImageResource(R.drawable.icon);
					}
				});
				img.setLayoutParams(new LinearLayout.LayoutParams(400, 400));


				LinearLayout li2 = new LinearLayout(context);
				li2.setOrientation(LinearLayout.VERTICAL);

				//Text for Name
				TextView txt = new TextView(context);
				txt.setText(i.getName());
				li2.addView(txt);

				//Text for Description
				txt = new TextView(context);
				txt.setText(i.getDescription());
				txt.setLayoutParams(new LinearLayout.LayoutParams(400,400));
				li2.addView(txt);

				li.addView(img);
				li.addView(li2);
				Button btn = new Button(context);

				img.setOnClickListener((View view) -> {
					Intent intent = new Intent(view.getContext(), ContentMain.class);
					intent.putExtra("eventObj", i);
					context.startActivity(intent);
				});
				if (flag == 1) {
					btn.setText("PopIn");
					btn.setOnClickListener((View v) ->{
						databaseRefAtt.child(auth.getUid()).child(snap.getKey()).setValue(i);
						Toast.makeText(context, "Popin In " + i.getName(), Toast.LENGTH_LONG).show();
					});
				} else {
					if (auth.getUid().equals(i.getCreatorId())) {

						btn.setText("Delete");
						btn.setOnClickListener((View v) ->
						{
							Toast.makeText(context, "Deleting" + i.getName(), Toast.LENGTH_LONG).show();
							databaseRefEv.child(snap.getKey()).removeValue();
							snap.getRef().removeValue();
							storageRef.child(i.getImagePath()).delete();
							new File(context.getCacheDir() + "/events/" + i.getImagePath() + ".jpg").delete();
							Intent intent = new Intent(context, SavedActivity.class);
							context.startActivity(intent);
						});
					}else{
						btn.setText("Pop Out");
						btn.setOnClickListener((View v) ->{
							snap.getRef().removeValue();
							Toast.makeText(context, "Popin Out" + i.getName(), Toast.LENGTH_LONG).show();

						});

					}
				}

				li.addView(btn);
				ls.addView(li);


			}
		}
	}

}