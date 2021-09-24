/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 终端补传分包请求
 * <p>
 *     终端补传分包请求消息体数据格式同服务器补传分包请求消息体格式，参见<code>P0x8003</code>
 * </p>
 *
 * @author liangyi
 * @date 2020/8/17 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0005 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原始消息流水号 2字节
     * <p>
     *     对应要求补传的原始消息第一包的消息流水号
     * </p>
     */
    private short originalFlowNo;

    /**
     * 重传包总数， 1字节
     */
    private byte againPackTotal;

    /**
     * 重传包ID列表
     */
    private List<Short> againPackIds;
}
