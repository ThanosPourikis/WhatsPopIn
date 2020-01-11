package com.example.whatspopin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class ScrollViewFill extends AppCompatActivity {

	public static void fill(LinearLayout ls, List<Event> eventList, int flag) {
		Context context = ls.getContext();
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(context);
		Executor myExecutor = Executors.newSingleThreadExecutor();


		if (!eventList.isEmpty()) {
			for (Event i : eventList) {

				LinearLayout li = new LinearLayout(context);
				li.setOrientation(LinearLayout.HORIZONTAL);

				//Image which is to be taken from the local Database
				ImageView img = new ImageView(context);
			try{
				byte [] imgBitmap = i.getImage();
				Bitmap bitmap = BitmapFactory.decodeByteArray(imgBitmap,0,imgBitmap.length);
				img.setImageBitmap(bitmap);
			}catch (Exception e)
			{
				System.out.println(e);
				img.setImageResource(R.drawable.icon);
			}

				img.setLayoutParams(new LinearLayout.LayoutParams(200, 200));



				LinearLayout li2 = new LinearLayout(context);
				li2.setOrientation(LinearLayout.VERTICAL);
				//Text for Name
				TextView txt = new TextView(context);
				txt.setText(i.getName());
				li2.addView(txt);

				//Text for Description
				txt = new TextView(context);
				txt.setText(i.getDescription());
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
					btn.setOnClickListener((View v) ->
							Toast.makeText(context, i.getName(), Toast.LENGTH_LONG).show());
				} else {
					btn.setText("Delete");
					btn.setOnClickListener((View v) ->
					{
						Toast.makeText(context, "Deleting" + i.getName(), Toast.LENGTH_LONG).show();
						myExecutor.execute(() -> db.eventDao().deleteEvent(i));
						Intent intent = new Intent(context, SavedActivity.class);
						context.startActivity(intent);

					});
				}

				li.addView(btn);
				ls.addView(li);


			}
		}
	}
}