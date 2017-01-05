package com.idea4j.common;

import com.alibaba.dubbo.rpc.RpcContext;
import com.idea4j.common.shardb.DatabaseContants;
import com.idea4j.common.shardb.DatabaseContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);
    protected Object determineCurrentLookupKey() {
        String key = (String) getKey();
        LOGGER.debug("current key"+key);
        return key;
    }
    //此处可以选择数据库
    private Object getKey() {
        String key = DatabaseContextHolder.getDatabaseKey();
        if (key == null) {
            key = RpcContext.getContext().getAttachment(DatabaseContants.DB_KEY);
        }
        return key;
    }
}