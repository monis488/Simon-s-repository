package Main_Package;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class Main {

	/*
	 * In the constructor we first create a window called win and make it 640 * 480
	 * pixels with the title of "LWJGL GAME" The constructor then shows it
	 * We then create a game loop 
	 * Also by pressing escape, the window will be destroyed and the loop 
	 * will stop running terminating the program
	 * The constructor will be initialized in the main function
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

		//GAME LOOP!!!!
		while (!glfwWindowShouldClose(win)) {

			glfwPollEvents();

			//////////////////////////////////////////////////
			glClear(GL_COLOR_BUFFER_BIT);
			glBegin(GL_QUADS);
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
