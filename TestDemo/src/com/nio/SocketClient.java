package com.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		SocketChannel sc = SocketChannel.open();
		SocketAddress socketAddress = new InetSocketAddress(
				"localhost", 8853);
		sc.connect(socketAddress);
		String question = "ask question4!";
		ByteBuffer buffer = ByteBuffer.wrap(CharUtil.getBytes(question.toCharArray()));
		sc.write(buffer);
		buffer.flip();
		int size = 0;
		while((size=sc.read(buffer))>0) {
			System.out.println(CharUtil.getChars(buffer.array()));
		}
	}
}
