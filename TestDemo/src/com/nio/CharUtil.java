package com.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharUtil {
	public static byte[] getBytes(char[] chars) {// 将字符转为字节(编码)
		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	public static char[] getChars(byte[] bytes) {// 将字节转为字符(解码)
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}
}
