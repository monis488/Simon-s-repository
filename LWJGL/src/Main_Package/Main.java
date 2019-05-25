package Main_Package;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;



public class Main {

	
	
public Main() {
	
	if(!glfwInit()) {
		
		System.err.println("testing");
		System.exit(1);
		
		}
	
	long win = glfwCreateWindow(640,480,"LWJGL GAME",0,0);
		
	glfwShowWindow(win);
	glfwMakeContextCurrent(win);
	GL.createCapabilities();
	
	//temp
	float x = 0;
	float y = 0;
	
	float red = 1;
	float blue =1;
	//temp
	
	while(!glfwWindowShouldClose(win)) {
		
		glfwPollEvents();
		
	//////////////////////////////////////////////////	
		glClear(GL_COLOR_BUFFER_BIT);
		glBegin(GL_QUADS);
		
		if(glfwGetKey(win,GLFW_KEY_W) == GLFW_TRUE) { y+=.0001f; }
		if(glfwGetKey(win,GLFW_KEY_A) == GLFW_TRUE) { x-=.0001f; }
		if(glfwGetKey(win,GLFW_KEY_S) == GLFW_TRUE) { y-=.0001f; }
		if(glfwGetKey(win,GLFW_KEY_D) == GLFW_TRUE) { x+=.0001f; }
		if(glfwGetKey(win,GLFW_KEY_F) == GLFW_TRUE) { glColor4f(red,1,blue,1); }
		
		glColor4f(red,0,blue,0);
		glVertex2f(-0.5f+x,0.5f+y);
		
		
		glVertex2f(0.5f +x,0.5f+y);
		
		
		glVertex2f(0.5f +x,-0.5f +y);
		
		
		glVertex2f(-0.5f +x,-0.5f +y);
		glEnd();
		
	/////////////////////////////////////////////////	
		glfwSwapBuffers(win);
		
	}
}
		public static void main(String [] args) {
			new Main();
		}
	
	
}

