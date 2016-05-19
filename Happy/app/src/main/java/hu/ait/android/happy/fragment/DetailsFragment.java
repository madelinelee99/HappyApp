package hu.ait.android.happy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import hu.ait.android.happy.R;
import hu.ait.android.happy.data.WeatherResult;

public class DetailsFragment extends Fragment {

    private TextView tvTemp;
    private TextView tvTempMin;
    private TextView tvTempMax;
    private TextView tvPressure;
    private TextView tvHumidity;
    private TextView tvSeaLevel;
    private TextView tvGrndLevel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_fragment, null);
        tvTemp = (TextView) rootView.findViewById(R.id.tvTemp);
        tvTempMin = (TextView) rootView.findViewById(R.id.tvTempMin);
        tvTempMax = (TextView) rootView.findViewById(R.id.tvTempMax);
        tvPressure = (TextView) rootView.findViewById(R.id.tvPressure);
        tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);
        tvSeaLevel = (TextView) rootView.findViewById(R.id.tvSeaLevel);
        tvGrndLevel = (TextView) rootView.findViewById(R.id.tvGrndLevel);
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
        tvTemp.setText("Temperature "+weatherResult.getMain().getTemp()+" C");
        tvTempMin.setText("Minimum Temperature "+weatherResult.getMain().getTempMin()+" C");
        tvTempMax.setText("Maximum Temperature "+weatherResult.getMain().getTempMax()+" C");
        tvPressure.setText("Pressure "+weatherResult.getMain().getPressure());
        tvHumidity.setText("Humidity "+weatherResult.getMain().getHumidity());
        tvSeaLevel.setText("Sea Level "+weatherResult.getMain().getSeaLevel());
        tvGrndLevel.setText("Ground Level "+weatherResult.getMain().getGrndLevel());

    }
}
