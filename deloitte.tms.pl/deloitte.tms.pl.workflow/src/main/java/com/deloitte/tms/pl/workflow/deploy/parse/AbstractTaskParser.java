package com.deloitte.tms.pl.workflow.deploy.parse;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.deloitte.tms.pl.workflow.process.security.Authority;
import com.deloitte.tms.pl.workflow.process.security.ComponentAuthority;

/**
 * @author Jacky.gao
 * @since 2013年12月9日
 */
public abstract class AbstractTaskParser extends AbstractParser {
	protected List<ComponentAuthority> parseComponentAuthorities(Element element){
		List<ComponentAuthority> list=new ArrayList<ComponentAuthority>();
		for(Object obj:element.elements()){
			if(!(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(!ele.getName().equals("component-authority")){
				continue;
			}
			String component=unescape(ele.attributeValue("component"));
			Authority authority=Authority.valueOf(ele.attributeValue("authority"));
			list.add(new ComponentAuthority(component,authority));
		}
		return list;
	}


}
