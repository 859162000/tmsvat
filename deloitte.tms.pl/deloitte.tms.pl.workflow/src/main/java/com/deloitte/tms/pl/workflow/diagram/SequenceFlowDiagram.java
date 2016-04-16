package com.deloitte.tms.pl.workflow.diagram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2013年9月8日
 */
public class SequenceFlowDiagram extends Diagram implements Cloneable{
	private static final long serialVersionUID = 1L;
	private List<Point> points;
	private String to;
	private String labelPosition;
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SequenceFlowDiagram diagram=(SequenceFlowDiagram)super.clone();
		if(points!=null){
			List<Point> list=new ArrayList<Point>();
			for(Point p:points){
				list.add((Point)p.clone());
			}
			diagram.setPoints(list);
		}
		return diagram;
	}
}
