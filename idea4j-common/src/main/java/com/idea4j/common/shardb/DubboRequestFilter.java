package com.idea4j.common.shardb;

import com.alibaba.dubbo.rpc.*;

import java.util.Random;

/**
 * 客户端传递数据库的key
 */
public class DubboRequestFilter implements Filter {

	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		setDatabaseKey();
		return invoker.invoke(invocation);
	}

	//定义分库规则 ，这里是示例，随机分配
	public void setDatabaseKey() {
		DatabaseContextHolder.setDatabaseKey("db"+(new Random().nextInt(3)+1));//
		String key = DatabaseContextHolder.getDatabaseKey();
		if (key != null) {
			RpcContext.getContext().setAttachment(DatabaseContants.DB_KEY,key);
		}
	}


}
