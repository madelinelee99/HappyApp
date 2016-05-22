package hu.ait.android.happy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Animations from http://androidmkab.com/
 **/

public class SplashActivity extends AppCompatActivity {

    ImageView ivWelcome;
    TextView tvQuote;
    Random rand = new Random(System.currentTimeMillis());
    int[] images = {R.drawable.quote1, R.drawable.quote2, R.drawable.quote3, R.drawable.quote4, R.drawable.quote5, R.drawable.quote6 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivWelcome = (ImageView) findViewById(R.id.ivWelcome);
        tvQuote = (TextView) findViewById(R.id.tvQuote);
        String[] quotes = {getString(R.string.quote1Text), getString(R.string.quote2Text),
                getString(R.string.quote3Text), getString(R.string.quote4Text),
                getString(R.string.quote5Text) , getString(R.string.quote6Text)};

        int randNum = rand.nextInt(6);
        ivWelcome.setImageResource(images[randNum]);
        tvQuote.setText(quotes[randNum]);

        startAnimations();

//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 5000);
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        LinearLayout l2 = (LinearLayout) findViewById(R.id.inner_lay);
        l2.clearAnimation();
        l2.startAnimation(anim);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);

    }
}
