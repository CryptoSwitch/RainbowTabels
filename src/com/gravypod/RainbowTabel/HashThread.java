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
	
	String quote = "\"";
	
	String singleQote = "\'";
	
	String altQuote = "`";
	
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
			
			ArrayList<String> text = getText();
			
			if (text == null) {
				
				BruteForce.removeThread();
				return;
				
			}
			
			for (String string : text) {
				
				RainbowTable.getFileHandle().print(clean(string) + " " + md5(string));
				wordsMade++;
				
			}
			
		} while (!RainbowTable.isDone() || QueueClass.hasContent());
		
		long time = System.nanoTime() - startTime;
		System.out.printf("Took %.3f seconds to hash %,d combinations%n", time / 1e9, wordsMade);
		
		if (wordsMade > 2000) {
			wordsMade = 0;
			startTime = System.nanoTime();
			startHash();
			return;
		}
		
		BruteForce.removeThread();
		
	}
	
	public String md5(String string) {
	
		messageDigest.reset();
		
		messageDigest.update(string.getBytes());
		
		digestedMessageBytes = messageDigest.digest();
		
		bigInt = new BigInteger(1, digestedMessageBytes);
		
		finalHashedText = bigInt.toString(16);
		
		while (finalHashedText.length() < 32) {
			finalHashedText = "0" + finalHashedText;
		}
		
		return finalHashedText;
		
	}
	
	private ArrayList<String> getText() {
	
		try {
			return QueueClass.take();
		} catch (InterruptedException e) {
			return getText();
		}
		
	}
	
	public String clean(String s) {
	
		return s.replace(quote, "&quot;").replace(singleQote, "&apos;").replace(altQuote, "&#096;").replace("(", "&lpar;").replace(")", "&rpar;").replace("´", "&acute;").replace("\\", "&#92;");
	}
	
}
