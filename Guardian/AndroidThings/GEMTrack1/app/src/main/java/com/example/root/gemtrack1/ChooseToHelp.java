package com.example.root.gemtrack1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.root.gemtrack.R;

import static android.content.ContentValues.TAG;

public class ChooseToHelp extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);
        Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
//              Intent myintent2 = new Intent(ChooseToHelp.this,MapsActivity.class);
//              startActivity(myintent2);
                Log.e(TAG, "onClick: YESYESYES");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:query=12.971891,77.641154"));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(ChooseToHelp.this,MainActivity.class);
                startActivity(intent1);
                Log.e(TAG, "onClick: NONONONO");
            }
        });
    }
}