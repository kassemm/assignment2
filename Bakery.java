import java.util.Arrays;

//Bakery lock implementation
//--------------------------
class Bakery implements Lock{
	
	/*Without using volatile the next thread is not able to enter the  CS 
	Because it is not be able to read the updated value of the flag or the label shared variables 
	from the its own local thread memory making it not able to read the updated values of these shared variables from the other threads 
	so it keeps waiting and can not enter its critical section
	--
	The solution is to use the volatile for the shared variables so that any values read or written to these 
	shared variable will be from the main memory so that always the updated values is used. 
	
	*/
	
	volatile boolean  ready ;				
	volatile boolean     []     flag ;  
	volatile int[]   	 	   label;  
	int[]	    label_sorted;
	
	int number_threads;

	public Bakery (int n){
		this.number_threads =n;
		flag  = new boolean[this.number_threads];
		label = new int [this.number_threads];
		label_sorted = new int [this.number_threads];

		//Initialization
		for(int i =0; i<this.number_threads; i++){
			flag[i] = false;
			label[i] =0;
		}
	}

	public void lock(){
		int i = ThreadID.get();
		//Debug
		if(i ==0)
		{
			System.out.println("Thread one wants to enter the critical section");
		}
		else
		{
			System.out.println("Thread Two wants to enter the critical section");
		}
		
		/*********Debug************/
		System.out.println("Printing labels before increase ");
		for(int j =0; j <label.length;j++)
		{
			System.out.println(label[j]);
		}
		/*********Debug************/
		flag[i]=true;
		
		//Get the max.
		
		for(int k =0; k<label.length;k++)
		{
			label_sorted[k]=label[k];
			//System.out.println(label_sorted[j]);
		}
		Arrays.sort(label_sorted);
		
		//Increase by one

		if(label[i] !=0)
		{
			if(label[i] != label_sorted[label_sorted.length-1] )
			{
				label[i] =label_sorted[label_sorted.length-1] +1;
			}
		}
		else
		{
			label[i] =label_sorted[label_sorted.length-1] +1;
		}
		
		/*********Debug************/
		System.out.println("Printing labels after increase ");
		for(int j =0; j <label.length;j++)
		{
			System.out.println(label[j]);
		}
		System.out.println("Printing Flags");
		for(int j =0; j <flag.length;j++)
		{
			System.out.println(flag[j]);
		}
		/*********Debug************/
		
		//Read all the labels and make sure that there is no thread with lower ID than the current thread otherwise block
		for (int k =0; k < this.number_threads ;k++)
		{
			if(i !=k)
			{
				/*********Debug************/
				System.out.println("Label and flags Seen");
				for(int l=0; l<label.length;l++){System.out.println(label[l]);}
				for(int l=0; l<label.length;l++){System.out.println(flag[l]);}
				/*********Debug************/
				while( (flag[k] ==true) && (label[k] <= label[i]) );
				System.out.printf("Exit wait thread %d \n",(i+1));
				
				if((flag[i] ==true) )
				{
					if(flag[k] == true)
					{
						if(label[k] > label[i])
						{
							System.out.printf("Thread %d enters CS\n",(i+1));
						}
					}
					else
					{
						System.out.printf("Thread %d enters CS\n",(i+1));
						
					}
					
				}
			}
		}
	}
	
	public void unlock()
	{
		flag[ThreadID.get()] =false;
		System.out.println("Thread  exits the critical section");
		/*********Debug************/
		for(int k =0;k<label.length;k++)
		{
			System.out.println(flag[k]);
		}
		
		for(int k =0;k<label.length;k++)
		{
			System.out.println(label[k]);
		}
		/*********Debug************/
	}
}
	
