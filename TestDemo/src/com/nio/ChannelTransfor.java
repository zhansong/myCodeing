package com.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class ChannelTransfor {

	
	public static void main(String[] args) throws Exception {
		
		
		RandomAccessFile from = new RandomAccessFile("e:\\from.txt", "rw");
		RandomAccessFile to = new RandomAccessFile("e:\\to.txt", "rw");
		long count = from.length();
		to.getChannel().transferFrom(from.getChannel(), to.length(), count);
		
	}
}
