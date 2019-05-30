package Main_Package;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.opengl.GL;

public class Main {

	/*
	 * In the constructor we first create a window called win and make it 640 * 480
	 * pixels with the title of "LWJGL GAME" The constructor then shows it We then
	 * create a game loop Also by pressing escape, the window will be destroyed and
	 * the loop will stop running terminating the program The constructor will be
	 * initialized in the main function
	 * 
	 */

	public Main() {

		if (!glfwInit()) {

			System.err.println("testing");
			System.exit(1);

		}

		long win = glfwCreateWindow(640, 480, "LWJGL GAME", 0, 0);

		glfwShowWindow(win);
		glfwMakeContextCurrent(win);
		GL.createCapabilities();

		glEnable(GL_TEXTURE_2D);

		Texture tex = new Texture("./res/test.png");

		// temp
		float x = 0;
		float y = 0;

		// GAME LOOP!!!!
		while (!glfwWindowShouldClose(win)) {

			

			glfwPollEvents();

			if (glfwGetKey(win, GLFW_KEY_W) == GLFW_TRUE) {y += .0001f;}
			if (glfwGetKey(win, GLFW_KEY_S) == GLFW_TRUE) {y -= .0001f;}
			if (glfwGetKey(win, GLFW_KEY_D) == GLFW_TRUE) {x += .0001f;}
			if (glfwGetKey(win, GLFW_KEY_A) == GLFW_TRUE) {x -= .0001f;}
			
			//////////////////////////////////////////////////
			glClear(GL_COLOR_BUFFER_BIT);

			tex.bind();

			glBegin(GL_QUADS);

			glTexCoord2f(0, 0);
			glVertex2f(-0.3f + x, 0.3f + y);
			glTexCoord2f(1, 0);
			glVertex2f(0.3f + x, 0.3f + y);
			glTexCoord2f(1, 1);
			glVertex2f(0.3f + x, -0.3f + y);
			glTexCoord2f(0, 1);
			glVertex2f(-0.3f + x, -0.3f + y);
			glEnd();
			
			glfwSwapBuffers(win);

			if (glfwGetKey(win, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
				glfwDestroyWindow(win);
				break;
			}
			
		}
		/////////////////////////////////////////////////

	}

	public static void main(String[] args) {
		new Main();

	}

}
