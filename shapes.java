package finalProj;
import java.util.ArrayList;

//
//  shapes.java
//
//  Students should not be modifying this file.
//  @author Pranay Shashank Adavi
//  @author Vasudev
//

public class shapes extends simpleShape {

    /**
     * Object selection variables
     */
    public static final int OBJ_CYLINDER = 0;
    public static final int OBJ_CUBE = 1;
    public static final int OBJ_SPHERE=2;
    public static final int OBJ_CUBE2=3;
    public static final int OBJ_CUBE3=4;

    /**
     * Shading selection variables
     */
    public static final int SHADE_FLAT = 0;
    

    /**
     * Constructor
     */
    public shapes() {
    }
    /**
	 * makeCube - Create a unit cube, centered at the origin, with a given
	 * number of subdivisions in each direction on each face.
	 * 
	 * @param subdivision
	 *            - number of equal subdivisons to be made in each direction
	 *            along each face
	 * 
	 *            Can only use calls to addTriangle()
	 */
  
 
    public void makeCube(int subdivisions) {
		if (subdivisions < 1)
			subdivisions = 1;

		// One side of cube is unit length
		float div = 1.0f / subdivisions;
		// Current point
		float y = -0.5f + div;
		// First or previous point
		float y1 = -0.5f;
		float z = 0.5f;

		while (y <= 0.5f) {
			float x = -0.5f + div;
			float x1 = -0.5f;

			while (x <= 0.5f) {

				// Making a quadrilateral using the current two points and
				// previous two points
				// for all faces of cube
				addQuad(x1, y1, z, x, y1, z, x, y, z, x1, y, z);
				addQuad(x1, -z, y1, x, -z, y1, x, -z, y, x1, -z, y);
				addQuad(z, x1, y1, z, x, y1, z, x, y, z, x1, y);

				addQuad(x1, y, -z, x, y, -z, x, y1, -z, x1, y1, -z);
				addQuad(x1, z, y, x, z, y, x, z, y1, x1, z, y1);
				addQuad(-z, x1, y, -z, x, y, -z, x, y1, -z, x1, y1);

				x1 = x;
				x = x + div;

			}
			// Making current points as previous
			y1 = y;
			y = y + div;

		}

	}
    /**
	 * makeSphere - Create sphere of a given radius, centered at the origin,
	 * using spherical coordinates with separate number of thetha and phi
	 * subdivisions.
	 * 
	 * @param radius
	 *            - Radius of the sphere
	 * @param slides
	 *            - number of subdivisions in the theta direction
	 * @param stacks
	 *            - Number of subdivisions in the phi direction.
	 * 
	 *            Can only use calls to addTriangle
	 */
    
    public void makeSphere(float radius, int slices, int stacks) {
		if (slices < 3)
			slices = 3;

		if (stacks < 3)
			stacks = 3;

		float heightDivisions = slices;
		float radialDivisions = stacks;
		// Total height is 1 unit
		float hDiv = 1.0f / heightDivisions;
		// retrieving the x,z, points for base circle
		float[][] coord = calRadius(0f, radialDivisions);
		// Starting point
		float y = -0.5f;
		// Point at next division
		float y1;

		for (int i = 1; i <= heightDivisions; i++) {
			// Calculating the location of the next division
			y1 = y + hDiv;
			// finding the new radius for the new disk
			float newRadius = sphereRadius(radius, y1);
			// retrieving the x,z, points for one division
			float[][] coord1 = calRadius(newRadius, radialDivisions);

			for (int j = 1; j <= coord.length; j++) {
				// Making a quadrilateral using the current two points and
				// previous two points
				addQuad(coord[j - 1][0], y, coord[j - 1][1], coord[j
						% coord.length][0], y, coord[j % coord.length][1],
						coord1[j % coord.length][0], y1, coord1[j
								% coord.length][1], coord1[j - 1][0], y1,
						coord1[j - 1][1]);

			}
			// Making current points as previous
			coord = coord1;
			y = y1;

		}

	}
    /**
	 * makeCylinder - Create polygons for a cylinder with unit height, centered
	 * at the origin, with separate number of radial subdivisions and height
	 * subdivisions.
	 * 
	 * @param radius
	 *            - Radius of the base of the cylinder
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 * 
	 *            Can only use calls to addTriangle()
	 */
    public void makeCylinder(float radius, int radialDivisions,
			int heightDivisions) {
		if (radialDivisions < 3)
			radialDivisions = 3;

		if (heightDivisions < 1)
			heightDivisions = 1;
		// Previous point
		float x;
		float z;
		// Current point
		float x1;
		float z1;
		// Total height is 1 unit
		float hDiv = (float) 1.0f / heightDivisions;
		float[][] coord = calRadius(radius, radialDivisions);

		// Point at next division
		float y1 = 0;
		// Starting point
		float y = -0.5f;

		for (int i = 1; i <= heightDivisions; i++) {
			// retrieving the x,z, points for base circle
			x = coord[0][0];
			z = coord[0][1];

			for (int j = 1; j <= coord.length; j++) {
				// Calculating the location of the next division
				y1 = y + hDiv;

				x1 = coord[j % coord.length][0];
				z1 = coord[j % coord.length][1];
				// Making a quadrilateral using the current two points and
				// previous two points
				addQuad(x, y, z, x1, y, z1, x1, y1, z1, x, y1, z);

				x = x1;
				z = z1;
			}
			// Making current points as previous
			y = y1;
		}
		// Constructing the base and top of cylinder
		x = coord[0][0];
		z = coord[0][1];
		y = -0.5f;
		y1 = 0.5f;

		for (int j = 1; j <= coord.length; j++) {

			x1 = coord[j % coord.length][0];
			z1 = coord[j % coord.length][1];

			// Call to addTriangle for front and back faces
			addTriangle(x1, y1, z1, 0, y1, 0, x, y1, z);

			addTriangle(x, y, z, 0, y, 0, x1, y, z1);

			x = x1;
			z = z1;
		}

	}


