/**  
 * generate input:systemStatusOptionsTag tag
 * 功能详细描述
 * @author weijia
 * @create 2016年3月20日 下午8:59:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
package com.deloitte.tms.pl.core.taglib;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.service.IUserService;

@Component("systemStatusOptionsTag")
public class SystemStatusOptionsTag extends BaseOptionTag implements ApplicationContextAware{
	
	 // @Resource
	  DictionaryService dictionaryService;
	  

	  
	  private static ApplicationContext applicationContext = null;
	  
	  private String parentCode;

	public int doEndTag() throws JspException {
		
		if(dictionaryService==null){
			dictionaryService=(DictionaryService) applicationContext.getBean("dictionaryService");
		}
		if(parentCode==null){
			return EVAL_PAGE;
		}
//		SystemStatusLabelService service = (SystemStatusLabelService) Framework.getServiceRepository().getService("CommonCodeService");
		
		 
		Collection<DictionaryEntity> results=dictionaryService.loadDictionaryEntities(parentCode);
		if(results==null||results.size()==0){
			return EVAL_PAGE;
		}
		
		for(DictionaryEntity entity:results){
			String name=entity.getName();
			if(AssertHelper.notEmpty(name)){
				outOption(entity.getCode(),name); 
			}
		}
		
		return super.doEndTag();
	}

	public int doStartTag() throws JspException {

		return super.doStartTag();
	}
	
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
			
	}
}
