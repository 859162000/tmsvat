package com.deloitte.tms.pl.workflow.deploy.parse.impl;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.workflow.diagram.NodeDiagram;
import com.deloitte.tms.pl.workflow.diagram.Point;
import com.deloitte.tms.pl.workflow.diagram.ProcessDiagram;
import com.deloitte.tms.pl.workflow.diagram.SequenceFlowDiagram;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.process.node.Node;

/**
 * @author Jacky.gao
 * @since 2013年9月8日
 */
public class ProcessDiagramBudiler {
	private int width=0;
	private int height=0;
	private int nodeWidth=0;
	private int nodeHeight=0;
	private ProcessDefinition process;
	private ProcessDiagram diagram;
	private List<SequenceFlowImpl> processedSequenceFLows=new ArrayList<SequenceFlowImpl>();
	public ProcessDiagramBudiler(ProcessDefinition process){
		this.process=process;
		createDiagram();
	}
	
	private void createDiagram(){
		buildSize(process.getStartNode());		
		diagram=new ProcessDiagram();
		diagram.setName(process.getName());
		diagram.setWidth(width+nodeWidth+10);
		diagram.setHeight(height+nodeHeight+10);
		List<NodeDiagram> nodeDiagrams=new ArrayList<NodeDiagram>();
		for(Node node:process.getNodes()){
			NodeDiagram diagram=node.getDiagram();
			if(node.getSequenceFlows()!=null && node.getSequenceFlows().size()>0){
				List<SequenceFlowDiagram> flowDiagrams=new ArrayList<SequenceFlowDiagram>();
				for(SequenceFlowImpl flow:node.getSequenceFlows()){
					flowDiagrams.add(flow.getDiagram());
				}
				diagram.setSequenceFlowDiagrams(flowDiagrams);
			}
			nodeDiagrams.add(diagram);
			
		}
		diagram.setNodeDiagrams(nodeDiagrams);
	}
	private void buildSize(Node targetNode){
		NodeDiagram d=targetNode.getDiagram();
		if(d.getX()>width){
			width=d.getX();
		}
		if(d.getY()>height){
			height=d.getY();
		}
		if(d.getWidth()>nodeWidth){
			nodeWidth=d.getWidth();
		}
		if(d.getHeight()>nodeHeight){
			nodeHeight=d.getHeight();
		}
		List<SequenceFlowImpl> flows=targetNode.getSequenceFlows();
		if(flows==null)return;
		for(SequenceFlowImpl flow:flows){
			if(processedSequenceFLows.contains(flow)){
				continue;
			}else{
				processedSequenceFLows.add(flow);
			}
			SequenceFlowDiagram diagram=flow.getDiagram();
			buildPoint(diagram.getPoints());
			Node node=process.getNode(flow.getToNode());
			buildSize(node);
		}
	}
	
	private void buildPoint(List<Point> points){
		if(points==null)return;
		for(Point p:points){
			if(p.getX()>width){
				width=p.getX();
			}
			if(p.getY()>height){
				height=p.getY();
			}
		}
	}

	public ProcessDiagram getDiagram(){
		return diagram;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
