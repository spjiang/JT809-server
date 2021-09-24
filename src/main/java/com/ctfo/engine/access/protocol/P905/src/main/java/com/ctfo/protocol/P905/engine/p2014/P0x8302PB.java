package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8302;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 提问下发消息体数据格式编解码处理类<code>P0x8302</code>
 *
 * <p>
 * 消息ID: 0x8302
 *
 * @author xiahancheng 2018年5月2日 下午4:54:47
 * @see
 * @version 1.0
 */
public class P0x8302PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8302消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8302 p = (P0x8302) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getSign());
			buf.putInt(p.getProblemID());
			buf.put(ConvUtil.string2Bytes(p.getProblem()));
			buf.put((byte)0);
			Map<Byte, String> answer = p.getAnswer();
			for(Entry<Byte, String> e : answer.entrySet()){
				buf.put(e.getKey()); //答案ID
				buf.put(ConvUtil.string2Bytes(e.getValue())); //答案內容
				buf.put((byte)0);
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8302编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if ( null == data || 0 >= data.length) {
			throw new P905Exception("P0x8302消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8302 p = new P0x8302();
			int pos = 0;
			p.setSign(data[pos]);
			pos++;
			p.setProblemID(ConvUtil.bytes2Int(data, pos));
			pos += 4;
			// 找到第一个已字节0结束的字符串【问题长度】
			int dataLen = data.length;
			for (int i = pos; i < dataLen; i++) {
				if (data[i] == 0x00) {
					pos = i;
					break;
				}
			}
			byte[] problem = new byte[pos - 4];  //问题
			System.arraycopy(data, 5, problem, 0, pos - 4);
			p.setProblem(ConvUtil.bytes2String(problem));

			byte[] answers = new byte[data.length - pos - 1]; //候选答案项内容
			System.arraycopy(data, pos + 1, answers, 0, answers.length);

			Map<Byte, String> answerItemMap = new HashMap<Byte, String>();
			int pos2 = 0;

			for (int i = 0; i < answers.length; i++) {
				if (answers[i] == 0) {
					byte[] answer = new byte[i - pos2]; // 一个答案项
					System.arraycopy(answers,pos2, answer, 0, answer.length);
					byte[] answerContent = new byte[answer.length - 1];
					System.arraycopy(answer,1, answerContent, 0, answerContent.length);
					answerItemMap.put(answer[0], ConvUtil.bytes2String(answerContent));
					pos2 += answer.length +1;
				}
			}
			p.setAnswer(answerItemMap);
			return p;

		}catch (Exception e) {
			throw new P905Exception("P0x8302编码失败:", e);
		}
	}


}
