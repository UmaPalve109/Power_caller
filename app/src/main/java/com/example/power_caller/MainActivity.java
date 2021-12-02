package com.example.power_caller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        permissions = new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.INTERNET,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CALL_LOG
        };
        int permission_All = 1;
        if(!hasPermission(MainActivity.this,permissions))
        {
            ActivityCompat.requestPermissions(MainActivity.this,permissions,permission_All);
        }

    }



    private  boolean hasPermission(Context context, String... permissions){
        System.out.print("insise per");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                context!=null  && permissions!=null)
        {
            for(String permission : permissions)
            {
                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read contact Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Read contact Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read phone state Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Read phone state Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Write copntact Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Write contact Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Internet Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Received SMS Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Received SMS Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "call log Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "call log Permission is denied", Toast.LENGTH_SHORT).show();
            }


        }
    }

}