/**
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询服务器时间请求， 无具体消息内容
 *
 * @author liangyi
 * @date 2020/8/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class P0x0004 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;
}
