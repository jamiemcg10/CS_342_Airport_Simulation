// Jamie Smart
// CS 342

import java.util.Random;

public class Driver {

	private int idNum = 0;  // VAR TO HOLD NUMBER FOR PLANE ID GENERATOR
	
	public static void main(String[] args) {

	Driver driver = new Driver();
	//simulate(int landingTime, int takeoffTime, int landingArrival, int takeoffArrival, int fuelLevel, int simulationTime)
	System.out.println("Simulation 1");
	driver.simulate(5, 10, 2, 5, 5, 60);
	System.out.println("Simulation 2");
	driver.simulate(10, 5, 4, 5, 60, 60);
	System.out.println("Simulation 3");
	driver.simulate(7, 7, 7, 10, 20, 360);
	System.out.println("Simulation 4");
	driver.simulate(3, 15, 21, 15, 10, 360);
	System.out.println("Simulation 5");
	driver.simulate(10, 10, 30, 15, 30, 720);
	System.out.println("Simulation 6");
	driver.simulate(3, 4, 5, 10, 1, 720);
	System.out.println("Simulation 7");
	driver.simulate(2, 2, 7, 7, 15, 1080);
	System.out.println("Simulation 8");
	driver.simulate(11, 7, 12, 6, 50, 1080);
	System.out.println("Simulation 9");
	driver.simulate(5, 6, 15, 16, 30, 900);
	System.out.println("Simulation 10");
	driver.simulate(11, 7, 16, 12, 22, 900);

	}
	
	
	public int assignID() {
		// RETURNS NUMBER FOR PLANE ID
		return ++idNum;
	}  // END assignID()
	
