package hu.ait.android.happy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hu.ait.android.happy.R;
import hu.ait.android.happy.data.WeatherResult;

public class WeatherFragment extends Fragment {

        private TextView tvResult;
        private ImageView ivWeather;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.weather_fragment, null);
            tvResult = (TextView) rootView.findViewById(R.id.tvResult);
            ivWeather = (ImageView) rootView.findViewById(R.id.ivWeather);
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            EventBus.getDefault().register(this);
        }

        @Override
        public void onStop() {
            EventBus.getDefault().unregister(this);
            super.onStop();
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onEventMainThread(WeatherResult weatherResult) {
            tvResult.setText("The weather today is: " + weatherResult.getWeather().get(0).getDescription());
            String iconName = weatherResult.getWeather().get(0).getIcon();
            Glide.with(this).load("http://openweathermap.org/img/w/" + iconName + ".png").into(ivWeather);
        }


    }


