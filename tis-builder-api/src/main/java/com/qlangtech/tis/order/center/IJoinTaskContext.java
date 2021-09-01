/**
 * Copyright (c) 2020 QingLang, Inc. <baisui@qlangtech.com>
 * <p>
 * This program is free software: you can use, redistribute, and/or modify
 * it under the terms of the GNU Affero General Public License, version 3
 * or later ("AGPL"), as published by the Free Software Foundation.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.qlangtech.tis.order.center;

/**
 * @author 百岁（baisui@qlangtech.com）
 * @date 2020/04/13
 */
public interface IJoinTaskContext extends IParamContext {

    public String getIndexName();

    public boolean hasIndexName();

    public int getTaskId();

    /**
     * 目标索引的组数
     *
     * @return
     */
    public int getIndexShardCount();

    public <T> T getAttribute(String key);

    public void setAttribute(String key, Object v);

    /**
     * dataX 管道、incr增量管道控制器
     * @return
     */
    public IAppSourcePipelineController getPipelineController();
}
