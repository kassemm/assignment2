public class ThreadID {

	public static int get() {
		return id.get();
	}
	private static int next = 0;
	private static final Object lock = new Object();
	private static final ThreadLocal<Integer> id = new ThreadLocal<Integer>() 
			{
				protected Integer initialValue() {
				synchronized (lock) {
				return next++;
				}
			}
	};
}