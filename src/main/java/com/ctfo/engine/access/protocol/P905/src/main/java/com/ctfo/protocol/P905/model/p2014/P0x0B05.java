package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营数据上传命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 空转重时车位置信息 详见0x0200 <br>
 * 14+25 重转空时位置基本信息 详见0x0200 <br>
 * 14+50 营运ID UINT32 开始营运时的时间戳，按10.2.2营运ID的生成规则生成<br>
 * 14+54 评价ID UINT32 评价时的时间戳，按10.2.2营运ID的生成规则生成。若没有评价，则以0x00填充<br>
 * 14+58 评价选项 UINT8 0x00，没有做出评价；0x01，满意；0x02，一般；0x03，不满意；0x04，投诉<br>
 * 14+59 评价选项扩展 UINT16 保留，默认0x0000<br>
 * 14+61 订单ID UINT32 0：正常营运数据；非0 标识电召营运数据<br>
 * 14+65 计价器营运数据 内容严格按照“智能服务终端与计价器通讯协议”单次营运结束后营运数据发送指令，计价器发往终端的数据区。
 *
 * @author xiahancheng 2018年5月9日 下午5:04:17
 * @see
 * @version 1.0
 */
public class P0x0B05 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 空转重时车位置信息
	 */
	private P0x0200 k2zPosition;

	/**
	 * 重转空时位置基本信息
	 */
	private P0x0200 z2kPosition;

	// 14+50 营运ID UINT32 4
	private int operId;
	// 14+54 评价ID UINT32 4
	private int assessId;
	// 14+58 评价选项 UINT8 1,0x00，没有做出评价；0x01，满意；0x02，一般；0x03，不满意；0x04，投诉
	private byte assess;
	// 14+59 评价选项扩展 UINT16 2 保留，默认0x0000  (老版本)
	private short assessK;
	// 14+61 订单ID UINT32 0：正常营运数据；非0 标识电召营运数据
	private int orderId;

	// 0 车牌号 BYTE[6] 车牌号，ASCII字符，老协议6位， 新协议9位
	private String carNum;
	// 6 单位代码ASCII[16] 不足16 位右补0x00
	private String unitId;
	// 22 驾驶员从业资格证号ASCII[19] IC 卡从业资格证号为身份证号，不足19 位右补0x00
	private String driverId;
	// 41 上车时间 BCD[5] 格式为YY-MM-DD-hh-mm
	private Date inCarTime;
	// 46 下车时间 BCD[2] 格式为hh-mm
	private Date outCarTime;
	// 48 计程公里 BCD[3] 格式为XXXXX.X公里
	private double kilomeNum;
	// 51 空驶公里 BCD[2] 格式为XXX.X公里
	private double kongKilome;
	// 53 附加费 BCD[3] 格式XXXXX.X元\
	private double plusMoney;
	// 56 等待计时时间 BCD[2] 格式为hh-mm
	private String waitTime;
	// 58 交易金额 BCD[3] 格式XXXXX.X元
	private double money;
	// 61 当前车次 UINT32 高位在前，低位在后
	private int coachNum;
	// 65 交易卡类型 BYTE 0x00：现金交易；0x01：M1卡交易；0x03：CPU卡交易；0x09：其他
	private int cardType;


	/**
	 * @return the k2zPosition
	 */
	public P0x0200 getK2zPosition() {
		return k2zPosition;
	}

	/**
	 * @param k2zPosition
	 *            the k2zPosition to set
	 */
	public void setK2zPosition(P0x0200 k2zPosition) {
		this.k2zPosition = k2zPosition;
	}

	/**
	 * @return the z2kPosition
	 */
	public P0x0200 getZ2kPosition() {
		return z2kPosition;
	}

	/**
	 * @param z2kPosition
	 *            the z2kPosition to set
	 */
	public void setZ2kPosition(P0x0200 z2kPosition) {
		this.z2kPosition = z2kPosition;
	}

	/**
	 * @return the operId
	 */
	public int getOperId() {
		return operId;
	}

	/**
	 * @param operId
	 *            the operId to set
	 */
	public void setOperId(int operId) {
		this.operId = operId;
	}

	/**
	 * @return the assessId
	 */
	public int getAssessId() {
		return assessId;
	}

	/**
	 * @param assessId
	 *            the assessId to set
	 */
	public void setAssessId(int assessId) {
		this.assessId = assessId;
	}

	/**
	 * @return the assess
	 */
	public byte getAssess() {
		return assess;
	}

	/**
	 * @param assess
	 *            the assess to set
	 */
	public void setAssess(byte assess) {
		this.assess = assess;
	}

	/**
	 * @return the assessK
	 */
	public short getAssessK() {
		return assessK;
	}

	/**
	 * @param assessK
	 *            the assessK to set
	 */
	public void setAssessK(short assessK) {
		this.assessK = assessK;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the carNum
	 */
	public String getCarNum() {
		return carNum;
	}

	/**
	 * @param carNum
	 *            the carNum to set
	 */
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	/**
	 * @return the unitId
	 */
	public String getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId
	 *            the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the inCarTime
	 */
	public Date getInCarTime() {
		return inCarTime;
	}

	/**
	 * @param inCarTime
	 *            the inCarTime to set
	 */
	public void setInCarTime(Date inCarTime) {
		this.inCarTime = inCarTime;
	}

	/**
	 * @return the outCarTime
	 */
	public Date getOutCarTime() {
		return outCarTime;
	}

	/**
	 * @param outCarTime
	 *            the outCarTime to set
	 */
	public void setOutCarTime(Date outCarTime) {
		this.outCarTime = outCarTime;
	}

	/**
	 * @return the kilomeNum
	 */
	public double getKilomeNum() {
		return kilomeNum;
	}

	/**
	 * @param kilomeNum
	 *            the kilomeNum to set
	 */
	public void setKilomeNum(double kilomeNum) {
		this.kilomeNum = kilomeNum;
	}

	/**
	 * @return the kongKilome
	 */
	public double getKongKilome() {
		return kongKilome;
	}

	/**
	 * @param kongKilome
	 *            the kongKilome to set
	 */
	public void setKongKilome(double kongKilome) {
		this.kongKilome = kongKilome;
	}

	/**
	 * @return the plusMoney
	 */
	public double getPlusMoney() {
		return plusMoney;
	}

	/**
	 * @param plusMoney
	 *            the plusMoney to set
	 */
	public void setPlusMoney(double plusMoney) {
		this.plusMoney = plusMoney;
	}

	/**
	 * @return the waitTime
	 */
	public String getWaitTime() {
		return waitTime;
	}

	/**
	 * @param waitTime
	 *            the waitTime to set
	 */
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * @return the money
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * @return the coachNum
	 */
	public int getCoachNum() {
		return coachNum;
	}

	/**
	 * @param coachNum
	 *            the coachNum to set
	 */
	public void setCoachNum(int coachNum) {
		this.coachNum = coachNum;
	}

	/**
	 * @return the cardType
	 */
	public int getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B05 [");
		sb.append("k2zPosition=").append(k2zPosition).append(",");
		sb.append("z2kPosition=").append(z2kPosition).append(",");
		sb.append("operId=").append(operId).append(",");
		sb.append("assessId=").append(assessId).append(",");
		sb.append("assess=").append(assess).append(",");
		sb.append("assessK=").append(assessK).append(",");
		sb.append("orderId=").append(orderId).append(",");
		sb.append("carNum=").append(carNum).append(",");
		sb.append("unitId=").append(unitId).append(",");
		sb.append("driverId=").append(driverId).append(",");
		sb.append("inCarTime=").append(inCarTime).append(",");
		sb.append("outCarTime=").append(outCarTime).append(",");
		sb.append("kilomeNum=").append(kilomeNum).append(",");
		sb.append("kongKilome=").append(kongKilome).append(",");
		sb.append("plusMoney=").append(plusMoney).append(",");
		sb.append("waitTime=").append(waitTime).append(",");
		sb.append("money=").append(money).append(",");
		sb.append("coachNum=").append(coachNum).append(",");
		sb.append("cardType=").append(cardType).append("]");
		return sb.toString();
	}
}
