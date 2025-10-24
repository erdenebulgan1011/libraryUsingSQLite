package com.example.lab10_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText b_name, b_author, b_date;
    Button saveBtn, viewBtn;
    Intent intent;

    private static final String CHANNEL_NAME = "my_channel_01_NAME";
    private static final String CHANNEL_DESC = "my_channel_01_DESC";
    private static final String CHANNEL_ID = "my_channel_01";


    NotificationManager notificationManager;

    private void createNotificationChannel(String Channel_ID){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = CHANNEL_NAME;
            String description = CHANNEL_DESC;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Channel_ID, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_name = findViewById(R.id.bookName);
        b_author = findViewById(R.id.bookAuthor);
        b_date = findViewById(R.id.bookDate);

        saveBtn = findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bname = b_name.getText().toString() + "\n";
                String bauthor = b_author.getText().toString();
                String bdate = b_date.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertBookDetails(bname, bauthor, bdate);
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Өгөгдөл оруулав....", Toast.LENGTH_SHORT).show();

                createNotificationChannel(CHANNEL_ID);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel =
                            new NotificationChannel(CHANNEL_ID, "Mascot Notification",
                                    NotificationManager.IMPORTANCE_DEFAULT);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(bname)
                        .setContentText(bauthor)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(bdate))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificationManager.notify(0,builder.build());
            }
        });

        viewBtn = findViewById(R.id.btnView);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(viewIntent);
            }
        });
    }
}
