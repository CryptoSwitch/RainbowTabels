package com.gravypod.RainbowTabel;

import java.util.Arrays;

public class MakeWords {
	
	static int charLen, wordsMade = 0;
	
	static long start = System.nanoTime();
	
	static char[] guess;
	
	static boolean runFull = true;
	
	static boolean isLast;
	
	public static void run(int _charLen, boolean _isLast, String startFrom) {
	
		charLen = _charLen;
		isLast = _isLast;
		
		if (startFrom != null && charLen == startFrom.length()) {
			runFull = false;
			guess = startFrom.toCharArray();
		}
		
		if (runFull) {
			
			guess = new char[charLen];
		
			Arrays.fill(guess, '!');
			
		}
		
		do {
			
			wordsMade++;
			
			BruteForce.addWords(new String(guess));
			
			int incrementIndex = guess.length - 1;
			
			while (incrementIndex >= 0) {
				
				guess[incrementIndex]++;
				
				if (guess[incrementIndex] > '~') {
					
					if (incrementIndex > 0) {
						
						guess[incrementIndex] = '!';
						
					}
					
					incrementIndex--;
					
				} else {
					break;
				}
				
			}
			
		} while (guess[0] <= '~');
		
		if (isLast)
			RainbowTable.setDone(true);
		
		long time = System.nanoTime() - start;
		System.out.printf("Took %.3f seconds to generate %,d combinations%n", time / 1e9, wordsMade);
		
	}
	
}
