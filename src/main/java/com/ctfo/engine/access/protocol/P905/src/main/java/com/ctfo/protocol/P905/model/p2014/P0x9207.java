package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *
 * 文件上传控制 消息体数据格式Model定义
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 下午5:28:24
 * @see
 * @version 1.0
 */
public class P0x9207 extends PModel implements Serializable{

	private static final long serialVersionUID = 2574735535148701258L;
	/**
	 * 12+0	应答流水号	WORD	对应平台文件上传消息的流水号
	 */
	private short ansFlowNo;
	/**
	 * 12+2	上传控制	BYTE	0：暂停；1：继续；2：取消
	 */
	private byte uploadControl;
	public short getAnsFlowNo() {
		return ansFlowNo;
	}
	public void setAnsFlowNo(short ansFlowNo) {
		this.ansFlowNo = ansFlowNo;
	}
	/**
	 * @return the uploadControl
	 */
	public byte getUploadControl() {
		return uploadControl;
	}
	/**
	 * @param uploadControl the uploadControl to set
	 */
	public void setUploadControl(byte uploadControl) {
		this.uploadControl = uploadControl;
	}
	@Override
	public String toString() {
		return "P0x9207 [ansFlowNo=" + ansFlowNo + ", uploadControl=" + uploadControl + "]";
	}

}