	public void simulate(int landingTime, int takeoffTime, int landingArrival, int takeoffArrival, int maxWaitTime, int simulationTime) {
		
		ArrayQueue takeoffQueue = new ArrayQueue();  // CREATE QUEUE FOR PLANES TAKING OFF
		ArrayQueue landingQueue = new ArrayQueue();  // CREATE QUEUE FOR PLANES LANDING
		Runway runway = new Runway();  // CREATE RUNWAY
		
		int clock = 0;  // VAR TO HOLD THE CURRENT TIME
		int crashTotal = 0;  // NUM OF PLANES IN LANDING QUEUE THAT CRASHED
		int numPlanesTookoff = 0;  // NUM OF PLANES THAT TOOK OFF
		int numPlanesLanded = 0;  // NUM OF PLANES THAT SUCCESSFULLY LANDED
		int sumTimeInTakeoffQueue = 0;  // SUM OF TIME PLANES SPENT IN TAKEOFF QUEUE TO COMPUTE AVERAGE
		int sumTimeInLandingQueue = 0;  // SUM OF TIME PLANES SPENT IN LANDING QUEUE TO COMPUTE AVERAGE

		Random landingArrivalIndicator = new Random();  // GENERATE RANDOM NUMS TO DETERMINE WHEN A PLANE ARRIVES TO LAND
		Random takeoffArrivalIndicator = new Random();  // GENERATE RANDOM NUMS TO DETERMINE WHEN A PLANE ARRIVES TO TAKE OFF
		
		// DECLARE VARIABLES TO HOLD PLANES TAKING OFF AND LANDING
		Plane planeTakingOff = null;
		Plane planeLanding = null;
		
		while (clock <= simulationTime) {
			// WHILE THE TIME ELAPSED IS LESS THAN THE LENGTH OF THE SIMULATION
			
			// CHECK FOR PLANES ARRIVING TO LAND - ADD TO LANDING QUEUE IF A PLANE HAS ARRIVED
			if (landingArrivalIndicator.nextInt(landingArrival) == 0) {
				landingQueue.addToQueue(new Plane(assignID(), clock, landingTime, takeoffTime, maxWaitTime));
			}  // END if (landingArrivalIndicator.nextInt(landingArrival) == 0)
			
			// CHECK FOR PLANES ARRIVING TO TAKE OFF - ADD TO TAKE OFF QUEUE IF A PLANE HAS ARRIVED
			if (takeoffArrivalIndicator.nextInt(takeoffArrival) == 0) {
				takeoffQueue.addToQueue(new Plane(assignID(), clock, landingTime, takeoffTime, maxWaitTime));
			}  // END if (takeoffArrivalIndicator.nextInt(takeoffArrival) == 0)
			
			
			
			if (runway.isEmpty() & !landingQueue.isEmpty()){
				// THE RUNWAY IS EMPTY AND PLANES ARE WAITING TO LAND
				// LANDING PLANES HAVE PRIORITY OVER PLANES TAKING OFF
				planeLanding = landingQueue.removeFromQueue();  // REMOVE A PLANE FROM THE LANDING QUEUE
				while (planeLanding != null && planeLanding.hasCrashed(clock) == true) {
					// THE LANDING PLANE CRASHED - ADD TO THE CRASH TOTAL AND REMOVE ANOTHER PLANE FROM
					// THE LANDING QUEUE UNTIL ONE IS REMOVED THAT HASN'T CRASHED
					crashTotal++;
					planeLanding = landingQueue.removeFromQueue();
				}  // END while (planeLanding != null && planeLanding.hasCrashed(clock) == true)
				
				if (planeLanding != null) { 
					// IF THERE IS A PLANE IN THE LANDING QUEUE THAT HASN'T CRASHED
					// LAND THE PLANE, INCREASE THE NUMBER OF PLANES THAT LANDED AND ADD TO 
					// THE RUNNING TOTAL OF TIME PLANES SPENT IN THE QUEUE
					runway.land(planeLanding);
					numPlanesLanded++;
					sumTimeInLandingQueue += planeLanding.timeInQueue(clock);
				
				}  // END if (planeLanding != null)
				
			}  // END if (runway.isEmpty() & !landingQueue.isEmpty())
			
			else if (runway.isEmpty() & !takeoffQueue.isEmpty()) {
				// THE RUNWAY IS EMPTY AND PLANES ARE WAITING TO TAKEOFF
				// LANDING PLANES HAVE PRIORITY OVER PLANES TAKING OFF
				planeTakingOff = takeoffQueue.removeFromQueue();  // REMOVE A PLANE FROM THE TAKE OFF QUEUE
				
				// THE PLANE TAKES OFF, INCREASE THE NUMBER OF PLANES THAT TOOK OFF AND ADD TO THE 
				// RUNNING TOTAL OF TIME PLANES SPENT IN THE QUEUE
				runway.takeOff(planeTakingOff);
				numPlanesTookoff++;
				sumTimeInTakeoffQueue += planeTakingOff.timeInQueue(clock);
				
			}  // END else if (runway.isEmpty() & !takeoffQueue.isEmpty())
			
			else if (runway.planeIsTakingOff()) {
				// A PLANE IS ALREADY ON THE RUNWAY TAKING OFF - THE PLANE CONTINUES TAKING OFF
				runway.takeOff(planeTakingOff);
			}  // END else if (runway.planeIsTakingOff())
			
			else if (runway.planeIsLanding()) {
				// A PLANE IS ALREADY ON THE RUNWAY LANDING - THE PLANE CONTINUES LANDING
				runway.land(planeLanding);
			}  // END else if (runway.planeIsLanding()) 
			
			
			clock++;  // INCREASE THE CLOCK
		
		}  // END while (clock <= simulationTime) 
		
		
		// CHECK FOR PLANES STILL IN LANDING QUEUE THAT HAVE CRASHED
		Plane checkPlane;
		for (int i = 0; i<landingQueue.getCount(); i++) {  
			checkPlane = landingQueue.removeFromQueue(); // REMOVE PLANE FROM LANDING QUEUE
			if (checkPlane.hasCrashed(clock)) {
				// IF PLANE LEFT IN QUEUE HAS CRASHED, ADD TO CRASH TOTAL
				crashTotal++;
			}  // END if (checkPlane.hasCrashed(clock))
		}  // END for (int i = 0; i<landingQueue.getCount(); i++)
		
		
		// OUTPUT INFO ABOUT SIMULATION
		System.out.println(numPlanesTookoff + " plane(s) took off.");
		if (numPlanesTookoff > 0) {
			System.out.println("Planes spent an average of " + (sumTimeInTakeoffQueue/numPlanesTookoff) + " minutes in the take off queue.");
		}
		System.out.println(numPlanesLanded + " plane(s) landed.");
		if (numPlanesLanded > 0) {
			System.out.println("Planes spent an average of " + (sumTimeInLandingQueue/numPlanesLanded) + " minutes in the landing queue.");
		}
		System.out.println(crashTotal + " plane(s) crashed while waiting to land.");
		System.out.println();
		
		
	}  // end simulate()

}  // end class Driver
