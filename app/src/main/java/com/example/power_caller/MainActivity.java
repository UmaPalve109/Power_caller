package com.example.power_caller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private String[] permissions;
    FloatingActionButton mAddFab;
    Button mAddButton;
    EditText mEditTextPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddFab = findViewById(R.id.add_blck_fab);

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

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

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        mAddButton =bottomSheetDialog.findViewById(R.id.btnAddBlk);
        mEditTextPhone =bottomSheetDialog.findViewById(R.id.edtTxtPhone);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent bundle = getIntent().putExtra("block_number",mEditTextPhone.getText());
//                bundle.putExtra("block_number",mEditTextPhone.getText());
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("block_number", String.valueOf(mEditTextPhone.getText()));
                editor.apply();
                Toast.makeText(getApplicationContext(),mEditTextPhone.getText()+" Blocked",Toast.LENGTH_LONG).show();
            }
        });
        bottomSheetDialog.show();
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