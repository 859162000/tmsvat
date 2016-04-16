package com.deloitte.tms.pl.workflow.diagram;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2013年10月12日
 */
public interface TaskDiagramInfoProvider {
	boolean disable();
	String getInfo(String nodeName,List<TaskInfo> tasks);
}
