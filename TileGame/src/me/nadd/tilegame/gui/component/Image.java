package me.nadd.tilegame.gui.component;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.io.*;
import java.nio.*;

import static java.lang.Math.*;
import me.nadd.tilegame.GameRender;
import static me.nadd.tilegame.IOUtil.ioResourceToByteBuffer;
import me.nadd.tilegame.gui.GUI;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;


/**
 * insert relevant informations
 * @author i need healing
 */
public final class Image extends GuiComponent {

    private final ByteBuffer image;

    private final int w;
    private final int h;
    private final int comp;

    private long window;
    private int ww = 800;
    private int wh = 600;

    private int scale;
    private int texID;

    public Image(int x, int y, String imagePath) {
        super(x, y);
        
        ByteBuffer imageBuffer;
        try {
            imageBuffer = ioResourceToByteBuffer(imagePath, 8 * 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer w    = BufferUtils.createIntBuffer(1);
        IntBuffer h    = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        // Use info to read image metadata without decoding the entire image.
        // We don't need this for this demo, just testing the API.
        if (!stbi_info_from_memory(imageBuffer, w, h, comp)) {
            throw new RuntimeException("Failed to read image information: " + stbi_failure_reason());
        }

        System.out.println("Image width: " + w.get(0));
        System.out.println("Image height: " + h.get(0));
        System.out.println("Image components: " + comp.get(0));
        System.out.println("Image HDR: " + stbi_is_hdr_from_memory(imageBuffer));

        // Decode the image
        image = stbi_load_from_memory(imageBuffer, w, h, comp, 0);
        if (image == null) {
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());
        }

        this.w = w.get(0);
        this.h = h.get(0);
        this.comp = comp.get(0);
        this.window = GameRender.getWindow();
        
        this.texID = glGenTextures();
    }

    private void setScale(int scale) {
        this.scale = max(-3, scale);
    }
    
    public void render(GUI gui) {
        gui.setDrawColor(1, 1, 1);
        glBindTexture(GL_TEXTURE_2D, texID);
        
        if (comp == 3) {
            if ((w & 3) != 0) {
                glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (w & 1));
            }
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, w, h, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

        glEnable(GL_TEXTURE_2D);
        
        glClear(GL_COLOR_BUFFER_BIT);

        float scaleFactor = 1.0f + scale * 0.25f;
            
        glScalef(scaleFactor, scaleFactor, 1f);

        glBegin(GL_QUADS);
        {
            glTexCoord2f(0.0f, 0.0f);
            glVertex2f(0.0f, 0.0f);

            glTexCoord2f(1.0f, 0.0f);
            glVertex2f(w, 0.0f);

            glTexCoord2f(1.0f, 1.0f);
            glVertex2f(w, h);

            glTexCoord2f(0.0f, 1.0f);
            glVertex2f(0.0f, h);
        }
        glEnd();
        
        glDisable(GL_TEXTURE_2D);
    }

    @Override
    public void destroy() {
        stbi_image_free(image);

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    @Override
    public void onMouseClick(GUI gui) {
        
    }
}