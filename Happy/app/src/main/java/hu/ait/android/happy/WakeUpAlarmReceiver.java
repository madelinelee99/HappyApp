package hu.ait.android.happy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by clarabelitz on 5/22/16.
 */
public class WakeUpAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        AlarmActivity alarmInstance = AlarmActivity.instance();
        alarmInstance.setAlarmText("Time to meditate!");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

//        ComponentName componentName = new ComponentName(context.getPackageName(),
//                AlarmService.class.getName());
//        start
    }
}
