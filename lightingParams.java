package finalProj;
//

// lightingParams.java
//
// Simple class for setting up the viewing and projection transforms
// for the Shading Assignment.
//
// Students are to complete this class.
//
//@author-Pranay Shashank Adavi
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*; 

public class lightingParams
{
    // Add any global class variables you need here.
	//Input Lighting Parameters
	public float [] ambientColor={0.8f, 0.8f, 0.8f, 1.0f};
	public float [] diffuseColor={0.8f, 0.8f, 0.8f, 1.0f};
	
	
	public float ka;
	public float kd;
		
	public float[] lightPosition={-1.6f,3.0f,1.9f,1.0f};
	public float[] lightColor={0.8f, 0.8f, 0.8f, 1.0f};
	public float[] colorAmbientLight={ 0.5f, 0.5f, 0.5f, 0.5f};
	
	 

    /**
     * constructor
     */
    public lightingParams()
    {
    	//Applying material coefficients for all effects
    	ka=0.5f;
    	kd=0.7f;
    	    	
      
    }
    /**
     * This functions sets up the lighting, material, and shading parameters
     * for the Phong shader.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     * parameter values are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpPhong (int program, GL2 gl2)
    {
    	//Passing values to the shader
    	int ambiColor = gl2.glGetUniformLocation (program, "ambientColor");
        gl2.glUniform4fv (ambiColor, 1, ambientColor, 0);
        
        int diffColor = gl2.glGetUniformLocation (program, "diffuseColor");
        gl2.glUniform4fv (diffColor, 1, diffuseColor, 0);
        
        
        int coeeficent = gl2.glGetUniformLocation (program, "ka");
        gl2.glUniform1f(coeeficent,ka);
        
        coeeficent = gl2.glGetUniformLocation (program, "kd");
        gl2.glUniform1f(coeeficent,kd);
        
        
        
        int lightPos = gl2.glGetUniformLocation (program, "lightPosition");
        gl2.glUniform4fv (lightPos, 1, lightPosition, 0);
        
        int lightCol = gl2.glGetUniformLocation (program, "lightColor");
        gl2.glUniform4fv (lightCol, 1, lightColor, 0);
        
        int colorAmbi = gl2.glGetUniformLocation (program, "colorAmbientLight");
        gl2.glUniform4fv (colorAmbi, 1, colorAmbientLight, 0);
        
        // Add your code here.
    }
}
