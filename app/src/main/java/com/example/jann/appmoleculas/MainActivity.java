package com.example.jann.appmoleculas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    private AtomoGLSurfaceView mGLView;
    private float YValue;
    private float XValue;
    private AtomoRenderer atomoRenderer;

    private static final float TOUCH_SCALE_FACTOR = 0.005f;
    private float mPreviousX;
    private float mPreviousY;
    private int controle=0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atomoRenderer = new AtomoRenderer(true);
        GLSurfaceView glView = (GLSurfaceView)findViewById(R.id.GLView);
        glView.setRenderer(atomoRenderer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button bOk = (Button) findViewById(R.id.bOk);

        bOk.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                if(controle<3){
                    controle++;
                }
                else{
                    controle=0;
                }
                Toast.makeText(getApplicationContext(), "Clicado no controle " + controle, Toast.LENGTH_SHORT).show();
            }
        });
        glView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                float x = e.getX();
                float y = e.getY();

                switch (e.getAction()) {
                    case MotionEvent.ACTION_MOVE:

                        float dx = x - mPreviousX;
                        atomoRenderer.setX(atomoRenderer.getX() - (dx * TOUCH_SCALE_FACTOR));

                        float dy = y - mPreviousY;
                        atomoRenderer.setY(atomoRenderer.getY() - (dy * TOUCH_SCALE_FACTOR));
                }

                mPreviousX = x;
                mPreviousY = y;
                return true;
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }



    private float transZ(float y, float transZ) {
        float maxTransZ = -3.0f;
        float minTransZ = -20.0f;
        transZ += (YValue - y) / 100;
        transZ = Math.min(maxTransZ, transZ);
        transZ = Math.max(minTransZ, transZ);
        return transZ;
    }

}