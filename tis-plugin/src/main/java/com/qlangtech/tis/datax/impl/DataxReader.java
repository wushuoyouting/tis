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
package com.qlangtech.tis.datax.impl;

import com.qlangtech.tis.TIS;
import com.qlangtech.tis.datax.IDataxReader;
import com.qlangtech.tis.extension.Describable;
import com.qlangtech.tis.extension.Descriptor;
import com.qlangtech.tis.fullbuild.IFullBuildContext;
import com.qlangtech.tis.plugin.KeyedPluginStore;

import java.util.Objects;

/**
 * datax Reader
 *
 * @author 百岁（baisui@qlangtech.com）
 * @date 2021-04-07 14:48
 */
public abstract class DataxReader implements Describable<DataxReader>, IDataxReader {

    /**
     * save
     *
     * @param appname
     */
    public static KeyedPluginStore<DataxReader> getPluginStore(String appname) {
        KeyedPluginStore<DataxReader> pluginStore = new KeyedPluginStore(new AppKey(appname));
//        Optional<Context> context = Optional.empty();
//        pluginStore.setPlugins(null, context, Collections.singletonList(new Descriptor.ParseDescribable(appSource)));
        return pluginStore;
    }

    /**
     * load
     *
     * @param appName
     * @return
     */
    public static DataxReader load(String appName) {
        DataxReader appSource = getPluginStore(appName).getPlugin();
        Objects.requireNonNull(appSource, "appName:" + appName + " relevant appSource can not be null");
        return appSource;
    }


    public static class AppKey extends KeyedPluginStore.Key<DataxReader> {
        public AppKey(String dataxName) {
            super(IFullBuildContext.NAME_APP_DIR, dataxName, DataxReader.class);
        }
    }


    @Override
    public Descriptor<DataxReader> getDescriptor() {
        return TIS.get().getDescriptor(this.getClass());
    }
}
