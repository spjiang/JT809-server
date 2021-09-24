package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 文件上传指令 消息体数据格式Model定义
 *
 * <p>平台向终端下发文件上传命令，终端回复通用应答后通过FTP方式将文件上传到目标FTP服务器的指定路径
 * @author zx 2018年9月28日 下午2:37:54
 * @see
 * @version 1.0
 */
public class P0x9206 extends PModel implements Serializable{

	private static final long serialVersionUID = -495076769198008808L;
	/**
	 * 12+0	服务器地址长度	BYTE	长度k
	 */
	private byte serverIpLength;
	/**
	 * 12+1	服务器地址	STRING	FTP服务器地址
	 */
	private String serverIp;
	/**
	 * 12+1+k	端口	WORD	FTP服务器端口号
	 */
	private short port;
	/**
	 * 12+3+k	用户名长度	BYTE	长度l
	 */
	private byte userNameLength;
	/**
	 * 12+4+k	用户名	STRING	FTP用户名
	 */
	private String userName;
	/**
	 * 12+4+k+l	密码长度	BYTE	长度m
	 */
	private byte passWordLength;
	/**
	 * 12+5+k+l	密码	STRING	FTP密码
	 */
	private String passWord;
	/**
	 * 12+5+k+l+m	文件上传路径长度	BYTE	长度n
	 */
	private byte dataUploadUrlLength;
	/**
	 * 12+6+k+l+m	文件上传路径	STRING	文件上传路径
	 */
	private String dataUploadUrl;
	/**
	 * 12+6+k+l+m+n	逻辑通道号	BYTE	参见A.6音视频通道定义表
	 */
	private byte logicalChannel;
	/**
	 * 12+7+k+l+m+n	开始时间	BCD[6]	YY-MM-DD-HH-MM-SS
	 */
	private Date startTime;
	/**
	 * 12+13+k+l+m+n	结束时间	BCD[6]	YY-MM-DD-HH-MM-SS
	 */
	private Date endTime;
	/**
	 * 12+19+k+l+m+n 报警标志 64BITS bit0～bit31见表A.27报警标志位定义； bit32～bit63见
	 * 特殊报警标识位定义表； 全0表示不指定是否有报
	 *
	 */
	private long alarmSign;
	/**
	 * 12+27+k+l+m+n	音视频资源类型	BYTE	0：音视频，1：音频，2：视频，3：视频或音视频
	 */
	private byte audioVideoType;
	/**
	 * 12+28+k+l+m+n	码流类型	BYTE	0：主码流或子码流，1：主码流，2：子码流
	 */
	private byte codeStreamType;
	/**
	 * 12+29+k+l+m+n	存储位置	BYTE	保留为0
	 */
	private byte memorLocation;
	/**
	 * 12+30+k+l+m+n	任务执行条件	BYTE
	 * 用bit位表示 Bit0：WIFI，为1时表示WIFI下可下载； Bit1：LAN，为1时表示LAN连接时可下载；Bit2：3G/4G，为1时表 示3G/4G连接时可下载
	 */
	private byte taskExecutionConditions;


	/**
	 * @return the serverIpLength
	 */
	public byte getServerIpLength() {
		return serverIpLength;
	}


	/**
	 * @param serverIpLength the serverIpLength to set
	 */
	public void setServerIpLength(byte serverIpLength) {
		this.serverIpLength = serverIpLength;
	}


	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}


	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}


	/**
	 * @return the port
	 */
	public short getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(short port) {
		this.port = port;
	}


	/**
	 * @return the userNameLength
	 */
	public byte getUserNameLength() {
		return userNameLength;
	}


	/**
	 * @param userNameLength the userNameLength to set
	 */
	public void setUserNameLength(byte userNameLength) {
		this.userNameLength = userNameLength;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the passWordLength
	 */
	public byte getPassWordLength() {
		return passWordLength;
	}


	/**
	 * @param passWordLength the passWordLength to set
	 */
	public void setPassWordLength(byte passWordLength) {
		this.passWordLength = passWordLength;
	}


	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}


	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	/**
	 * @return the dataUploadUrlLength
	 */
	public byte getDataUploadUrlLength() {
		return dataUploadUrlLength;
	}


	/**
	 * @param dataUploadUrlLength the dataUploadUrlLength to set
	 */
	public void setDataUploadUrlLength(byte dataUploadUrlLength) {
		this.dataUploadUrlLength = dataUploadUrlLength;
	}


	/**
	 * @return the dataUploadUrl
	 */
	public String getDataUploadUrl() {
		return dataUploadUrl;
	}


	/**
	 * @param dataUploadUrl the dataUploadUrl to set
	 */
	public void setDataUploadUrl(String dataUploadUrl) {
		this.dataUploadUrl = dataUploadUrl;
	}


	/**
	 * @return the logicalChannel
	 */
	public byte getLogicalChannel() {
		return logicalChannel;
	}


	/**
	 * @param logicalChannel the logicalChannel to set
	 */
	public void setLogicalChannel(byte logicalChannel) {
		this.logicalChannel = logicalChannel;
	}


	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * @return the alarmSign
	 */
	public long getAlarmSign() {
		return alarmSign;
	}


	/**
	 * @param alarmSign the alarmSign to set
	 */
	public void setAlarmSign(long alarmSign) {
		this.alarmSign = alarmSign;
	}


	/**
	 * @return the audioVideoType
	 */
	public byte getAudioVideoType() {
		return audioVideoType;
	}


	/**
	 * @param audioVideoType the audioVideoType to set
	 */
	public void setAudioVideoType(byte audioVideoType) {
		this.audioVideoType = audioVideoType;
	}


	/**
	 * @return the codeStreamType
	 */
	public byte getCodeStreamType() {
		return codeStreamType;
	}


	/**
	 * @param codeStreamType the codeStreamType to set
	 */
	public void setCodeStreamType(byte codeStreamType) {
		this.codeStreamType = codeStreamType;
	}


	/**
	 * @return the memorLocation
	 */
	public byte getMemorLocation() {
		return memorLocation;
	}


	/**
	 * @param memorLocation the memorLocation to set
	 */
	public void setMemorLocation(byte memorLocation) {
		this.memorLocation = memorLocation;
	}


	/**
	 * @return the taskExecutionConditions
	 */
	public byte getTaskExecutionConditions() {
		return taskExecutionConditions;
	}


	/**
	 * @param taskExecutionConditions the taskExecutionConditions to set
	 */
	public void setTaskExecutionConditions(byte taskExecutionConditions) {
		this.taskExecutionConditions = taskExecutionConditions;
	}


	@Override
	public String toString() {
		return "P0x9206 [serverIpLength=" + serverIpLength + ", serverIp=" + serverIp + ", port=" + port
				+ ", userNameLength=" + userNameLength + ", userName=" + userName + ", passWordLength=" + passWordLength
				+ ", passWord=" + passWord + ", dataUploadUrlLength=" + dataUploadUrlLength + ", dataUploadUrl="
				+ dataUploadUrl + ", logicalChannel=" + logicalChannel + ", startTime=" + startTime + ", endTime="
				+ endTime + ", alarmSign=" + alarmSign + ", audioVideoType=" + audioVideoType + ", codeStreamType="
				+ codeStreamType + ", memorLocation=" + memorLocation + ", taskExecutionConditions="
				+ taskExecutionConditions + "]";
	}

}
