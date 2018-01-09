package com.example.root.gemtrack1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.os.Bundle;


import com.example.root.gemtrack.R;
import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.pio.Gpio;

import java.io.IOException;
import java.lang.String;
import java.util.List;

import static android.content.ContentValues.TAG;
/**
 * Created by root on 27/6/17.
 */

public class Buttonchoice extends Activity{
    //Driver for the doorbell button;
    private Button mButton;
    private Button mButton1;
    //The GPIO pin to activate for button presses
    private final String BUTTON_GPIO_PIN = "BCM4";
    private final String BUTTON_GPIO_PIN1 = "BCM5";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button1);
        Log.d(TAG, "Button wali Activity created.");
        // Initialize the doorbell button driver
        try {
            mButton = new Button(BUTTON_GPIO_PIN, Button.LogicState.PRESSED_WHEN_LOW);
            mButton.setOnButtonEventListener(mButtonCallback);
        } catch (IOException e) {
            Log.e(TAG, "button driver error", e);
        }

        try {
            mButton1 = new Button(BUTTON_GPIO_PIN1, Button.LogicState.PRESSED_WHEN_LOW);
            mButton1.setOnButtonEventListener(mButtonCallback1);
        } catch (IOException e) {
            Log.e(TAG, "button driver error", e);
        }
    }

    /**
     * Callback for button events.
     */
    private Button.OnButtonEventListener mButtonCallback = new Button.OnButtonEventListener() {
        @Override
        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                Log.d(TAG, "YES button pressed");
                Intent myintent3 = new Intent(Buttonchoice.this,GpsActivity.class);
                startActivity(myintent3);
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("google.navigation:query=12.971891,77.641154"));
//                intent.setPackage("com.google.android.apps.maps");
//                startActivity(intent);

            }
        }
    };

    private Button.OnButtonEventListener mButtonCallback1 = new Button.OnButtonEventListener() {
        @Override
        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                Log.d(TAG, "NO button pressed");
            }
        }
    };
}