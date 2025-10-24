package com.example.lab10_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        ListView lv = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList, R.layout.list_row,
                new String[]{"bname", "bauthor", "bdate"},
                new int[]{R.id.details_bname, R.id.details_bauthor, R.id.details_bdate});
        lv.setAdapter(adapter);

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ItemClick", "Item clicked at position: " + position);
                HashMap<String, String> selectedItem = userList.get(position);

                // Create an intent to open the details page
                Intent detailsIntent = new Intent(DetailsActivity.this, DetailsItemActivity.class);

                // Pass the selected item's details to the details page
                detailsIntent.putExtra("id", position + 1); // Assuming ID starts from 1
                detailsIntent.putExtra("bname", selectedItem.get("bname"));
                detailsIntent.putExtra("bauthor", selectedItem.get("bauthor"));
                detailsIntent.putExtra("bdate", selectedItem.get("bdate"));

                // Start the details page activity
                startActivity(detailsIntent);
            }
        });

    }

}
