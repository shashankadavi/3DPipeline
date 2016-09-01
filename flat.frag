#version 120

//@author-Pranay Shashank Adavi
// Flat shading fragment shader
//common data between vertex shader and fragment shader

varying vec4 finalLight;
void main()
{
	   //assigning the fragment color
       gl_FragColor = finalLight;
}
