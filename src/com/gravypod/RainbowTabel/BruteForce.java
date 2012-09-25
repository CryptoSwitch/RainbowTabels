package com.gravypod.RainbowTabel;

/**
 * 
 * Brute force class to calculate strings for hashing.
 * 
 * @author gravypod
 * 
 */
public class BruteForce {
	
	static int threadsMade = 0;
	
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
				MakeWords.run(charLen, (charLen < max ? true : false), startFrom);
			
			RainbowTable.setDone(true);
			
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
		
		if ((QueueClass.strings.size() >= 800) && !(threadsMade > 30)) {
			RainbowTable.startHashing();
			++threadsMade;
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
	
	public static synchronized void removeThread() {
		
		--threadsMade;
		
	}
	
	
}
