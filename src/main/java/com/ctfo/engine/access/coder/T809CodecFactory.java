package com.ctfo.engine.access.coder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 809协议编解码处理工厂类
 *
 * @author 凉意 2018年5月14日 下午4:15:18
 * @version 1.0
 */
public class T809CodecFactory implements ProtocolCodecFactory {

	/**
	 * 字符编码集
	 */
	private String charset;

	private T809Encoder encoder;

	private T809Decoder decoder;

	public T809CodecFactory(String charset) {
		encoder = new T809Encoder(charset);
		decoder = new T809Decoder(charset);
		this.charset = charset;
	}

	/**
	 * @see ProtocolCodecFactory#getEncoder(IoSession)
	 */
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	/**
	 * @see ProtocolCodecFactory#getDecoder(IoSession)
	 */
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset
	 *            the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
}
