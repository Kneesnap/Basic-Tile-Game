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

    private final Images image;
    
    private int x;
    private int y;
    private int size;
    private float scale = 0;

    
    public Image(int x, int y, int size, Images image) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.size = size;
        this.image = image;
    }

    private void setScale(int scale) {
        this.scale = max(-3, scale);
    }
    
    public Image setLoc(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }
    
    public void render(GUI gui) {
        //gui.setDrawColor(1, 1, 1);
        glBindTexture(GL_TEXTURE_2D, image.getTextureId());
        
        if (image.getComp() == 3) {
            if ((image.getWidth() & 3) != 0) {
                glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (image.getWidth() & 1));
            }
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, image.getWidth(), image.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, image.getBuffer());
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image.getBuffer());

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

        glEnable(GL_TEXTURE_2D);
        
        //glClear(GL_COLOR_BUFFER_BIT);

        float scaleFactor = 1.0f + scale * 0.25f;
            
        glScalef(scaleFactor, scaleFactor, 1f);

        glBegin(GL_QUADS);
        {
                //Top Left
                glTexCoord2f(0.0f, 0.0f);
                glVertex2f(x, y);
                
                //Bottom Left
                glTexCoord2f(1.0f, 0.0f);
                glVertex2f(x+size, y);
                
                //Top right
                glTexCoord2f(1.0f, 1.0f);
                glVertex2f(x+size, y+size);
                
                //Bottom right
                glTexCoord2f(0.0f, 1.0f);
                glVertex2f(x, y+size);
        }
        glEnd();
        
        glDisable(GL_TEXTURE_2D);
    }

    @Override
    public void destroy() {
        stbi_image_free(image.getBuffer());
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    
    @Override
    public void onMouseClick(GUI gui) {
        
    }
}