package com.deloitte.tms.pl.business.service.rule.logic;

public interface ILogicLoader {
	String DEFAULT_LOGIC_LOADER_DEFALUT="DEFAULT_LOGIC_LOADER_DEFALUT";
	ILogic loadLogic(String logicCode);
}
