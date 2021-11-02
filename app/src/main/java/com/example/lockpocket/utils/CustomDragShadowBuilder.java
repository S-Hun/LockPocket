package com.example.lockpocket.utils;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class CustomDragShadowBuilder extends View.DragShadowBuilder {

    public CustomDragShadowBuilder(View v) {
        super(v);
    }

    @Override
    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
        super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        final View view = getView();
        if(view != null) {
            outShadowSize.set(view.getWidth(), view.getHeight());
            outShadowTouchPoint.set(64, 64);
        }
    }
}
