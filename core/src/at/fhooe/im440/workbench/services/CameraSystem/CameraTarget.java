package at.fhooe.im440.workbench.services.CameraSystem;

import at.fhooe.im440.workbench.components.BaseComponent;
import at.fhooe.im440.workbench.components.Pose;

public class CameraTarget extends BaseComponent {

	private Pose pose;
	
	public CameraTarget(Pose pose) {
		this.pose = pose;
	}
	
	public void setPose(Pose pose) {
		this.pose = pose;
	}
	
	public Pose getPose() {
		return this.pose;
	}
	
	public float getPosX() {
		return pose.getPosX();
	}
	
	public float getPosY() {
		return pose.getPosY();
	}
	
	public float getAngle() {
		return pose.getAngle();
	}

	@Override
	public void addToManager() {
		// TODO add to camera system
	}

	@Override
	public void removeFromManager() {
		// TODO remove from camera system
	}
	
}