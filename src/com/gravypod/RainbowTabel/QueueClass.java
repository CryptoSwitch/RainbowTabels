package com.gravypod.RainbowTabel;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueClass {
	
	private static final BlockingQueue<String> strings = new ArrayBlockingQueue<String>(1024);
	
	/**
	 * Take out all current strings from the BlockingQueue.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static ArrayList<String> take() throws InterruptedException {
	
		synchronized (strings) {
			
			ArrayList<String> temp = new ArrayList<String>();
			temp.addAll(strings);
			strings.removeAll(temp);
			
			return temp;
			
		}
		
	}
	
	/**
	 * If the BlockingQueue is empty
	 * 
	 * @return
	 */
	public static boolean hasContent() {
	
		return !strings.isEmpty();
		
	}
	
	/**
	 * Add a string to the blocking queue
	 * 
	 * @param s
	 */
	public static void put(String s) {
	
		try {
			strings.put(s);
		} catch (InterruptedException e) {
			put(s);
		}
		
	}
	
}
