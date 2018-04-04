package aiofwa.weseehim;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(3); // EGL 3.0!
        Renderer renderer = new Renderer();
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);
    }
}
