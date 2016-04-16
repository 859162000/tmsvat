package com.deloitte.tms.pl.core.log;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.context.ServiceContextUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * 
 *切入web端 BaseService BaseDao 的 保存,更新,删除操作
 * 功能详细描述
 * @author bo.wang
 * @create Mar 19, 2016 11:23:42 AM 
 * @version 1.0.0
 */
@Aspect
@Component
public class LoggerAspect {

    protected final Log logger = LogFactory.getLog(getClass());

    private final static String USER = "用户";

    private final static String UNKNOWN_USER = "SYSTEM";

    private final static String START = "开始执行:";

    private final static String END = "结束执行:";
    
    @Resource(name=BaseDaoSimple.BEAN_ID)
    BaseDaoSimple baseDao;
    
//    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
//    @Pointcut("execution(* com.deloitte.tms.pl.core.service.impl.BaseService.*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDao.*(..))")
//    public void aspect() {
//    	
//    }

    /*@AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrowing(Exception ex) {
        logger.error("系统异常", ex);
    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        logger.debug(buildLoggerInfo(joinPoint, START));
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        logger.debug(buildLoggerInfo(joinPoint, END));
    }*/
//    /**
//     * 
//     * 将companyId切入到所有的查询中
//     * 功能详细描述
//     * @param joinPoint
//     * @see [相关类/方法]（可选）
//     * @since [产品/模块版本] （可选）
//     */
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    @Before("execution(* com.deloitte.tms..*.service..*.*(java.util.Map,..)) and args(params,..)")
//    public void beforeServiceMap(JoinPoint joinPoint,Map params){
//    	if(params==null){
//    		params=new HashMap();
//    	}
//    	String companyId=ContextUtils.getFixedCompanyId();
//    	params.put(TableColnumDef.COMPANY_ID_FILED_DEF, companyId);
//    	
//    	params.put(ServiceContextUtils.AOP_COMPANYID, companyId);
//    	params.put(ServiceContextUtils.AOP_USERID, ContextUtils.getCurrentUserCode());
//    	params.put(ServiceContextUtils.AOP_ORGID, ContextUtils.getCurrentOrgId());
//    }
//    /**
//     * 自动注入默认查询条件
//     * @param joinPoint
//     * @param params
//     */
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//	@Before("execution(* com.deloitte.tms..*.dao..*.*(java.util.Map,..)) and args(params,..)")
//    public void beforeDaoMap(JoinPoint joinPoint,Map params){
//    	if(params==null){
//    		params=new HashMap();
//    	}
//    	String companyId=ContextUtils.getFixedCompanyId();
//    	params.put(TableColnumDef.COMPANY_ID_FILED_DEF, companyId);
//    	logger.debug(" aspect "+joinPoint.getSignature().getName()+" with companyId:"+companyId);
//    	Object[] args=joinPoint.getArgs();
//    	String message="";
//    	for(Object arg:args){
//    		message=message+"type:"+arg.getClass()+" value:"+arg.toString()+" ";
//    	}
//    	logger.debug(message);
//    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Before("execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.find*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.load*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.page*(..)) ||execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.query*(..)) ||execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.execute*(..))||execution(* com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple.count*(..))")
    public void beforeQuery(JoinPoint joinPoint){
    	Object[] args=joinPoint.getArgs();
    	String message="";
    	for(Object arg:args){
    		message=message+"type:"+arg.getClass()+" value:"+arg.toString()+" ";
    	}
    	logger.debug(message);
    }
    @Before("execution(* com.deloitte.tms.pl.core.service.impl.BaseService.update*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDao.update*(..))")
    public void beforeUpdate(JoinPoint joinPoint){
    	Object[] args=joinPoint.getArgs();
    	for(Object arg:args){
    		proceeUpdateArg(arg);
    	}
    	logger.debug(buildLoggerInfo(joinPoint, END));
    }
    @Before("execution(* com.deloitte.tms.pl.core.service.impl.BaseService.save*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDao.save*(..))")
    public void beforeSave(JoinPoint joinPoint){
    	Object[] args=joinPoint.getArgs();
    	for(Object arg:args){
    		proceeSaveArg(arg);
    	}
    	logger.debug(buildLoggerInfo(joinPoint, END));
    }
    @Before("execution(* com.deloitte.tms.pl.core.service.impl.BaseService.remove*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDao.remove*(..))")
    public void beforeRemove(JoinPoint joinPoint){
    	Object[] args=joinPoint.getArgs();
    	for(Object arg:args){
    		if(arg instanceof BaseEntity){
    			BaseEntity baseEntity=(BaseEntity)arg;
    			if(baseEntity!=null){
    				baseEntity=(BaseEntity) baseDao.get(baseEntity.getClass(),baseEntity.getId());
    				proceeRemoveArg(baseEntity);
    			}
    		}
    	}
    	logger.debug(buildLoggerInfo(joinPoint, END));
    }
    @Before("execution(* com.deloitte.tms.pl.core.service.impl.BaseService.saveOrUpdate*(..)) || execution(* com.deloitte.tms.pl.core.dao.impl.BaseDao.saveOrUpdate*(..))")
    public void beforesaveOrUpdate(JoinPoint joinPoint){
    	Object[] args=joinPoint.getArgs();
    	for(Object arg:args){
    		if(arg instanceof BaseEntity){
    			BaseEntity baseEntity=(BaseEntity)arg;
    			if(baseEntity.getId()==null){
    				proceeSaveArg(arg);
    			}else {
					proceeUpdateArg(arg);
				}
    		}
    			
    	}
    	logger.debug(buildLoggerInfo(joinPoint, END));
    }
    private void proceeUpdateArg(Object arg) {
    	logger.debug(buildClassInfo(arg, "update"));
		if(arg instanceof BaseEntity){
			BaseEntity baseEntity=(BaseEntity)arg;
			if(baseEntity!=null){
				processBaseEntity(baseEntity);
				baseEntity.setModifiedBy(ContextUtils.getCurrentUserCode());
				String currentUser=ContextUtils.getCurrentUserCode();
				baseEntity.setModifiedBy(currentUser==null?"SYSTM":currentUser);
				baseEntity.setModifiedDate(new Date());
				baseEntity.setVersionId(baseEntity.getVersionId()==null?1:baseEntity.getVersionId()+1);
				if(baseEntity.getArchiveBaseDate()==null){
					baseEntity.setArchiveBaseDate(DateUtils.addYear(new Date(), 2));
				}
				if(baseEntity.getFlag()==null){
					baseEntity.setFlag(TableColnumDef.FLAG_EFFECT);
				}
			}
		}else if(arg instanceof Collection){
			for(Object entity:(Collection)arg){
				proceeUpdateArg(entity);
			}
		}
	}
	
