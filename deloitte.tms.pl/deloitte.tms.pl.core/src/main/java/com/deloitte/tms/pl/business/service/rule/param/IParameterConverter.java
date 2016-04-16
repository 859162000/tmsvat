package com.deloitte.tms.pl.business.service.rule.param;
/**
 * 
* @Description: 将Iparamter转换为对应的java类型
* @author bo.wang
* @date 2013-3-26 上午10:48:41
*
 */
public interface IParameterConverter {
	Object converter(IParameter parameter);
}
