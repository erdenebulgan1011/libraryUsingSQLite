package com.example.lab10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsItemActivity extends AppCompatActivity {
    private TextView title, date, content;
    private EditText bauthor,bdate;
    private Button deleteButton, updateButton;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        title = findViewById(R.id.detailsTitle);
        date = findViewById(R.id.detailsDateText);
        content = findViewById(R.id.detailsTextView);
        bauthor = findViewById(R.id.bauthor);
        bdate = findViewById(R.id.bdate);
        deleteButton = findViewById(R.id.deleteButton);
        updateButton = findViewById(R.id.updateButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(v -> {
            intent = new Intent(DetailsItemActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title.setText(extras.getString("bname"));
            date.setText("Date: " + extras.getString("bdate"));
            content.setText("Author: " + extras.getString("bauthor"));
            deleteButton.setOnClickListener(v -> {
                try {
                    String nameToDelete = extras.getString("bname");
                    Log.d("DeleteButton", "Delete button clicked for book with Name: " + nameToDelete);
                    DbHandler dba = new DbHandler(getApplicationContext());
                    dba.deleteBook(nameToDelete);
                    Toast.makeText(getApplicationContext(), "Book Deleted!", Toast.LENGTH_LONG).show();
                    finish();
                    Intent viewIntent = new Intent(DetailsItemActivity.this, DetailsActivity.class);
                    startActivity(viewIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error deleting book: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            updateButton.setOnClickListener(v -> {
                try {
                    String nameToUpdate = extras.getString("bname");
                    Log.d("UpdateButton", "Update button clicked for book with Name: " + nameToUpdate);
                    DbHandler dba = new DbHandler(getApplicationContext());

                    // Retrieve updated values from EditText fields
                    String updatedAuthor = bauthor.getText().toString().trim();
                    String updatedDate = bdate.getText().toString().trim();

                    // Update book details
                    dba.updateBook(nameToUpdate, updatedAuthor, updatedDate);

                    Toast.makeText(getApplicationContext(), "Book Updated!", Toast.LENGTH_LONG).show();
                    finish();


                            Intent viewIntent = new Intent(DetailsItemActivity.this, DetailsActivity.class);
                            startActivity(viewIntent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error updating book: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}