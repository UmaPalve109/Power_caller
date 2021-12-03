package com.example.power_caller.Bradcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;
//import com.android.internal.telephony;
import com.android.internal.telephony.ITelephony;


public class MyBroadcastReceiver extends BroadcastReceiver {

    String cName;
    String msg;


    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(
                new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
//                Log.d("","Icoming Number inside onCallStateChange : " + incomingNumber);
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
//                        Log.d("", "PHONE RINGING.........TAKE IT.........");
//
//                         cName = findNameByNumber(context,incomingNumber);
//                        Log.d("", "PHONE RINGING.........TAKE IT........." + cName);
//                        if(cName == null){
//                            msg = "Unknown";
//                        }else {
//                            msg = cName;
//                        }

//                        Log.d("", "PHONE RINGING.........TAKE IT........." + incomingNumber);
//
//                        Bundle bundle = intent.getExtras();
//                        String phoneNumber = bundle.getString("block_number");
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String phoneNumber = preferences.getString("block_number", "");
                        Log.d("test", "testing "+phoneNumber);
                        if ((phoneNumber != null)) {
                            Log.d("test", "testing "+phoneNumber);
                            try{

                                endCall(context);

                            }catch (Exception e) {
                                e.printStackTrace();

                            }
                            Log.d("HANG UP", phoneNumber);
                        }
//                        Intent i = new Intent(context, CustomDialog.class);
//                        i.putExtra("msg",msg);
//
//                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(i);
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d("", "CALL_STATE_OFFHOOK...........");
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                }
//                super.onCallStateChanged(state, incomingNumber);
                System.out.println("incomingNumber : "+incomingNumber);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);



    }


    private void endCall(Context context) throws Exception {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Class c = Class.forName(tm.getClass().getName());
        Method m = c.getDeclaredMethod("getITelephony");
        m.setAccessible(true);
        ITelephony telephonyService = (ITelephony) m.invoke(tm);
        telephonyService.endCall();
    }


    public String findNameByNumber(Context mContext,String num){

        boolean val = false;

        Uri uri = Uri.withAppendedPath(Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(num));

        String name = null;
        String number = null;
        Cursor cursor = mContext.getContentResolver().query(uri,
                new String[] { Contacts.People.Phones.DISPLAY_NAME }, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(Contacts.People.Phones.DISPLAY_NAME));
            //number = cursor.getString(cursor.getColumnIndex(Contacts.People.Phones.NUMBER));
            Log.d("",name);
            System.out.println(" Output    : "+ name);
            cursor.close();
        }

        return  name;
    }

}
