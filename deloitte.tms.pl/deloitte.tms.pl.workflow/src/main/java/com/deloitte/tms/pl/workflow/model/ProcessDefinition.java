package com.deloitte.tms.pl.workflow.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deloitte.tms.pl.workflow.diagram.ProcessDiagram;
import com.deloitte.tms.pl.workflow.process.node.Node;
import com.deloitte.tms.pl.workflow.process.node.StartNode;
import com.deloitte.tms.pl.workflow.process.security.ComponentAuthority;
import com.deloitte.tms.pl.workflow.process.swimlane.Swimlane;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
/**
 * @author Jacky.gao
 * @since 2013年8月18日
 */
@Entity
@Table(name=TablePreDef.WORKFLOW+"PROCESS")
public class ProcessDefinition{
	@Id
	@Column(name="ID_")
	private long id;
	
	@Column(name="NAME_",length=60)
	private String name;
	
	@Column(name="KEY_",length=60)
	private String key;
	
	@Column(name="START_PROCESS_URL_",length=120)
	private String startProcessUrl;
	
	@Column(name="VERSION_")
	private int version;
	
	@Column(name="CREATE_DATE_")
	private Date createDate;
	
	@Column(name="EFFECT_DATE_")
	private Date effectDate;
	
	@Column(name="CATEGORY_ID_",length=60)
	private String categoryId;
	
	@Column(name="DESCRIPTION_",length=120)
	private String description;
	
	@Transient
	private String eventHandlerBean;
	
	@Transient
	private StartNode startNode;

	@Transient
	private List<Node> nodes;
	
	@Transient
	private List<Swimlane> swimlanes;
	
	@Transient
	private List<ComponentAuthority> componentSecuritys;

	@Transient
	private ProcessDiagram diagram;
	
	public Node getNode(String name){
		for(Node node:nodes){
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	
	public Swimlane getSwimlane(String name){
		for(Swimlane swimlane:swimlanes){
			if(swimlane.getName().equals(name)){
				return swimlane;
			}
		}
		return null;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getStartProcessUrl() {
		return startProcessUrl;
	}
	public void setStartProcessUrl(String startProcessUrl) {
		this.startProcessUrl = startProcessUrl;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public StartNode getStartNode() {
		return startNode;
	}

	public void setStartNode(StartNode startNode) {
		this.startNode = startNode;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Swimlane> getSwimlanes() {
		return swimlanes;
	}

	public void setSwimlanes(List<Swimlane> swimlanes) {
		this.swimlanes = swimlanes;
	}

	public List<ComponentAuthority> getComponentSecuritys() {
		return componentSecuritys;
	}

	public void setComponentSecuritys(List<ComponentAuthority> componentSecuritys) {
		this.componentSecuritys = componentSecuritys;
	}

	public String getEventHandlerBean() {
		return eventHandlerBean;
	}

	public void setEventHandlerBean(String eventHandlerBean) {
		this.eventHandlerBean = eventHandlerBean;
	}

	public ProcessDiagram getDiagram() {
		return diagram;
	}

	public void setDiagram(ProcessDiagram diagram) {
		this.diagram = diagram;
	}
}
