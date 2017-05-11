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
    private Atomo_azul hidrogenio, hidrogenio2;
    private boolean mBlending = true;
    private float angle;


    public float transY, transZ, transX;
    public float mTransY, mTransX, mTranxZ;
    public float mTransY2, mTransX2, mTranxZ2;
    public float mTransY3, mTransX3, mTranxZ3;
    public float x1, y1, z1;
    public float x2, y2, z2;
    public float x3, y3, z3;

    public AtomoRenderer(boolean useTranslucentBackground) {
        this.useTranslucentBackground = useTranslucentBackground;
        oxigenio = new Atomo(20, 20, .5f, 1.0f);
        oxigenio.setPosition(-0.2f, 5.0f, 0.0f);
        hidrogenio = new Atomo_azul(20, 20, .2f, 1.0f);
        hidrogenio.setPosition(0.0f, 0.0f, 0.0f);
        hidrogenio2 = new Atomo_azul(20,20, .2f, 1.0f);
        hidrogenio2.setPosition(0.0f, 0.0f, 0.0f);
        //transZ = -5.0f; // quanto mais perto do zero, maior fica
    }

    public AtomoRenderer() {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.0f, 0.5f, 0.3f, 5.0f);
        // vermelho, verde, azul, alpha
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
        //+gl.glTranslatef(0.0f, (float) Math.sin(transY), transZ);

        //OXIGENIO
        gl.glPushMatrix();
        //gl.glTranslatef(-2.9f, -0.7f, 0.0f);
        gl.glTranslatef(-mTransX, mTransY, -15.0f);
      //  gl.glRotatef(angle, 1, 0, 0);
      //  gl.glRotatef(angle, 0, 1, 0);
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL11.GL_COLOR_ARRAY);
        executeAtomo(oxigenio, gl);
        gl.glPopMatrix();
        //HIDROGENIO1
        gl.glPushMatrix();
        gl.glTranslatef(2f, 3f, -9.0f);
        executeAtomoA(hidrogenio, gl);
        gl.glPopMatrix();

        //HIDROGENIO2
        gl.glPushMatrix();
        gl.glTranslatef(1f, 3f, -9.0f);
       // gl.glRotatef(angle, 1,0,0);
      //  gl.glRotatef(angle,0,1,0);
        executeAtomoA(hidrogenio2, gl);
        gl.glPopMatrix();
        //mTransY+=.075f;
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
    private void executeAtomoA(Atomo_azul m_Atomo, GL10 gl)    {

        float posX, posY, posZ;
        posX = m_Atomo.m_Pos[0];
        posY = m_Atomo.m_Pos[1];
        posZ = m_Atomo.m_Pos[2];
        gl.glPushMatrix();
        gl.glTranslatef(posX, posY, posZ);
        gl.glRotatef(angle, 1,0,0);
        gl.glRotatef(angle,0,1,0);
        m_Atomo.draw(gl);
        gl.glPopMatrix();
    }
    public float getY() {
        return mTransY;
    }

    public void setY(float mY) {
        mTransY = mY;
    }

    public float getX() {
        return mTransX;
    }

    public void setX(float mX) {
        mTransX = mX;
    }


}