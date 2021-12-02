package com.example.power_caller.component;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.power_caller.R;

public class CustomDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        Bundle bundle = getIntent().getExtras();


        new AlertDialog.Builder(this)
                .setMessage("Icomming call from " + bundle.getString("msg"))
                .setTitle("Power Caller")
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
//                                Toast.makeText(this,"Yes is clicked",Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context,"cancel is clicked",Toast.LENGTH_LONG).show();
                    }
                })

                .create()
                .show();
    }
}