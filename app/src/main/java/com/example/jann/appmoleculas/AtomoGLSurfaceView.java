package com.example.jann.appmoleculas;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jann on 10/04/2017.
 */

public class AtomoGLSurfaceView extends GLSurfaceView {

    public AtomoGLSurfaceView(Context context) {
        super(context);
        this.setRenderer(new AtomoRenderer());
    }

    public AtomoGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setRenderer(new AtomoRenderer());
    }

}