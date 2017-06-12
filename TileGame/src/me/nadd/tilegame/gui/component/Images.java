/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nadd.tilegame.gui.component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import static me.nadd.tilegame.IOUtil.ioResourceToByteBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_info_from_memory;
import static org.lwjgl.stb.STBImage.stbi_is_hdr_from_memory;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

/**
 *
 * @author voice
 */
public enum Images {
    WALL("lwjgl33"),
    BLANK("lwjgl35"),
    GOAL("lwjgl34"),
    TEST("lwjgl32");
    
    
    private final ByteBuffer buffer;
    private final int width;
    private final int height;
    private final int comp;
    private final int textureId;
    
    Images(String fileName){
        ByteBuffer imageBuffer;
        
        try {
            imageBuffer = ioResourceToByteBuffer("C:\\Users\\voice\\Desktop\\" + fileName + ".png", 8 * 1024);
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
        this.buffer = stbi_load_from_memory(imageBuffer, w, h, comp, 0);
        if (this.buffer == null)
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());

        this.width = w.get(0);
        this.height = h.get(0);
        this.comp = comp.get(0);
        this.textureId = glGenTextures();
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getTextureId() {
        return textureId;
    }
    
    public int getComp() {
        return comp;
    }
}
