package com.ctfo.engine.access.server;

import com.ctfo.engine.access.coder.T809CodecFactory;
import com.ctfo.engine.access.server.handler.T809Handler;
import lombok.Data;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.rmi.ServerException;


/**
 * 809服务管理类
 *
 * @author 凉意 2018年5月14日 下午4:05:06
 * @version 1.0
 */
@Data
public class T809Server {


	/** 字符集编码 */
	private String charset = "GBK";

	/** 缓冲区大小 */
	private int buffSize = 51200;

	/** 会话自动失效时间 */
	private int sessionInvalid = 120;

	/** 开放端口 */
	private int port = 8885;


	public T809Server() {
		super();
	}

	/**
	 * 创建服务
	 *
	 * @author 凉意 2018年5月16日 上午10:06:42
	 * @throws ServerException
	 */
	public void bind() throws ServerException {
		// 创建服务端监控线程
		NioSocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		// 设置每一个非主监听连接的端口可以重用Socket.setReuseAddress
		// 务关掉重启时立马可使用该端口，而不是提示端口占用
		// 为了确保一个进程被关闭后，及时它还没有释放该端口，同一个主机上的其他进程还可以立刻重用该端口，可以调用Socket的setResuseAddress(true)方法
		acceptor.setReuseAddress(true);
		// 设置主服务监听端口的监听队列的最大值为100，如果当前已经有100个连接，再新的连接来将被服务器拒绝
		acceptor.setBacklog(100);
		// 设置日志记录器
		LoggingFilter logging = new LoggingFilter();
		logging.setSessionCreatedLogLevel(LogLevel.NONE);
		logging.setSessionOpenedLogLevel(LogLevel.NONE);
		logging.setExceptionCaughtLogLevel(LogLevel.NONE);
		logging.setMessageReceivedLogLevel(LogLevel.NONE);
		logging.setMessageSentLogLevel(LogLevel.NONE);
		logging.setSessionClosedLogLevel(LogLevel.NONE);
		logging.setSessionIdleLogLevel(LogLevel.NONE);
		// 设置过滤器
		// 记录通信过程中的事件以及请求
		acceptor.getFilterChain().addLast("logger", logging);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new T809CodecFactory(charset)));
		// 指定业务逻辑处理器
		acceptor.setHandler(new T809Handler());
		// 长时间没有心跳断开连接
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, sessionInvalid);

		// 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出Socket.setSendBufferSize
		acceptor.getSessionConfig().setTcpNoDelay(true);
		acceptor.getSessionConfig().setWriteTimeout(3000);
		acceptor.getSessionConfig().setKeepAlive(true);
		acceptor.getSessionConfig().setReadBufferSize(buffSize);
		acceptor.getSessionConfig().setReceiveBufferSize(buffSize);// 设置读取缓存大小
		acceptor.getSessionConfig().setSendBufferSize(buffSize);// 设置输出缓冲区的大小
		// 设置端口号
		acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
		// 启动监听线程
		try {
			acceptor.bind();
		} catch (IOException e) {
			throw new ServerException("JT809 启动服务失败:", e);
		}
	}

}
