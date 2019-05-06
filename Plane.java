// Jamie Smart
// CS 342

public class Plane {
	
	private int queueEntryTime;  // WHEN THE PLANE ENTERED A QUEUE
	private int landingTime;  // HOW LONG IT TAKES A PLANE TO LAND
	private int takeoffTime;  // HOW LONG IT TAKES A PLANE TO TAKE OFF
	private String id;  // THE ID NUMBER OF THE PLANE
	private  int maxLandingQueueTime; // THE MAX TIME A PLANE CAN SPEND IN THE LANDING QUEUE BEFORE CRASHING
	
	
	public Plane(int id, int arrivalTime, int landingTime, int takeoffTime, int maxLandingQueueTime) {
		// SETS PLANE ID, TIME PLANE ENTERED QUEUE, AND TIME IT TAKES FOR A PLANE TO LAND AND TAKE OFF
		// ARRIVAL TIME WILL COME FROM CLOCK IN DRIVER
		// LANDING AND TAKEOFF TIME WILL COME FROM SIMULATE METHOD CALL IN DRIVER
		// ID WILL COME FROM ID GENERATOR IN DRIVER
		this.landingTime = landingTime;
		this.takeoffTime=takeoffTime;
		this.id = "Plane " + id; 
		this.queueEntryTime = arrivalTime;
		this.maxLandingQueueTime = maxLandingQueueTime;
		
	}  // END Plane(int id, int arrivalTime, int landingTime, int takeoffTime)
	
	public int getQueueEntryTime() {
		// RETURNS THE TIME (CLOCK VALUE) A PLANE ENTERED A QUEUE
		return queueEntryTime;
	}  // END getQueueEntryTime()
	

	public String toString() {
		// PRINTS ID OF PLANE
		return this.id;
	}  // END toString()
	
	public int getTakeOffTimeLeft() {
		// RETURNS HOW MUCH TIME IS LEFT UNTIL THE PLANE HAS TAKEN OFF
		return takeoffTime;
	}  // END getTakeOffTimeLeft()
	
	public int takeOffTimeLeft() {
		// RETURNS HOW MUCH TIME IS LEFT UNTIL THE PLANE HAS TAKEN OFF AND THEN DECREMENTS IT BY 1
		return takeoffTime--;
	}  // END takeOffTimeLeft()
	
	public int landingTimeLeft() {
		// RETURNS HOW MUCH TIME IS LEFT UNTIL THE PLANE HAS LANDED AND THEN DECREMENTS IT BY 1
		return landingTime--;
	}  // END landingTimeLeft()
	

	public int timeInQueue(int clock) {
		// RETURNS THE AMOUNT OF TIME IN MINUTES THAT HAS PASSED SINCE THE PLANE ENTERED THE QUEUE
		return clock - queueEntryTime;
	}  // END timeInQueue(int clock)
	
	public boolean hasCrashed(int clock) {
		// RETURNS WHETHER THE PLANE HAS CRASHED BY TESTING WHETHER THE TIME THE PLANE SPENT IN THE QUEUE
		// IS GREATER THAN THE AMOUNT OF TIME A PLANE CAN SPEND IN THE LANDING QUEUE BEFORE CRASHING
		return (clock - queueEntryTime) > maxLandingQueueTime;
	}  // END hasCrashed(int clock)

}  // END class Plane
