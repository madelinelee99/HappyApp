package hu.ait.android.happy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import hu.ait.android.happy.adapter.MyPagerAdapter;


public class WeatherActivity extends ActionBarActivity {

    public static final String KEY_COORD = "KEY_COORD";
    private String coordName = "lat=35&lon=139";
    private Context context;
    private PagerTitleStrip ptsTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_fragment);

        locationManager = new MyLocationManager(this);

        requestNeededPermission();

        MyPagerAdapter adapter = new MyPagerAdapter(
                getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(adapter);

        ptsTop = (PagerTitleStrip) findViewById(R.id.ptsTop);
//        ptsTop.setTextSpacing(20);

        if (getIntent() != null && getIntent().hasExtra(KEY_COORD)) {
            coordName = getIntent().getStringExtra(KEY_COORD);
        }

    }

    public static final int REQUEST_CODE_LOCATION_PERMISSION = 401;
    private MyLocationManager locationManager;
    private String coordinates;


    public void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(WeatherActivity.this, "I need it for GPS", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            locationManager.startLocationMonitoring();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(WeatherActivity.this, "FINE_LOC perm granted", Toast.LENGTH_SHORT).show();
                    locationManager.startLocationMonitoring();
                } else {
                    Toast.makeText(WeatherActivity.this, "FINE_LOC perm NOT granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        locationManager.stopLocationMonitoring();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onLocationReceived(Location location) {
        //TODO: calculate distance


        Intent intentShowDetails = new Intent(context,WeatherActivity.class);
        coordinates = "lat="+location.getLatitude() + "&lon="+location.getLongitude();
        intentShowDetails.putExtra(WeatherActivity.KEY_COORD,
                coordinates);

        startActivity(intentShowDetails);

    }





    @Override
    protected void onResume() {
        super.onResume();
        new HttpGetTask().execute("http://api.openweathermap.org/data/2.5/weather?"+coordName+"&units=metric&appid=7bbe78cd9652922152ef8f205bc9c7d6");
    }

}