	private void proceeSaveArg(Object arg) {
		logger.debug(buildClassInfo(arg, "save"));
		if(arg instanceof BaseEntity){
			BaseEntity baseEntity=(BaseEntity)arg;
			if(baseEntity!=null){
				processBaseEntity(baseEntity);
				if(baseEntity.getVersionId()==null){
					baseEntity.setVersionId(1);
				}	
				if(baseEntity.getFlag()==null){
					baseEntity.setFlag(TableColnumDef.FLAG_EFFECT);
				}
				if(baseEntity.getModifiedBy()==null){
					String currentUser=ContextUtils.getCurrentUserCode();
					baseEntity.setModifiedBy(currentUser==null?"SYSTM":currentUser);
				}
				if(baseEntity.getModifiedDate()==null){
					baseEntity.setModifiedDate(new Date());
				}
				if(baseEntity.getArchiveBaseDate()==null){
					baseEntity.setArchiveBaseDate(DateUtils.addYear(new Date(), 2));
				}
				if(baseEntity.getOperationOrgCode()==null){
					baseEntity.setOperationOrgCode(ContextUtils.getOperationOrgCode());
				}
				
			}
		}else if(arg instanceof Collection){
			for(Object entity:(Collection)arg){
				proceeSaveArg(entity);
			}
		}
	}
	private void proceeRemoveArg(Object arg) {
		logger.debug(buildClassInfo(arg, "remove"));
		if(arg instanceof BaseEntity){
			BaseEntity baseEntity=(BaseEntity)arg;
			if(baseEntity!=null){
				processBaseEntity(baseEntity);
				String currentUser=ContextUtils.getCurrentUserCode();
				baseEntity.setDeleteBy(currentUser==null?"SYSTM":currentUser);
				baseEntity.setDeleteDate(new Date());
				baseEntity.setFlag(TableColnumDef.FLAG_DISABLED);	
				if(baseEntity.getArchiveBaseDate()==null){
					baseEntity.setArchiveBaseDate(DateUtils.addYear(new Date(), 2));
				}
			}
		}else if(arg instanceof Collection){
			for(Object entity:(Collection)arg){
				proceeRemoveArg(entity);
			}
		}
	}
	private void processBaseEntity(BaseEntity baseEntity) {
		if(baseEntity.getBizOrgCode()==null){
			baseEntity.setBizOrgCode(ContextUtils.getOperationOrgCode());
		}
		if(baseEntity.getCompanyId()==null){
			baseEntity.setCompanyId(ContextUtils.getFixedCompanyId());
		}
		if(baseEntity.getCreateDate()==null){
			baseEntity.setCreateDate(new Date());
		}
		if(baseEntity.getCreatedBy()==null){
			String currentUser=ContextUtils.getCurrentUserCode();
			baseEntity.setCreatedBy(currentUser==null?"SYSTM":currentUser);
		}
		if(baseEntity.getDataowner()==null){
			baseEntity.setDataowner(ContextUtils.getDataOwner());
		}
	}
	/**
	 * 
	 *记录操作日志
	 * 功能详细描述
	 * @param joinPoint
	 * @param state
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
    private String buildLoggerInfo(JoinPoint joinPoint, String state) {
        StringBuilder builder = new StringBuilder();
        SecurityUser loginUser = ContextUtils.getCurrentUser();
        Signature signature = joinPoint.getSignature();
        if (loginUser == null) {
            builder.append(UNKNOWN_USER);
        } else {
            builder.append(USER).append("[").append(loginUser.getUsername()).append("]");
        }
        builder.append(state);
        builder.append(signature.getDeclaringType().getName());
        builder.append("->");
        builder.append(signature);
        builder.append(" at "+DateUtils.formatCurrTime(DateUtils.ISO_DATETIME_FORMAT2));
        return builder.toString();
    }
    private String buildClassInfo(Object arg,String state) {
        return "process "+state+" Class-->"+arg.getClass();
    }

}
