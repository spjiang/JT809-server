package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 位置基本信息数据格式Model定义
 * <p>
 * 14+0 报警标志 UINT32 详见表 22 报警标志位定义<br>
 * 14+4 状态 UINT32 详见表 23 状态位定义<br>
 * 14+8 纬度 UINT32 1/10000分<br>
 * 14+12 经度 UINT32 1/10000分<br>
 * 14+16 速度 UINT16 1/10KM/H<br>
 * 14+18 方向 UINT8 0—178,刻度为2度，正北为0，顺时针<br>
 * 14+19 时间 BCD[6] YY-MM-DD-hh-mm-ss<br>
 *
 * @author 凉意 2018年4月22日 下午2:31:20
 * @see
 * @version 1.0
 */
public class P0x0200 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 报警标志位
	 */
	private int armFlag;

	/**
	 * 状态标志位
	 */
	private int stateFlag;

	/**
	 * 纬度：1/10000分
	 */
	private int lat;

	/**
	 * 经度：1/10000分
	 */
	private int lng;

	/**
	 * 速度：1/10KM/H
	 */
	private short speed;

	/**
	 * 方向：0—178,刻度为2度，正北为0，顺时针
	 */
	private byte direction;

	/**
	 * 上报时间BCD[6]，格式：YY-MM-DD-hh-mm-ss
	 */
	private Date upTime;

	/**
	 * 附加信息，KEY采用，附加ID_消息长度
	 * <p>
	 * 附加信息ID 附加信息长度 说明<br>
	 * 0x01 4 里程，UINT32，1/10KM，对应车上里程表读数<br>
	 * 0x02 2 油量，UINT16，1/10L，对应车上油量表读数<br>
	 * 0x03 海拔高度，INT16，米<br>
	 * 0X04～0XF 预留<br>
	 * 0X11 1或5 超速报警附加信息<br>
	 * 0x12 6 进出区域/路线报警附加信息<br>
	 * 0x13 7 路段行驶时间不足/过长报警附加信息<br>
	 * 0x14 4 禁行路段行驶报警附加信息<br>
	 */
	private Map<String, Object> additionalMap = new HashMap<String, Object>();

	/**
	 * @return the armFlag
	 */
	public int getArmFlag() {
		return armFlag;
	}

	/**
	 * @param armFlag
	 *            the armFlag to set
	 */
	public void setArmFlag(int armFlag) {
		this.armFlag = armFlag;
	}

	/**
	 * @return the stateFlag
	 */
	public int getStateFlag() {
		return stateFlag;
	}

	/**
	 * @param stateFlag
	 *            the stateFlag to set
	 */
	public void setStateFlag(int stateFlag) {
		this.stateFlag = stateFlag;
	}

	/**
	 * @return the lat
	 */
	public int getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(int lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public int getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(int lng) {
		this.lng = lng;
	}

	/**
	 * @return the speed
	 */
	public short getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(short speed) {
		this.speed = speed;
	}

	/**
	 * @return the direction
	 */
	public byte getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(byte direction) {
		this.direction = direction;
	}

	/**
	 * @return the upTime
	 */
	public Date getUpTime() {
		return upTime;
	}

	/**
	 * @param upTime
	 *            the upTime to set
	 */
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	/**
	 * @return the additionalMap
	 */
	public Map<String, Object> getAdditionalMap() {
		return additionalMap;
	}

	/**
	 * @param additionalMap
	 *            the additionalMap to set
	 */
	public void setAdditionalMap(Map<String, Object> additionalMap) {
		this.additionalMap = additionalMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0200 [");
		sb.append("armFlag=").append(this.armFlag).append(",");
		sb.append("stateFlag=").append(this.stateFlag).append(",");
		sb.append("lng=").append(this.lng).append(",");
		sb.append("lat=").append(this.lat).append(",");
		sb.append("speed=").append(this.speed).append(",");
		sb.append("direction=").append(this.direction).append(",");
		sb.append("upTime=").append(this.upTime).append(",");
		sb.append("additionalMap=").append(this.additionalMap).append("]");
		return super.toString();
	}
}
