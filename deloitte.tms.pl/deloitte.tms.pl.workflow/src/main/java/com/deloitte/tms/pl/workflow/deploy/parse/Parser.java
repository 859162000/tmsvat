package com.deloitte.tms.pl.workflow.deploy.parse;

import org.dom4j.Element;

public interface Parser {
	Object parse(Element element,long processId,boolean parseChildren);
	boolean support(Element element);
}
