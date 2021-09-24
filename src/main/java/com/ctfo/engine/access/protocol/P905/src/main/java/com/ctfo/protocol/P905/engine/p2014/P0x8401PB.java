package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8401;
import com.ctfo.protocol.P905.vo.P0x8401VO;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置电话本消息体数据格式编解码处理类<code>P0x8401</code>
 *
 * <p>
 * 消息ID: 0x8401
 *
 * @author xiahancheng 2018年5月3日 上午11:10:45
 * @see
 * @version 1.0
 */
public class P0x8401PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8401消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8401 p = (P0x8401) model;
			List<P0x8401VO> dataList = p.getContactsItems();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getContactsSum());
			if (p.getContactsSum() > 0) {
				for (P0x8401VO pvo : dataList) {
					byte sign1 = pvo.getSign();
					buf.put(sign1); // 标志
					buf.put(ConvUtil.string2Bytes(pvo.getPhoneNumber()));// 电话号码
					buf.put((byte) 0);
					buf.put(ConvUtil.string2Bytes(pvo.getContacts())); // 联系人
					buf.put((byte) 0);
				}
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8401编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x8401消息体编码失败，消息为空或者长度不足。");
		}
		try {
			P0x8401 p = new P0x8401();
			p.setContactsSum(data[0]);
			if (data[0] == 0) {
				return p;
			}
			byte[] contactItems = new byte[data.length - 1]; // 联系人项内容
			System.arraycopy(data, 1, contactItems, 0, contactItems.length);

			List<P0x8401VO> contactsList = new ArrayList<P0x8401VO>();
			int pos = 0;
			int index = 0;
			int zeroNum = 1;
			P0x8401VO pvo = new P0x8401VO();
			for (int i = 0; i < contactItems.length; i++) {
				if (contactItems[i] == 0) {
					byte[] item = new byte[pos];
					System.arraycopy(contactItems, index, item, 0, item.length);
					index = i + 1;
					if (zeroNum == 1) {
						pvo.setSign(item[0]);
						byte[] tel = new byte[item.length - 1]; // 电话号码
						System.arraycopy(item, 1, tel, 0, tel.length);
						pvo.setPhoneNumber(ConvUtil.bytes2String(tel));
						zeroNum ++;
					} else {
						pvo.setContacts(ConvUtil.bytes2String(item));
						zeroNum = 1;
						contactsList.add(pvo);
						pvo = new P0x8401VO();
					}
					pos = 0;
					continue;
				}
				pos ++;
			}
			p.setContactsItems(contactsList);
			return p;

		} catch (Exception e) {
			throw new P905Exception("P0x8401编码失败:", e);
		}
	}

}
