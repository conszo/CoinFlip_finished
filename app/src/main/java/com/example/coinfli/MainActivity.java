package com.example.coinfli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final Random RANDOM =new Random();
    private ImageView coin;
    private Button btn_flip, btnHeads, btnTails;
    private TextView scoreTextView;
    private  int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    coin = (ImageView) findViewById(R.id.coin_imgView);
    btn_flip = (Button) findViewById(R.id.btn_flip);
    btnHeads = (Button) findViewById(R.id.btnHeads);
    btnTails = (Button) findViewById(R.id.btnTails);
    scoreTextView = (TextView) findViewById(R.id.scoreTextView);


    btn_flip.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            flipCoin();
        }
    });



    btnHeads.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            checkPrediction(true);
        }
    });
        btnTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPrediction(false);
            }
        });
    }

    private void flipCoin(){
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            coin.setImageResource(RANDOM.nextFloat() > 0.5f ? R.drawable.tails2:R.drawable.pound_heads);

                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);
                coin.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coin.startAnimation(fadeOut);
        scoreTextView.setText("Score:" + score);

    }

    private void checkPrediction(boolean predictedHeads) {
        // Check if the prediction is correct
        boolean isHeads = RANDOM.nextFloat() > 0.5f;
        if ((predictedHeads && isHeads) || (!predictedHeads && !isHeads)) {
            // Prediction is correct
            score++;
        } else {
            // Prediction is incorrect
            score--;
        }

        // Update the score TextView
        scoreTextView.setText("Score: " + score);
    }
}