package com.gravypod.RainbowTabel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	
	String hashedText;
	
	byte[] digestedMessageBytes;
	
	BigInteger bigInt;
	
	String finalHashedText;
	
	MessageDigest messageDigest = null;
	
	private PrintWriter pw;
	
	long startTime;
	
	static final ArrayList<String> hashedTextList = new ArrayList<String>();
	
	public HashThread() {
	
		startTime = System.nanoTime();
		this.setPriority(MAX_PRIORITY);
		this.start();
		
	}
	
	@Override
	public void run() {
	
		try {
			pw = new PrintWriter(new FileWriter(new File("HashList.txt"), true));
		} catch (Exception e) {
		}
		
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
				
				hashedText = string + " " + finalHashedText;
				
				pw.println(hashedText);
				
			}
			
			pw.flush();
			
		} while (!RainbowTable.isDone() || QueueClass.hasContent());
		
		BruteForce.setStartedThread(false);
		
		long time = System.nanoTime() - startTime;
		System.out.printf("Took %.3f seconds to hash and print %,d combinations%n", time / 1e9, BruteForce.wordsMade);
		
	}
	
}
