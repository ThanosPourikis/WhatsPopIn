package com.example.whatspopin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatspopin.database.Event;

import java.util.List;

public final class ScrollViewFill {
	public static void fill(LinearLayout ls, List<Event> eventList) {
		for (Event i : eventList) {

			LinearLayout li = new LinearLayout(ls.getContext());
			li.setOrientation(LinearLayout.HORIZONTAL);
			//Image which is to be taken from the local Database
			ImageView img = new ImageView(ls.getContext());
			img.setImageResource(R.drawable.icon);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
			img.setLayoutParams(layoutParams);


			LinearLayout li2 = new LinearLayout(ls.getContext());
			li2.setOrientation(LinearLayout.VERTICAL);
			//Text for Name
			TextView txt = new TextView(ls.getContext());
			txt.setText(i.getName());
			li2.addView(txt);

			//Text for Description
			txt = new TextView(ls.getContext());
			txt.setText(i.getDescription());
			li2.addView(txt);

			li.addView(img);
			li.addView(li2);
			Button btn = new Button(ls.getContext());
			btn.setText("PopIn");

			li.addView(btn);
			ls.addView(li);

			btn.setOnClickListener((View v) ->
			{
				Toast.makeText(ls.getContext(), "Deleting" + i.getName(), Toast.LENGTH_LONG).show();
			});

		}
	}
}
