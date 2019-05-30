package Main_Package;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

public class Texture {

	int id;
	int width, height;

	/**
	 * Input the path of the file into the constructor The method will then find the
	 * image file then find the files width and height after that the method will
	 * create an array called pixels_raw that can store RGB integers for every pixel
	 * in the image Then 2 for loops will loop through all of the pixels and find
	 * the RGB of every pixel and store them inside the pixels_raw array
	 * 
	 * @param filename
	 */

	public Texture(String filename) {

		BufferedImage bi;

		try {
			bi = ImageIO.read(new File(filename));
			width = bi.getWidth();
			height = bi.getHeight();

			int[] pixels_raw = new int[width * height * 4];
			pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int pixel = pixels_raw[i * width + j];
					pixels.put((byte) ((pixel >> 16) & 0xFF)); // RED
					pixels.put((byte) ((pixel >> 8) & 0xFF)); // GREEN
					pixels.put((byte) (pixel & 0xFF)); // BLUE
					pixels.put((byte) ((pixel >> 24) & 0xFF));// ALPHA
				}
			}

			pixels.flip();

			id = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, id);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * This method will give that specific texture an id so 
	 * could be called upon more easily
	 * 
	 */
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
}
