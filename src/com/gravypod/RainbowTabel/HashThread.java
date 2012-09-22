package com.gravypod.RainbowTabel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This thread is created for every 100000 words we brute force.
 * 
 * @author gravypod
 * 
 */
public class HashThread extends Thread {
	
	byte[] digestedMessageBytes;
	
	BigInteger bigInt;
	
	String finalHashedText;
	
	MessageDigest messageDigest = null;
	
	long startTime;

	int wordsMade;
	
	public HashThread() {
	
		startTime = System.nanoTime();
		this.setPriority(MAX_PRIORITY);
		this.start();
		
	}
	
	@Override
	public void run() {
	
		try {
			
			messageDigest = MessageDigest.getInstance("MD5");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			
			try {
				startHash();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public synchronized void startHash() throws InterruptedException {
	
		do {
			
			while (!QueueClass.hasContent());
			
			ArrayList<String> text;
			
			try {
				text = QueueClass.take();
			} catch (InterruptedException e) {
				text = QueueClass.take();
			}
			
			if (text == null)
				startHash();
			
			for (String string : text) {
				
				messageDigest.reset();
				
				messageDigest.update(string.getBytes());
				
				digestedMessageBytes = messageDigest.digest();
				
				bigInt = new BigInteger(1, digestedMessageBytes);
				
				finalHashedText = bigInt.toString(16);
				
				while (finalHashedText.length() < 32) {
					finalHashedText = "0" + finalHashedText;
				}
				
				RainbowTable.getFileHandle().print(org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(string) + " " + finalHashedText);
				wordsMade++;
			}
			
		} while (!RainbowTable.isDone() || QueueClass.hasContent());
		
		BruteForce.setStartedThread(false);
		
		long time = System.nanoTime() - startTime;
		System.out.printf("Took %.3f seconds to hash %,d combinations%n", time / 1e9, wordsMade);
		
		BruteForce.removeThread();
		
	}
	
}
