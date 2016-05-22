package hu.ait.android.happy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * All alarm code adapted from concretepage.com and javapapers.com
 **/

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity alarmInstance;
    private TextView alarmTextView;
    private Button btnAlarmDone;
    private Button btnAlarmCancel;

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);

//    public static AlarmActivity instance() {
//        return alarmInstance;
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        alarmInstance = this;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        btnAlarmDone = (Button) findViewById(R.id.btnAlarmDone);
        btnAlarmCancel = (Button) findViewById(R.id.btnAlarmCancel);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent newIntent = new Intent(AlarmActivity.this, WakeUpAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, newIntent, 0);

        btnAlarmDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAlarmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
                finish();
            }
        });
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
           // Log.d("AlarmActivity", "Alarm On");
            setAlarm();
        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
          //  Log.d("MyActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
//            pendingIntent = PendingIntent.getActivity(this, requestCode, )
//            alarmManager.cancel(pendingIntent);
//        Intent i = new Intent(AlarmActivity.this, WakeUpAlarmReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(AlarmActivity.this,
//                0, i, 0);
//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        am.cancel(pi);
        Intent i = new Intent(getBaseContext(), WakeUpAlarmReceiver.class);
        AlarmManager alarm = (AlarmManager) AlarmActivity.this.getSystemService(ALARM_SERVICE);
        PendingIntent pending = PendingIntent.getActivity(AlarmActivity.this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        alarm.cancel(pending);
    }

}
