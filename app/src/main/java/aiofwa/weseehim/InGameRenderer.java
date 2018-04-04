package aiofwa.weseehim;

import android.opengl.GLES30;
import android.provider.Settings;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by 42yeah on 2018/4/4.
 */

public class InGameRenderer implements GameRenderer {
    final int FLOATSIZ = 4;
    FloatBuffer triangle;

    String vertexShader =
            "#version 300 es\n" +
            "\n" +
            "layout (location = 0) in vec2 aPos;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "   gl_Position = vec4(aPos, 0.0, 1.0);\n" +
            "}\n" +
            "";

    String fragmentShader =
            "#version 300 es\n" +
            "\n" +
            "out vec4 fragColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "   fragColor = vec4(0.1, 0.7, 0.2, 1.0);\n" +
            "}\n";

    int prog;

    @Override
    public void init() {
        float[] triangle_buffer = new float[] {
                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f
        };
        triangle = ByteBuffer.allocateDirect(FLOATSIZ * triangle_buffer.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
        triangle.put(triangle_buffer);
        triangle.position(0);

        int v, f;
        v = GLES30.glCreateShader(GLES30.GL_VERTEX_SHADER);
        GLES30.glShaderSource(v, vertexShader);
        GLES30.glCompileShader(v);
        String vinfo = GLES30.glGetShaderInfoLog(v);

        f = GLES30.glCreateShader(GLES30.GL_FRAGMENT_SHADER);
        GLES30.glShaderSource(f, fragmentShader);
        GLES30.glCompileShader(f);
        String finfo = GLES30.glGetShaderInfoLog(f);

        Log.d("vinfo", vinfo);
        Log.d("finfo", finfo);
        prog = GLES30.glCreateProgram();
        GLES30.glAttachShader(prog, v);
        GLES30.glAttachShader(prog, f);
        GLES30.glLinkProgram(prog);

        String pinfo = GLES30.glGetProgramInfoLog(prog);
        Log.d("pinfo", pinfo);
    }

    @Override
    public void render() {
        GLES30.glVertexAttribPointer(0, 2, GLES30.GL_FLOAT, false, FLOATSIZ * 2, triangle);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glUseProgram(prog);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);
    }
}
