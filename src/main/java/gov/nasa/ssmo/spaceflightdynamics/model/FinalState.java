package gov.nasa.ssmo.spaceflightdynamics.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class FinalState 
{
	Vector3D position;
	Vector3D velocity;
	
	public FinalState(Vector3D position, Vector3D velocity) {
		super();
		this.position = position;
		this.velocity = velocity;
	}		
	public Vector3D getPosition() {
		return position;
	}
	public Vector3D getVelocity() {
		return velocity;
	}
	@Override
	public String toString() {
		return "FinalState [position=" + position + ", velocity="
				+ velocity + "]";
	}
}
