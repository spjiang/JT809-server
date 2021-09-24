/**
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询服务器时间应答
 * <p>
 *  服务器时间  BCD[6]
 * </p>
 *
 * @author liangyi
 * @date 2020/8/17 14:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x8004 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务器时间  BCD[6]
     */
    private Date datetime;
}
