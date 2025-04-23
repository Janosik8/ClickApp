package com.example.clicker;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;


public  class MyAccessibilityService extends AccessibilityService {
    private static MyAccessibilityService instance;

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(getApplicationContext(), "Service connected", Toast.LENGTH_SHORT).show());
    }

    public static MyAccessibilityService getInstance() {
        return instance;
    }

    public void simulateTap(int x, int y) {
        Log.d("siema", "Waiting 1 second before tap...");

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Path clickPath = new Path();
            clickPath.moveTo(x, y);
            GestureDescription.StrokeDescription clickStroke =
                    new GestureDescription.StrokeDescription(clickPath, 0, 100); // tap duration

            GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
            gestureBuilder.addStroke(clickStroke);

            dispatchGesture(gestureBuilder.build(), new GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    Log.d("siema", "Tap performed!");
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    Log.d("siema", "Tap cancelled");
                }
            }, null);
        }, 1000); // 1 second delay
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}

    @Override
    public void onInterrupt() {}
}