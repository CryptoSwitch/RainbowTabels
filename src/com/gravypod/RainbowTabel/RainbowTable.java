package com.gravypod.RainbowTabel;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 
 * Gravypods RanbowTable creator.
 * 
 * @author gravypod
 * 
 */
public class RainbowTable {
	
	private static boolean isDone;
	
	/**
	 * Main field. You do not need to have anything in args
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int min = 0;
		int max = 0;
		String startFrom = null;
		
		try {
			System.out.println("Please enter the min string lenth.");
			min = Integer.parseInt(br.readLine());
			System.out.println("Please enter the max string lenth.");
			max = Integer.parseInt(br.readLine());
			++max;
			
			if (new File("HashList.txt").exists()) {
				
				System.out.println("From what text would you like to start.");
				startFrom = br.readLine();
				
			}
				
		} catch (Exception e) {
			min = 6;
		}
		
		BruteForce.run(min, max, startFrom);
		
	}
	
	/**
	 * 
	 * Starts a hashing thread.
	 * 
	 */
	public static synchronized void startHashing() {
	
		System.out.println("Starting a hash thread");
		new HashThread();
		
	}
	
	/**
	 * @return isDone - if the application is done running.
	 */
	public static synchronized boolean isDone() {
	
		return isDone;
	}
	
	/**
	 * @param isDone
	 *            set if the application is done running.
	 */
	public static synchronized void setDone(boolean isDone) {
	
		RainbowTable.isDone = isDone;
	}
	
}
