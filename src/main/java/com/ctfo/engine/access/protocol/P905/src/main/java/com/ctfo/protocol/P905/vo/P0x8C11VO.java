package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 驾驶员从业资格扩展信息定义
 *
 * @author 凉意 2018年10月27日 下午3:35:09
 * @see
 * @version 1.0
 */
public class P0x8C11VO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 0	信息版本号	BCD[6]	平台从业资格扩展信息数据库更新日期
	 */
	private String informationVersion;
	/**
	 * 6	从业资格证号	ASCII[19]	驾驶员的从业资格证号，ASCII或汉字内码字符，长度不足19byte，右补0x00
	 */
	private String quacertifi;
	/**
	 * 25	姓名	ASCII[12]	驾驶员的姓名，ASCII或汉字内码，长度不足12byte，右补0x00，
	 */
	private String driverName;
	/**
	 * 37	信用等级	UINT8	0：未评定; 1：一星; 2：二星 3：三星;   4：四星; 5：五星
	 */
	private byte creditLevel;
	/**
	 * 38	服务工号	ASCII[12]	驾驶员服务工号简称，ASCII或汉字内码，长度不足12byte，右补0x00
	 */
	private String serviceNum;
	/**
	 * 50	服务单位代码标识	ASCII[32]	ASCII或汉字内码，长度不足32byte，右补0x00
	 */
	private String serviceUnitCode;
	/**
	 * 82	服务单位名称	ASCII[40]	最大存储20汉字，ASCII或汉字内码，用于机读设备读取，便于稽查（简称）
	 */
	private String serviceUnitName;
	/**
	 * 122	投诉电话	ASCII[12]	ASCII字符，长度不足12byte，右补0x00
	 */
	private String complaintTel;
	/**
	 * 134	核发机关	ASCII[40]	最大存储20汉字, ASCII或汉字内码，
	 */
	private String nuclearAuthority;
	/**
	 * 174	从业资格类别	ASCII[48]	ASCII字符，采用从业资格类别简写形式
	 */
	private String quacertifiType;
	/**
	 * 222	发证机关	ASCII[48]	ASCII字符，长度不足48byte，右补0x00
	 */
	private String issuingOrgan;
	/**
	 * 270	有效期开始时间	BCD[4]	Yyyymmdd，如“2010-07-01”存储为0x20，0x10，0x07，0x01
	 */
	private Date effectiveStartTime;
	/**
	 * 274	有效期截止时间	BCD[4]	Yyyymmdd，如“2010-07-01”存储为0x20，0x10，0x07，0x01
	 */
	private Date effectiveEndTime;
	/**
	 * 278	考核周期	BCD[8]	前四个字节存储起始时间，后四字节存储结束时间，如“2009-07-01至2010-07-01”存储为0x20，0x09,0x07,0x01，0x20，0x10,0x07,0x01
	 */
	private String assessmentCycle;
	/**
	 * 286	考核时间	BCD[4]	如“2009-07-01”存储为0x20，0x09,0x07,0x01
	 */
	private Date assessmentTime;
	/**
	 * 290	考核周期累计记分	BCD[2]	BCD码  xxx.x
	 */
	private float assessmentCycleCumulativeScore;
	/**
	 * 292	司机外卡号	BYTE[19]	司机的外卡号，不足19位右补0
	 */
	private String driverCardNum;
	/**
	 * 311	准驾的车牌号	BYTE[9]	车牌号，ASCII字符，不含汉字，不足长度右补0x00
	 */
	private String vehicleNo;
	/**
	 * 320	RFU	BYTE[512-347]	预留字段，填充0x00
	 */
	private String extend;

	public byte[] bodyToBytes() throws P905Exception {
		IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
		buf.put(ConvUtil.bcd2Bytes(this.informationVersion));
		byte[] quacertifi = ConvUtil.string2Bytes(this.quacertifi);
		buf.put(quacertifi);
		if (19 > quacertifi.length) {
			int c = 19 - quacertifi.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] driverName = ConvUtil.string2Bytes(this.driverName);
		buf.put(driverName);
		if (12 > driverName.length) {
			int c = 12 - driverName.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		buf.put(this.creditLevel);
		byte[] serviceNum = ConvUtil.string2Bytes(this.serviceNum);
		buf.put(serviceNum);
		if (12 > serviceNum.length) {
			int c = 12 - serviceNum.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] serviceUnitCode = ConvUtil.string2Bytes(this.serviceUnitCode);
		buf.put(serviceUnitCode);
		if (32 > serviceUnitCode.length) {
			int c = 32 - serviceUnitCode.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] serviceUnitName = ConvUtil.string2Bytes(this.serviceUnitName);
		buf.put(serviceUnitName);
		if (40 > serviceUnitName.length) {
			int c = 40 - serviceUnitName.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] complaintTel = ConvUtil.string2Bytes(this.complaintTel);
		buf.put(complaintTel);
		if (12 > complaintTel.length) {
			int c = 12 - complaintTel.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] nuclearAuthority = ConvUtil.string2Bytes(this.nuclearAuthority);
		buf.put(nuclearAuthority);
		if (40 > nuclearAuthority.length) {
			int c = 40 - nuclearAuthority.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] quacertifiType = ConvUtil.string2Bytes(this.quacertifiType);
		buf.put(quacertifiType);
		if (48 > quacertifiType.length) {
			int c = 48 - quacertifiType.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] issuingOrgan = ConvUtil.string2Bytes(this.issuingOrgan);
		buf.put(issuingOrgan);
		if (48 > issuingOrgan.length) {
			int c = 48 - issuingOrgan.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		buf.put(ConvUtil.bcd2Bytes(sdf.format(this.effectiveStartTime)));
		buf.put(ConvUtil.bcd2Bytes(sdf.format(this.effectiveEndTime)));
		buf.put(ConvUtil.bcd2Bytes(this.assessmentCycle));
		buf.put(ConvUtil.bcd2Bytes(sdf.format(this.assessmentTime)));
		buf.put(ConvUtil.int2Bcd2Bytes((int)(this.assessmentCycleCumulativeScore * 10), 4));
		byte[] driverCardNum = ConvUtil.string2Bytes(this.driverCardNum);
		buf.put(driverCardNum);
		if (19 > driverCardNum.length) {
			int c = 19 - driverCardNum.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		byte[] vehicleNo = ConvUtil.string2Bytes(this.vehicleNo);
		buf.put(vehicleNo);
		if (9 > vehicleNo.length) {
			int c = 9 - vehicleNo.length;
			for (int i = 0; i < c; i++) {
				buf.put((byte) 0);
			}
		}
		if (StringUtils.isNotBlank(this.extend)) {
			buf.put(ConvUtil.hex2Bytes(this.extend));
		}

		buf.flip();
		byte[] b = new byte[buf.limit()];
		buf.get(b);
		return b;
	}

	public void bodyFromBytes(byte[] data) throws P905Exception{
		int pos = 0;
		byte[] informationVersion = new byte[6];
		System.arraycopy(data, pos, informationVersion, 0, 6);
		this.informationVersion = ConvUtil.bytes2Bcd(informationVersion);
		pos += 6;
		byte[] quacertifi = new byte[19];
		System.arraycopy(data, pos, quacertifi, 0, 19);
		this.quacertifi = ConvUtil.bytes2String(quacertifi);
		pos += 19;
		byte[] driverName = new byte[12];
		System.arraycopy(data, pos, driverName, 0, 12);
		this.driverName = ConvUtil.bytes2String(driverName);
		pos += 12;
		this.creditLevel = data[pos];
		pos ++;
		byte[] serviceNum = new byte[12];
		System.arraycopy(data, pos, serviceNum, 0, 12);
		this.serviceNum = ConvUtil.bytes2String(serviceNum);
		pos += 12;
		byte[] serviceUnitCode = new byte[32];
		System.arraycopy(data, pos, serviceUnitCode, 0, 32);
		this.serviceUnitCode = ConvUtil.bytes2String(serviceUnitCode);
		pos += 32;
		byte[] serviceUnitName = new byte[40];
		System.arraycopy(data, pos, serviceUnitName, 0, 40);
		this.serviceUnitName = ConvUtil.bytes2String(serviceUnitName);
		pos += 40;
		byte[] complaintTel = new byte[12];
		System.arraycopy(data, pos, complaintTel, 0, 12);
		this.complaintTel = ConvUtil.bytes2String(complaintTel);
		pos += 12;
		byte[] nuclearAuthority = new byte[40];
		System.arraycopy(data, pos, nuclearAuthority, 0, 40);
		this.nuclearAuthority = ConvUtil.bytes2String(nuclearAuthority);
		pos += 40;
		byte[] quacertifiType = new byte[48];
		System.arraycopy(data, pos, quacertifiType, 0, 48);
		this.quacertifiType = ConvUtil.bytes2String(quacertifiType);
		pos += 48;
		byte[] issuingOrgan = new byte[48];
		System.arraycopy(data, pos, issuingOrgan, 0, 48);
		this.issuingOrgan = ConvUtil.bytes2String(issuingOrgan);
		pos += 48;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		byte[] effectiveStartTime = new byte[4];
		System.arraycopy(data, pos, effectiveStartTime, 0, 4);
		try {
			this.effectiveStartTime = sdf.parse(ConvUtil.bytes2Bcd(effectiveStartTime));
		} catch (ParseException e) {
			throw new P905Exception("effectiveStartTime解析错误.") ;
		}
		pos += 4;
		byte[] effectiveEndTime = new byte[4];
		System.arraycopy(data, pos, effectiveEndTime, 0, 4);
		try {
			this.effectiveEndTime = sdf.parse(ConvUtil.bytes2Bcd(effectiveEndTime));
		} catch (ParseException e) {
			throw new P905Exception("effectiveEndTime解析错误.") ;
		}
		pos += 4;
		byte[] assessmentCycle = new byte[8];
		System.arraycopy(data, pos, assessmentCycle, 0, 8);
		this.assessmentCycle = ConvUtil.bytes2String(assessmentCycle);
		pos += 8;
		byte[] assessmentTime = new byte[4];
		System.arraycopy(data, pos, assessmentTime, 0, 4);
		try {
			this.assessmentTime = sdf.parse(ConvUtil.bytes2Bcd(assessmentTime));
		} catch (ParseException e) {
			throw new P905Exception("assessmentTime解析错误.") ;
		}
		pos += 4;
		byte[] assessmentCycleCumulativeScore = new byte[2];
		System.arraycopy(data, pos, assessmentCycleCumulativeScore, 0, 2);
		this.assessmentCycleCumulativeScore = ConvUtil.bytes2Bcd2Int(assessmentCycleCumulativeScore, 4) / 10f;
		pos += 2;
		byte[] driverCardNum = new byte[19];
		System.arraycopy(data, pos, driverCardNum, 0, 19);
		this.driverCardNum = ConvUtil.bytes2String(driverCardNum);
		pos += 19;
		byte[] vehicleNo = new byte[9];
		System.arraycopy(data, pos, vehicleNo, 0, 9);
		this.vehicleNo = ConvUtil.bytes2String(vehicleNo);
		pos += 9;
		byte[] extend = new byte[data.length - pos];
		System.arraycopy(data, pos, extend, 0, extend.length);
		this.extend = ConvUtil.bytes2Hex(extend);
	}

	public String getInformationVersion() {
		return informationVersion;
	}
	public void setInformationVersion(String informationVersion) {
		this.informationVersion = informationVersion;
	}
	public String getQuacertifi() {
		return quacertifi;
	}
	public void setQuacertifi(String quacertifi) {
		this.quacertifi = quacertifi;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/**
	 * @return the creditLevel
	 */
	public byte getCreditLevel() {
		return creditLevel;
	}
	/**
	 * @param creditLevel the creditLevel to set
	 */
	public void setCreditLevel(byte creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}
	public String getServiceUnitCode() {
		return serviceUnitCode;
	}
	public void setServiceUnitCode(String serviceUnitCode) {
		this.serviceUnitCode = serviceUnitCode;
	}
	public String getServiceUnitName() {
		return serviceUnitName;
	}
	public void setServiceUnitName(String serviceUnitName) {
		this.serviceUnitName = serviceUnitName;
	}
	public String getComplaintTel() {
		return complaintTel;
	}
	public void setComplaintTel(String complaintTel) {
		this.complaintTel = complaintTel;
	}
	public String getNuclearAuthority() {
		return nuclearAuthority;
	}
	public void setNuclearAuthority(String nuclearAuthority) {
		this.nuclearAuthority = nuclearAuthority;
	}
	public String getQuacertifiType() {
		return quacertifiType;
	}
	public void setQuacertifiType(String quacertifiType) {
		this.quacertifiType = quacertifiType;
	}
	public String getIssuingOrgan() {
		return issuingOrgan;
	}
	public void setIssuingOrgan(String issuingOrgan) {
		this.issuingOrgan = issuingOrgan;
	}
	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}
	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}
	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}
	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}
	public String getAssessmentCycle() {
		return assessmentCycle;
	}
	public void setAssessmentCycle(String assessmentCycle) {
		this.assessmentCycle = assessmentCycle;
	}
	public Date getAssessmentTime() {
		return assessmentTime;
	}
	public void setAssessmentTime(Date assessmentTime) {
		this.assessmentTime = assessmentTime;
	}
	public float getAssessmentCycleCumulativeScore() {
		return assessmentCycleCumulativeScore;
	}
	public void setAssessmentCycleCumulativeScore(float assessmentCycleCumulativeScore) {
		this.assessmentCycleCumulativeScore = assessmentCycleCumulativeScore;
	}
	public String getDriverCardNum() {
		return driverCardNum;
	}
	public void setDriverCardNum(String driverCardNum) {
		this.driverCardNum = driverCardNum;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("P0x8C11VO [");
		buf.append("informationVersion=").append(this.informationVersion).append(",");
		buf.append("quacertifi=").append(this.quacertifi).append(",");
		buf.append("driverName=").append(this.driverName).append(",");
		buf.append("creditLevel=").append(this.creditLevel).append(",");
		buf.append("serviceNum=").append(this.serviceNum).append(",");
		buf.append("serviceUnitCode=").append(this.serviceUnitCode).append(",");
		buf.append("serviceUnitName=").append(this.serviceUnitName).append(",");
		buf.append("complaintTel=").append(this.complaintTel).append(",");
		buf.append("nuclearAuthority=").append(this.nuclearAuthority).append(",");
		buf.append("quacertifiType=").append(this.quacertifiType).append(",");
		buf.append("issuingOrgan=").append(this.issuingOrgan).append(",");
		buf.append("effectiveStartTime=").append(this.effectiveStartTime).append(",");
		buf.append("effectiveEndTime=").append(this.effectiveEndTime).append(",");
		buf.append("assessmentCycle=").append(this.assessmentCycle).append(",");
		buf.append("assessmentTime=").append(this.assessmentTime).append(",");
		buf.append("assessmentCycleCumulativeScore=").append(this.assessmentCycleCumulativeScore).append(",");
		buf.append("driverCardNum=").append(this.driverCardNum).append(",");
		buf.append("vehicleNo=").append(this.vehicleNo).append(",");
		buf.append("extend=").append(this.extend).append("]");
		return buf.toString();
	}

}
