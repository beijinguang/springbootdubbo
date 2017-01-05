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
	public String setDatabaseKey() {
//		String key = DatabaseContextHolder.getDatabaseKey();
//		if (key == null) {
//			key = RpcContext.getContext().getAttachment(DatabaseContants.DB_KEY);
//		}
//		return key;
		return "db"+ (new Random().nextInt(3)+1);
	}


}
