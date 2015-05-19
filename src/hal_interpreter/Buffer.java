/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author debian
 */

package hal_interpreter;

public class Buffer {
    private boolean available = false;
	private int data;

	public synchronized void put(int x) {
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

	public synchronized int get() {
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
