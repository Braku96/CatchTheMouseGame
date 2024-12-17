package com.example.catchthemouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Time;
    TextView ScoreText;
    int Score;
    ImageView mouse1;
    ImageView mouse2;
    ImageView mouse3;
    ImageView mouse4;
    ImageView mouse5;
    ImageView mouse6;
    ImageView mouse7;
    ImageView mouse8;
    ImageView mouse9;
    ImageView mouse10;
    ImageView mouse11;
    ImageView mouse12;
    ImageView mouse13;
    ImageView mouse14;
    ImageView mouse15;
    ImageView mouse16;
    ImageView[] ImageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Time = findViewById(R.id.Time);
        ScoreText = findViewById(R.id.Score);
        Score = 0;
        mouse1 = findViewById(R.id.mouse1);
        mouse2 = findViewById(R.id.mouse2);
        mouse3 = findViewById(R.id.mouse3);
        mouse4 = findViewById(R.id.mouse4);
        mouse5 = findViewById(R.id.mouse5);
        mouse6 = findViewById(R.id.mouse6);
        mouse7 = findViewById(R.id.mouse7);
        mouse8 = findViewById(R.id.mouse8);
        mouse9 = findViewById(R.id.mouse9);
        mouse10 = findViewById(R.id.mouse10);
        mouse11 = findViewById(R.id.mouse11);
        mouse12 = findViewById(R.id.mouse12);
        mouse13 = findViewById(R.id.mouse13);
        mouse14 = findViewById(R.id.mouse14);
        mouse15 = findViewById(R.id.mouse15);
        mouse16 = findViewById(R.id.mouse16);
        hideImage();
        ImageArray = new ImageView[]{
                mouse1,mouse2,mouse3,mouse4,mouse5,mouse6,mouse7,mouse8,mouse9,mouse10,mouse11,mouse12,mouse13,mouse14,mouse15,mouse16};

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Time.setText("Time : "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Time.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image : ImageArray){
                    image.setVisibility(View.INVISIBLE);

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Wanna Restart ?");
                    alert.setMessage("Are you sure to Restart the Game?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"Game Ended",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.show();
                }
            }

        }.start();

    }

    public void increaseScore(View view){
        Score++;
        ScoreText.setText("Score : " + Score);
    }

    public void hideImage(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : ImageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(16);
                ImageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,1000);
            }



        };
        handler.post(runnable);
    }

}