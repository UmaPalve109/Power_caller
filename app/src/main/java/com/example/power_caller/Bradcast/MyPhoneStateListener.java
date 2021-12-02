package com.example.power_caller.Bradcast;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneStateListener extends PhoneStateListener {

    public void onCallStateChange(int state, String incomingNumber){

        Log.d("","Icoming Number inside onCallStateChange : " + incomingNumber);
        switch(state){
            case TelephonyManager.CALL_STATE_RINGING:
                Log.d("","PHONE RINGING.........TAKE IT.........");
                Log.d("","PHONE RINGING.........TAKE IT........." + incomingNumber);

                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.d("","CALL_STATE_OFFHOOK...........");
                break;
        }
    }
}
