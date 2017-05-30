package com.example.jann.appmoleculas;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import android.opengl.GLES20;
/**
 * Created by Jann on 10/04/2017.
 */

public class AtomoRenderer implements GLSurfaceView.Renderer {

    private static final float fieldOfView = 30.0f / 57.3f;
    private static final float zNear = .1f;
    private static final float zFar = 1000;
    private boolean useTranslucentBackground;
    private Atomo oxigenio;
    private Atomo hidrogenio, hidrogenio2;
    private Atomo  sodio;
    private Atomo cloro;
    private boolean mBlending = true;
    private float angle;


    public float transY, transZ, transX;
    public float mTransY, mTransX, mTransZ = -15.0f,
            mTransY2, mTransX2=2.5f, mTransZ2,
            mTransY3, mTransX3=2.5f, mTransZ3,
            mTransX4, mTransY4, mTransZ4,
            mTransX5, mTransY5, mTransZ5;
    public float x1, y1, z1;
    public float x2, y2, z2;
    public float x3, y3, z3;

    public AtomoRenderer(boolean useTranslucentBackground) {
        this.useTranslucentBackground = useTranslucentBackground;
        oxigenio = new Atomo(20, 20, .5f, 1.0f, 1);
        oxigenio.setPosition(-1.2f, 5.0f, 0.0f);

        hidrogenio = new Atomo(20, 20, .2f, 1.0f, 3);
        hidrogenio.setPosition(0.5f, 3f, 0.0f);

        hidrogenio2 = new Atomo(20,20, .2f, 1.0f, 3);
        hidrogenio2.setPosition(1f, 3f, 0.0f);

        sodio = new Atomo(30,30,1.0f, 1.0f, 4);
        sodio.setPosition(0.5f, 3.2f, 0.0f);

        cloro = new Atomo(30, 30, 0.8f, 1.0f, 2);
        cloro.setPosition(2.7f, 3.2f, 0.0f);

        //transZ = -5.0f; // quanto mais perto do zero, maior fica
    }

    public AtomoRenderer() {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.0f, 0.5f, 0.3f, 5.0f);
        // vermelho, verde, azul, alpha
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();

        //OXIGENIO
        gl.glPushMatrix();
        gl.glTranslatef(-mTransX, mTransY, mTransZ);
      //  gl.glRotatef(angle, 1, 0, 0);
      //  gl.glRotatef(angle, 0, 1, 0);
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL11.GL_COLOR_ARRAY);
        executeAtomo(oxigenio, gl);
        gl.glPopMatrix();

        //HIDROGENIO1
        gl.glPushMatrix();
        gl.glTranslatef(-mTransX2, mTransY2, -9.0f);
        executeAtomo(hidrogenio, gl);
        gl.glPopMatrix();


        //HIDROGENIO2
        gl.glPushMatrix();
        gl.glTranslatef(-mTransX3, mTransY3, -9.0f);
        executeAtomo(hidrogenio2, gl);
        gl.glPopMatrix();
        //mTransY+=.075f;

        //SODIO

        gl.glPushMatrix();
        gl.glTranslatef(-mTransX4, mTransY4, -12.0f);
        executeAtomo(sodio, gl);
        gl.glPopMatrix();

        //CLORO
        gl.glPushMatrix();
        gl.glTranslatef(-mTransX5, mTransY5, -13.0f);
        executeAtomo(cloro, gl);
        gl.glPopMatrix();

        angle+=.4;
    }
    public void movimentoAtomo(){

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glEnable(GL10.GL_NORMALIZE);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        float aspectRatio = (float) width / (float) height;
        float size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));
        gl.glFrustumf(-size, size, -size / aspectRatio, size / aspectRatio, zNear, zFar);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        if (useTranslucentBackground) {
            gl.glClearColor(0, 0, 0, 0);
        } else {
            gl.glClearColor(1, 1, 1, 1);
        }
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
    }
    private void executeAtomo(Atomo m_Atomo, GL10 gl)    {

        float posX, posY, posZ;
        posX = m_Atomo.m_Pos[0];
        posY = m_Atomo.m_Pos[1];
        posZ = m_Atomo.m_Pos[2];
        gl.glPushMatrix();
        gl.glTranslatef(posX, posY, posZ);
        m_Atomo.draw(gl);
        gl.glPopMatrix();
    }



    public float getY() {
        return mTransY;
    }

    public void setY(float mY) {
        mTransY = mY;
    }

    public float getZ() { return mTransZ; }

    public void setZ(float mZ ) { mTransZ = mZ;}

    public float getX() {
        return mTransX;
    }

    public void setX(float mX) {
        mTransX = mX;
    }

    public float getY2() {
        return mTransY2;
    }

    public void setY2(float mY) {
        mTransY2 = mY;
    }

    public float getZ2() { return mTransZ2; }

    public void setZ2(float mZ ) { mTransZ2 = mZ;}

    public float getX2() {
        return mTransX2;
    }

    public void setX2(float mX) {
        mTransX2 = mX;
    }

    public float getY3() {
        return mTransY3;
    }

    public void setY3(float mY) {
        mTransY3 = mY;
    }

    public float getZ3() { return mTransZ3; }

    public void setZ3(float mZ ) { mTransZ3 = mZ;}

    public float getX3() {
        return mTransX3;
    }

    public void setX3(float mX) { mTransX3 = mX; }

    public float getX4() {
        return mTransX4;
    }

    public void setX4(float mX) { mTransX4 = mX; }

    public float getY4() {
        return mTransY4;
    }

    public void setY4(float mY) {
        mTransY4 = mY;
    }

    public float getY5() {
        return mTransY5;
    }

    public void setY5(float mY) {
        mTransY5 = mY;
    }

    public float getX5() {
        return mTransX5;
    }

    public void setX5(float mX) { mTransX5 = mX; }


}