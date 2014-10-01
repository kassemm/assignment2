//Class that represents the critical section

//When the thread enters the CS it should print from 500 to 1 without interrupt 

public class Decrement_Print {
	//Decrement_Print DP = new Decrement_Print(); 
	  Bakery lock = new Bakery(2);
	  //lock 
	   public /*synchronized*/ void printCount(){
		//Lock.
		   int i =500;      
		lock.lock();   
		 
		try {
	        
			while (i >0) {
	            System.out.println("Counter   ---   "  + i );
	            i--;
	         }
	     } catch (Exception e) {
	         System.out.println("Thread  interrupted.");
	     }
		lock.unlock();
	   }
	  }