 //Make a cylinder object at one time
    private void makeCylinders( int shadingType ) {

        if( shadingType == shapes.SHADE_FLAT ) {
        	makeCylinder(0.5f,53,30);

          

        } 
    }

    //Make a cube object at one time
    private void makeCubes( int shadingType ) {

        if( shadingType == shapes.SHADE_FLAT ) {

        	makeCube(1);
  

        } 
        
      
    }
    
   // construct the individual shapes and objects
    

    public void makeShape( int choice, int shadingType ) {
        if( choice == shapes.OBJ_CYLINDER ){
            makeCylinders( shadingType );
        }
        else if(choice== shapes.OBJ_CUBE)
        { makeCubes( shadingType );
        }else if(choice==shapes.OBJ_SPHERE){
        	//make a cylinder object
        	makeSphere(0.5f,75,55);
        }else if(choice==shapes.OBJ_CUBE2){
        	makeCubes( shadingType );
        }else{
        	makeCubes( shadingType );
        }
    }
    
    /**
	 * addQuad - Create a quadrilateral of the four points sent by making two
	 * triangles from the four points
	 * 
	 * @param x0
	 *            ,y0,z0,x1,y1,z1,x2,y2,z2,x3,y3,z3 coordinates of points for
	 *            adding triangle
	 * 
	 */
    
    public void addQuad(float x0, float y0, float z0, float x1, float y1,
			float z1, float x2, float y2, float z2, float x3, float y3, float z3) {

		// Calls to addTriangle()
		addTriangle(x0, y0, z0, x1, y1, z1, x2, y2, z2);
		addTriangle(x2, y2, z2, x3, y3, z3, x0, y0, z0);

	}

	/**
	 * calRadius - Calculate the radius for the circle/disk of a cylinder,cone
	 * or a sphere that needs to be tesselated
	 * 
	 * @param radius
	 *            the radius of the circle
	 * @param radialDivisions
	 *            the number of radial divisions
	 */

    
    public float[][] calRadius(float radius, float radialDivisions) {
		ArrayList<Float> xvalue = new ArrayList<Float>();
		ArrayList<Float> zvalue = new ArrayList<Float>();

		// Dividing 360 in radians with the no of divisions
		float rDiv = 6.28318531f / radialDivisions;

		// calculating the point using the parametric equation
		for (int i = 0; i < radialDivisions; i++) {
			xvalue.add((float) (radius * Math.cos(i * rDiv)));
			zvalue.add((float) (radius * Math.sin(i * rDiv)));
		}

		float[][] coord = new float[xvalue.size()][2];
		// adding the x,z points to a 2-D array
		for (int i = 0; i < xvalue.size(); i++) {
			coord[i][0] = xvalue.get(i);
			coord[i][1] = zvalue.get(i);

		}

		return coord;

	}
    /**
	 * sphereRadius - Calculate the radius of a secondary disk of the sphere
	 * 
	 * @param radius
	 *            The radius of the circle
	 * @param y1
	 *            The next point moving up
	 * 
	 */
    public float sphereRadius(float radius, float y1) {
		// If the next point is in the lower sub-sphere
		if (y1 < 0) {
			y1 = -y1;
		}

		return (float) Math.sqrt(radius * radius - y1 * y1);
	}

}
