package com.gravypod.RainbowTabel;

/**
 * 
 * Brute force class to calculate strings for hashing.
 * 
 * @author gravypod
 * 
 */
public class BruteForce {
	
	static boolean startedThread = false;
	
	public static int wordsMade = 0;
	
	/**
	 * String generator
	 * 
	 * @param min
	 *            - min string length
	 * @param max
	 *            - max string length
	 * @param startFrom 
	 */
	public static void run(int min, int max, String startFrom) {
	
		// Creator: http://stackoverflow.com/users/609251/aroth
		int realMin = min;
		
		if (startFrom != null)
			realMin = startFrom.length();
		
		for (int charLen = realMin; charLen < max; charLen++) {
			
			RainbowTable.setDone(false);
			
			if (startFrom == null || (startFrom.length() <= charLen))
				new MakeWords(charLen, (charLen < max ? true : false), startFrom).run();
			
		}
		
	}
	
	/**
	 * 
	 * Action to do after we have created a string
	 * 
	 * @param s
	 */
	private static void action(String s) {
	
		QueueClass.put(s);
		
		if (!startedThread) {
			RainbowTable.startHashing();
			startedThread = true;
		}
		
	}
	
	/**
	 * Add a word and incraments the words made val.
	 * 
	 * @param s
	 */
	public static synchronized void addWords(String s) {
	
		action(s);
		wordsMade++;
	}
	
	/**
	 * Set if we need to create a new thread.
	 * 
	 * @param startedThread
	 */
	public static void setStartedThread(boolean startedThread) {
	
		BruteForce.startedThread = startedThread;
	}
	
}
