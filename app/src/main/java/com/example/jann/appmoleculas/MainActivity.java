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
    private int cHidrogenio=-1, cOxigenio=-1, cCloro=-1, cSodio=-1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atomoRenderer = new AtomoRenderer(true);
        //Chamando a area designada para o openGL
        GLSurfaceView glView = (GLSurfaceView)findViewById(R.id.GLView);
        glView.setRenderer(atomoRenderer);

        //App em tela cheia
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Botão que designa qual átomo vai ser movido
        Button bH = (Button) findViewById(R.id.bH);
        bH.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                if(cHidrogenio<1){
                    cHidrogenio++;
                    cOxigenio=-1;
                    cCloro=-1;
                    cSodio=-1;
                }
                else{
                    cHidrogenio=0;
                }
                Toast.makeText(getApplicationContext(), "Movendo o hidrogênio " + cHidrogenio, Toast.LENGTH_SHORT).show();
            }
        });
        Button bO = (Button) findViewById(R.id.bO);
        bO.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                if(cOxigenio<1){
                    cOxigenio++;
                    cHidrogenio=-1;
                    cCloro=-1;
                    cSodio=-1;
                }
                else{
                    cOxigenio=0;
                }
                Toast.makeText(getApplicationContext(), "Movendo o oxigênio " + cOxigenio, Toast.LENGTH_SHORT).show();
            }
        });
        Button bS = (Button) findViewById(R.id.bS);
        bS.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                if(cSodio<1){
                    cOxigenio=-1;
                    cHidrogenio=-1;
                    cCloro=-1;
                    cSodio++;
                }
                else{
                    cSodio=0;
                }
                Toast.makeText(getApplicationContext(), "Movendo o sodio " + cSodio, Toast.LENGTH_SHORT).show();
            }
        });

        Button bC = (Button) findViewById(R.id.bC);
        bC.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                if(cCloro<1){
                    cOxigenio=-1;
                    cHidrogenio=-1;
                    cCloro++;
                    cSodio=-1;
                }
                else{
                    cCloro=0;
                }
                Toast.makeText(getApplicationContext(), "Movendo o cloro " + cCloro, Toast.LENGTH_SHORT).show();
            }
        });
        //Operação de toque na tela
        glView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                float x = e.getX();
                float y = e.getY();

                switch (e.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        float dx = x - mPreviousX;
                        float dy = y - mPreviousY;
                        if (cOxigenio==0) { //Oxigenio
                            atomoRenderer.setX(atomoRenderer.getX() - (dx * TOUCH_SCALE_FACTOR));
                            atomoRenderer.setY(atomoRenderer.getY() - (dy * TOUCH_SCALE_FACTOR));
                        }
                        if (cHidrogenio==0){ //Hidrogenio 1
                            atomoRenderer.setX2(atomoRenderer.getX2() - (dx * TOUCH_SCALE_FACTOR));
                            atomoRenderer.setY2(atomoRenderer.getY2() - (dy * TOUCH_SCALE_FACTOR));
                        } else if (cHidrogenio==1) { //Hidrogenio 2
                            atomoRenderer.setX3(atomoRenderer.getX3() - (dx * TOUCH_SCALE_FACTOR));
                            atomoRenderer.setY3(atomoRenderer.getY3() - (dy * TOUCH_SCALE_FACTOR));
                        }
                        if(cCloro==0){
                            atomoRenderer.setX4(atomoRenderer.getX4() - (dx * TOUCH_SCALE_FACTOR));
                            atomoRenderer.setY4(atomoRenderer.getY4() - (dy * TOUCH_SCALE_FACTOR));
                        }
                        if(cSodio==0){
                            atomoRenderer.setX5(atomoRenderer.getX5() - (dx * TOUCH_SCALE_FACTOR));
                            atomoRenderer.setY5(atomoRenderer.getY5() - (dy * TOUCH_SCALE_FACTOR));
                        }
                        break;
                }

                mPreviousX = x;
                mPreviousY = y;
                x=0;
                y=0;
                return true;
            }
        });
    }

    public boolean intersect(AtomoRenderer atomo1, AtomoRenderer atomo2) {
        // we are using multiplications because it's faster than calling Math.pow
        float distance = (float) Math.sqrt(
                (atomo1.getX() - atomo2.getX2()) * (atomo1.getX() - atomo2.getX2()) +
                (atomo1.getY() - atomo2.getY2()) * (atomo1.getY() - atomo2.getY2()) +
                (atomo1.getZ() - atomo2.getZ2()) * (atomo1.getZ() - atomo2.getZ2())
        );
        if(distance < (atomo1.getZ() + atomo2.getZ2())){
            return true;
        }else{
            return false;
        }
    }


    /*Método chamado quando acontecer uma interrupção como por exemplo:
    Quando alguém ligar, isso fará com que o programa que está rodando
    no momento(pode ser um jogo) entre em modo de pausa(onPause).
    Isso pode acontecer a qualquer momento. Não temos como prever no
    meio do jogo em qual momento entrará uma ligação.*/
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }

    //Para realizar interação com o "Z" do oxigênio - Não está sendo utilizado
    private float transZ(float y, float transZ) {
        float maxTransZ = -3.0f;
        float minTransZ = -20.0f;
        transZ += (YValue - y) / 100;
        transZ = Math.min(maxTransZ, transZ);
        transZ = Math.max(minTransZ, transZ);
        return transZ;
    }

}