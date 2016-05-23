package hu.ait.android.happy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**Created by Maddy**/

public class BadgesActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivHealthyLifestyle;
    private TextView tvHealthyLifestyle;
    private ImageView ivSocialLife;
    private TextView tvSocialLife;
    private ImageView ivAvidAdventurer;
    private TextView tvAvidAdventurer;

    private int adventureScore = 0;
    private int socialScore = 0;
    private int healthScore = 0;

    private static final String AD_STRING = "ADVENTURE";
    private static final String HEALTH_STRING = "HEALTH";
    private static final String SOCIAL_STRING = "SOCIAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badges_activity);

        Intent mIntent = getIntent();

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvHealthyLifestyle = (TextView) findViewById(R.id.tvHealthyLifestyle);
        ivHealthyLifestyle = (ImageView) findViewById(R.id.ivHealthyLifestyle);
        tvSocialLife = (TextView) findViewById(R.id.tvSocialLife);
        ivSocialLife = (ImageView) findViewById(R.id.ivSocialLife);
        tvAvidAdventurer = (TextView) findViewById(R.id.tvAvidAdventurer);
        ivAvidAdventurer = (ImageView) findViewById(R.id.ivAvidAdventurer);

        adventureScore = getIntent().getIntExtra(AD_STRING, 0);
        healthScore = getIntent().getIntExtra(HEALTH_STRING, 0);
        socialScore =  getIntent().getIntExtra(SOCIAL_STRING, 0);

        tvHealthyLifestyle.setText(Integer.toString(healthScore));
        tvSocialLife.setText(Integer.toString(socialScore));
        tvAvidAdventurer.setText(Integer.toString(adventureScore));


    }
}

