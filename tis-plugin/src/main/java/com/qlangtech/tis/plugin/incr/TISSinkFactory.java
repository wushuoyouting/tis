/**
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.qlangtech.tis.plugin.incr;

import com.google.common.collect.Maps;
import com.qlangtech.tis.TIS;
import com.qlangtech.tis.datax.IDataXPluginMeta;
import com.qlangtech.tis.datax.IDataxProcessor;
import com.qlangtech.tis.extension.Describable;
import com.qlangtech.tis.extension.Descriptor;
import com.qlangtech.tis.extension.TISExtension;
import com.qlangtech.tis.util.HeteroEnum;
import com.qlangtech.tis.util.Selectable;

import java.util.Map;

/**
 * @author: 百岁（baisui@qlangtech.com）
 * @create: 2021-09-29 10:50
 **/
public abstract class TISSinkFactory implements Describable<TISSinkFactory> {

    @TISExtension
    public static final HeteroEnum<TISSinkFactory> sinkFactory = new HeteroEnum<TISSinkFactory>(//
            TISSinkFactory.class, //
            "sinkFactory", //
            "Incr Sink Factory", //
            Selectable.Single, true);

    /**
     * Map< IDataxProcessor.TableAlias, <SinkFunction<DTO> >
     *
     * @param dataxProcessor
     * @return
     */
    public abstract <SinkFunc> Map<IDataxProcessor.TableAlias, SinkFunc> createSinkFunction(IDataxProcessor dataxProcessor);

    @Override
    public final Descriptor<TISSinkFactory> getDescriptor() {
        Descriptor<TISSinkFactory> descriptor = TIS.get().getDescriptor(this.getClass());
        Class<BaseSinkFunctionDescriptor> expectClazz = getExpectDescClass();
        if (!(expectClazz.isAssignableFrom(descriptor.getClass()))) {
            throw new IllegalStateException(descriptor.getClass() + " must implement the Descriptor of " + expectClazz.getName());
        }
        return descriptor;
    }

    protected <TT extends BaseSinkFunctionDescriptor> Class<TT> getExpectDescClass() {
        return (Class<TT>) BaseSinkFunctionDescriptor.class;
    }


    public static abstract class BaseSinkFunctionDescriptor extends Descriptor<TISSinkFactory> {
        @Override
        public Map<String, Object> getExtractProps() {
            Map<String, Object> vals = Maps.newHashMap();
            IDataXPluginMeta.EndType targetType = this.getTargetType();
            vals.put(IDataXPluginMeta.END_TARGET_TYPE, targetType.getVal());
            return vals;
        }

        protected abstract IDataXPluginMeta.EndType getTargetType();
    }
}
