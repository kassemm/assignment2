public class Thread_IMP extends Thread {
	   private Thread t;
	   private String threadName;
	   Decrement_Print  Decr_Print;

	   Thread_IMP( String name,  Decrement_Print Decr_Print){
	       threadName = name;
	       this.Decr_Print = Decr_Print;
	   }
	   
	   public void run() {
     //Critical Section
     Decr_Print.printCount();
     //Finish Critical Section
	     System.out.println("Thread " +  threadName + " exiting.");
	   }

	   public void start ()
	   {
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}

