package com.gravypod.RainbowTabel;

import java.util.Arrays;

public class MakeWords extends Thread {
	
	int charLen, wordsMade = 0;
	
	long start = System.nanoTime();
	
	char[] guess;
	
	boolean runFull = true;
	
	boolean isLast;
	
	/**
	 * Create words of a given length
	 * 
	 * @param _charLen
	 * @param _isLast
	 * @param startFrom 
	 */
	public MakeWords(int _charLen, boolean _isLast, String startFrom) {
	
		charLen = _charLen;
		isLast = _isLast;
		
		if (startFrom != null && charLen == startFrom.length()) {
			runFull = false;
			guess = startFrom.toCharArray();
		}
		
	}
	
	@Override
	public void run() {
		
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
