package com.example.hoaht.demobattery;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBatteryInfo = (Button) findViewById(R.id.btnBatteryInfo);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, intentFilter);
        // Are we charging / charged?
        if (batteryStatus != null) {
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            Log.d(TAG, "isCharging: " + isCharging);

            // How are we charging?
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            Log.d(TAG, "usbCharge: " + usbCharge);
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            Log.d(TAG, "acCharge: " + acCharge);
        }
    }
}
