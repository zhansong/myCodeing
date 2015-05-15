package com.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ServerSocket serverSocket = ssc.socket();
		serverSocket.bind(new InetSocketAddress("127.0.0.1", 8853));
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (true) {
			int num = selector.select();
			if (num < 1) {
				continue;
			}
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			while (iterator.hasNext()) {
				SelectionKey sk = iterator.next();
				if ((sk.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ServerSocketChannel sc = (ServerSocketChannel) sk
							.channel();
					SocketChannel accept = sc.accept();
					accept.configureBlocking(false);
					SelectionKey key = accept.register(selector, SelectionKey.OP_READ);
					key.attach(accept);
					iterator.remove();
				} else if ((sk.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					{
						iterator.remove();
						SocketChannel sc = (SocketChannel) sk.channel();
						int size = 0;
						String last = "";
						while ((size = sc.read(buffer)) > 0) {
							char[] chars = CharUtil.getChars(buffer.array());
							last = String.valueOf(chars[chars.length-1]);
							System.out.println(chars);
						}
						buffer.flip();
						String result = "return the success!" + last;
						byte[] bytes = CharUtil.getBytes(result.toCharArray());
						ByteBuffer send = ByteBuffer.wrap(bytes);
						if(send.hasRemaining()) {
							sc.write(send);
						}
						send.clear();
						sc.close();
					}
				}
			}
		}
	}
}