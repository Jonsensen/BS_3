
/**
 *
 * @author debian
 */
package hal_interpreter;

public class Buffer {
    private boolean available = false;
	//private int data;
        private float data;
	public synchronized void put(float x) {
		while(available) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		data = x;
		available = true;
		notifyAll();
	}

	public synchronized float get() {
		while(!available) {
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		available = false;
		notifyAll();
		return data;
	}
    
    
}
