package com.cxy.frame.plugin;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * @author TonyJ
 * 实现执行sql的日志记录
 *	类上面必须添加@Intercepts注解，@Signature，代表拦截点，method表示需要拦截的方法，mybatis有
 *	update, query, flushStatements, commit, rollback, getTransaction, close, isClosed
 *	方法，其中，update包括新增、修改、删除等方法，query用于查询，其它的基本用不到。
 *	type表示拦截的接口类型，有Executor、StatementHandler、ParameterHandler和ResultSetHandler。
 *	args表示拦截的参数类型，有MappedStatement、Object、RowBounds和ResultHandler等等.
 */
/*@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args=Connection.class),
	 		   @Signature(type=Executor.class,method = "query",args = {MappedStatement.class,
	 			 						Object.class, RowBounds.class,ResultHandler.class }),})*/
@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
public class LogSqlPlugin implements Interceptor {

	private final static Log LOGGER = LogFactory.getLog(LogSqlPlugin.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，
		//BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，
		//SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是
		//处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个
		//StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、
		//PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
		//我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候
		//是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
		Object[] args = invocation.getArgs();
        // 获取执行的方法
        if (args.length > 1) {
            // 传入的对象
            Object obj = args[1];
            if (obj instanceof Log) {
                // 若是日志对象 则直接跳过
                return invocation.proceed();
            }
        }
        return invocation.proceed();
	}

	/**
	 * 返回对象,如果返回原对象不拦截,如果返回代理则拦截
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/** 
	 * 此方法用来获取配置参数
	 */
	@Override
	public void setProperties(Properties properties) {
		
	}


}
