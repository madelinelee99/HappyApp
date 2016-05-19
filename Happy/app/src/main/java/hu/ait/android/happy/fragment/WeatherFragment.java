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
        private TextView tvIdea;
        private ImageView ivIdea;


    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.weather_fragment, null);
            tvResult = (TextView) rootView.findViewById(R.id.tvResult);
            tvIdea = (TextView) rootView.findViewById(R.id.tvIdea);
            ivWeather = (ImageView) rootView.findViewById(R.id.ivWeather);
            ivIdea = (ImageView) rootView.findViewById(R.id.ivIdea);

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
            tvResult.setText("The weather today is: " + "\n" + weatherResult.getWeather().get(0).getDescription());
            String iconName = weatherResult.getWeather().get(0).getIcon();
            Glide.with(this).load("http://openweathermap.org/img/w/" + iconName + ".png").into(ivWeather);

            if(weatherResult.getWeather().get(0).getDescription().equals("clear sky")){
                tvIdea.setText("Spend the day in the park reading and soaking in the rays!");
                ivIdea.setImageResource(R.drawable.park);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("broken clouds")){
                tvIdea.setText("Hike in a nearby national park");
                ivIdea.setImageResource(R.drawable.hike);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("overcast clouds")){
                tvIdea.setText("Swim at your local swimming pool");
                ivIdea.setImageResource(R.drawable.swim);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("few clouds")){
                tvIdea.setText("Get on your bike and explore your neighborhood");
                ivIdea.setImageResource(R.drawable.bike);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("light rain")){
                tvIdea.setText("Sing in the rain!");
                ivIdea.setImageResource(R.drawable.sing);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("moderate rain")){
                tvIdea.setText("Do any art project that you've been meaning to get to");
                ivIdea.setImageResource(R.drawable.art);
            }
            if(weatherResult.getWeather().get(0).getDescription().equals("heavy intensity rain")){
                tvIdea.setText("Play boardgames with friends indoors");
                ivIdea.setImageResource(R.drawable.boardgames);
            }

        }


    }


