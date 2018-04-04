package aiofwa.weseehim;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 42yeah on 2018/4/4.
 */

public class Renderer implements GLSurfaceView.Renderer {
    InGameRenderer inGameRenderer;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES30.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        inGameRenderer = new InGameRenderer();
        inGameRenderer.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT | GLES30.GL_COLOR_BUFFER_BIT);
        inGameRenderer.render();
    }
}
