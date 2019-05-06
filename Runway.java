// Jamie Smart
// CS 342

public class Runway {
	
	public boolean planeTakingOff = false; // FLAG FOR WHETHER A PLANE IS TAKING OFF ON THE RUNWAY
	public boolean planeLanding = false;  // FLAG FOR WHETHER A PLANE IS LANDING ON THE RUNWAY
	public Plane plane;  // PLANE ON THE RUNWAY
	
	
	public boolean isEmpty(){
		// RETURNS WHETHER THERE IS A PLANE ON THE RUNWAY (FALSE) OR NOT (TRUE)
		
		if (!planeTakingOff & !planeLanding) {  
			// IF THERE IS NOT A PLANE TAKING OFF OR LANDING ON THE RUNWAY, RETURN TRUE
			return true;
		}  // END if (!planeTakingOff & !planeLanding)
		
		return false;
	}  // END isEmpty()
	
	public void takeOff(Plane plane) {
		// SIMULATES A PLANE TAKING OFF OF THE RUNWAY
		this.plane = plane;  // ASSIGN THE PLANE TO THE RUNWAY TO TAKE OFF
		planeTakingOff = true;  // SET THE FLAG FOR A PLANE TAKING OFF
		
		if (plane.takeOffTimeLeft() == 0) {
			// DECREMENTS THE TIME LEFT FOR THE PLANE TO TAKE OFF BY 1 MIN. AND TESTS FOR 
			// WHETHER THE PLANE HAS FINISHED TAKING OFF
			// IF THE PLANE HAS TAKEN OFF REMOVE THE PLANE FROM THE RUNWAY AND CHANGE FLAG TO FALSE
			
			planeTakingOff = false;
			removePlaneFromRunway();
		}  // END if (plane.takeOffTimeLeft() == 0)
		
	}  // END takeOff(Plane plane)
	
	public void land(Plane plane) {
		// SIMULATES A PLANE LANDING ON THE RUNWAY
		this.plane = plane;  // ASSIGN THE PLANE TO THE RUNWAY TO LAND
		planeLanding = true;  // SET THE FLAG FOR A PLANE LANDING
		
		if (plane.landingTimeLeft() == 0) {
			// DECREMENTS THE TIME LEFT FOR THE PLANE TO LAND BY 1 MIN. AND TESTS FOR 
			// WHETHER THE PLANE HAS FINISHED LANDING
			// IF THE PLANE HAS LANDED REMOVE THE PLANE FROM THE RUNWAY AND CHANGE FLAG TO FALSE			
			
			planeLanding = false;
			removePlaneFromRunway();
		}  // END if (plane.landingTimeLeft() == 0)

	}  // END land(Plane plane)
	
	public boolean planeIsTakingOff() {
		// RETURN WHETHER A PLANE IS TAKING OFF ON THE RUNWAY
		
		return planeTakingOff;
	}  // END planeIsTakingOff()
	
	public boolean planeIsLanding() {
		// RETURN WHETHER A PLANE IS LANDING ON THE RUNWAY
		
		return planeLanding;
	}  // END planeIsLanding()
	
	
	public void removePlaneFromRunway() {
		//REMOVE A PLANE FROM THE RUNWAY AND SET THE LANDING AND TAKE OFF FLAGS TO FALSE
		
		planeLanding = false;
		planeTakingOff = false;
		plane = null;
	}  // END removePlaneFromRunway()

	
} // end class Runway
