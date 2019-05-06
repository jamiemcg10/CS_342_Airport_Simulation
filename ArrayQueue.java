// Jamie Smart
// CS 342

public class ArrayQueue {

	private int head;  // BEGINNING POSITION OF QUEUE
	private int tail;  // LAST POSITION OF QUEUE
	private int count;  // NUMBER OF ELEMENTS IN QUEUE
	private int size;  // NUMBER OF ELEMENTS QUEUE CAN HOLD
	private Plane[] queue;  // ARRAY TO HOLD PLANES
	
	public ArrayQueue(){
		head = 0;
		tail = 0;
		this.count = 0;
		this.size = 50; // ARRAY CAN INITIALLY HOLD 50 ITEMS
		queue = new Plane[this.size];  // INSTANTIATE PLANE ARRAY
		
	}  // END ArrayQueue()
	
	public int getCount() {
		// RETURNS NUMBER OF PLANES IN QUEUE
		return count;
	}  // END getCount()
	
	public boolean isEmpty() {
		// RETURNS WHETHER THE QUEUE IS EMPTY OR NOT
		return count == 0;
	}  // END isEmpty()
	
	public void addToQueue(Plane plane){
		// ADDS A PLANE TO THE QUEUE
		ensureCapacity();  // MAKE SURE THERE'S ENOUGH ROOM IN THE QUEUE FOR ADDITIONAL ITEMS
		
		queue[tail++] = plane;  // ADD A PLANE TO THE TAIL OF THE QUEUE
		count++;  // INCREASE COUNT VAR FOR NUMBER OF PLANES IN QUEUE
		
	}  // END addToQueue(Plane plane)
	
	public Plane removeFromQueue() {
		if (count == 0) {
			// IF THERE ARE NO ITEMS IN THE QUEUE, THERE'S NOTHING TO REMOVE
			return null;
		}
		
		// CREATE A VARIABLE FOR THE PLANE TO BE RETURNED AND SET IT TO THE 
		// FIRST PLANE IN THE QUEUE
		Plane currentPlane;
		currentPlane = queue[head];  
		head++;  // INCREASE THE HEAD BY 1 TO "REMOVE" THE CURRENT PLANE FROM THE QUEUE
		
		count--;  // DECREASE THE COUNT VAR FOR THE NUMBER OF PLANES IN THE QUEUE
		return currentPlane;  // RETURN THE PLANE THAT WAS REMOVED FROM THE QUEUE
	}  // END removeFromQueue()
	
	public void ensureCapacity() {
		// CHECKS TO SEE IF THERE IS ROOM FOR MORE ELEMENTS IN THE QUEUE AND CREATES 
		// A LARGER ARRAY AND COPIES THE ELEMENTS OVER IF THERE IS NOT ENOUGH ROOM
		
		if (count >= size | tail >= size) {
			// IF THE NUMBER OR ITEMS OR THE TAIL POSITION IS GREATER OR EQUAL TO THE
			// SIZE OF THE ARRAY,  CREATE A NEW, LARGER ARRAY AND COPY THE PLANES OVER
			size = (size * 2) + 1;  // SET SIZE OF NEW ARRAY
			Plane newQueue[] = new Plane[size];  // INSTANTIATE NEW ARRAY

			// COPY OLD QUEUE TO NEW QUEUE
			int j=0;  // VAR FOR INDEX OF NEW QUEUE
			for (int i=head; i<tail; i++) {  // LOOP THROUGH OLD QUEUE
				newQueue[j++] = queue[i]; // SET VALUE OF NEW QUEUE TO OLD QUEUE
			}  // END for (int i=head; i<tail; i++)

			head = 0; // SET HEAD TO 0
			tail = count;  // SET TAIL TO THE NUMBER OF ITEMS IN THE QUEUE

			queue = newQueue;  // REPLACE THE OLD QUEUE WITH THE NEW QUEUE
		}  // END if (count >= size | tail >= size)

	}  // END ensureCapacity()

	
	public String toString() {
		// PRINTS QUEUE
		if (count == 0) {
			// IF THERE IS NOTHING IN THE QUEUE, PRINT A MESSAGE SAYING THE QUEUE IS EMPTY
			return "<EMPTY QUEUE>";
		}  // END if (count == 0)
		
		String printStr = "";
		for (int i=head; i<tail; i++) {  // LOOP THROUGH QUEUE AND PRINT PLANES
			printStr += queue[i] + "\n";
			
		}  // END for (int i=head; i<tail; i++)
		
		return printStr;
		
	}  // end toString()


	
	
} // END ArrayQueue class
