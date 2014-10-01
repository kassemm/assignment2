/*
    The main part of the program 
	............................
	
	
	Instantiation of threads for testing the lock.
	The number of threads used is two.
*/


public class main {

	public static void main(String[] args) {
	  System.out.println("Moustafa Mohammad Kassem ");
	
	  //Shared Resource	
	  Decrement_Print DP = new Decrement_Print();
	  
	  //Create 2 Threads
	  Thread_IMP T1 = new Thread_IMP( "Thread - 1 ", DP );
	  Thread_IMP T2 = new Thread_IMP( "Thread - 2 ", DP );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      } catch( Exception e) {
         System.out.println("Interrupted");
      }
	
	}

}
