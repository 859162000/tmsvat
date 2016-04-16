package com.deloitte.tms.pl.version.party.model.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.version.party.model.Party;
import com.deloitte.tms.pl.version.party.model.organization.criteria.CriteriaFilter;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;
import com.deloitte.tms.pl.version.party.model.organization.iterator.Iterator;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;
import com.deloitte.tms.pl.version.party.model.organization.node.TreeNode;
import com.deloitte.tms.pl.version.party.model.person.ChaRole;




public class OrganizationNode extends TreeNode implements Party, Node {

	public OrganizationNode(String id, String orgName) {
		super(id, orgName);
		this.orgName = orgName;
		this.id=id;
		this.posterities.put(id, this);
	}
	
	protected String comment;

	protected String orgName;
	
	protected String orgCode;

	protected String orgPhone;

	protected String conName;

	protected String conPhone;

	protected String preName;
	
	protected Date createDate;
	
	/** 机构辖属全局渠道人员 Map<ChannelRole.id,Role> * */
	protected Map<Long, ChaRole> corpora = new HashMap<Long, ChaRole>();

	/** 机构辖属全局机构(含本机构) **/
	protected Map<Serializable, OrganizationNode> posterities = new HashMap<Serializable, OrganizationNode>();
	
	protected Object extraData;

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getConPhone() {
		return conPhone;
	}

	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Map<Long, ChaRole> getCorpora() {
		return corpora;
	}

	public Map<Serializable, OrganizationNode> getPosterities() {
		return posterities;
	}

	public Object clone() {
		OrganizationNode organization = (OrganizationNode) super.clone();
		organization.setOrgName(orgName);
		organization.setOrgCode(orgCode);
		organization.setComment(comment);
		organization.setOrgPhone(orgPhone);
		organization.setConName(conName);
		organization.setConPhone(conPhone);
		organization.posterities = new HashMap<Serializable, OrganizationNode>();
		organization.posterities.put(orgCode, organization);
		organization.corpora = new HashMap<Long, ChaRole>();
		return organization;
	}

	public String toString() {
		return "机构ID：" + orgCode + " 机构名称：" + orgName + " 机构代码：" + orgCode;
	}

	public void add(Node node) {
		super.add(node);
		OrganizationNode temp = this;
		OrganizationNode organization = (OrganizationNode) node;
		do {
			temp.getPosterities().put(organization.getOrgCode(), organization);
		} while ((temp = (OrganizationNode) temp.getParent()) != null);
	}

	/**
	 * clone自己为根节点的树结构
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Node deepClone() {
		OrganizationNode cloneNode;
		cloneNode = (OrganizationNode) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			OrganizationNode child = (OrganizationNode) enumeration
					.nextElement();
			cloneNode.add(child.deepClone());
		}
		return cloneNode;
	}

	/**
	 * clone按照filter过滤后的结构树 深层clone
	 * 
	 * @param filter
	 *            过滤器
	 * @return
	 */
	public Node deepClone(Filter filter) {
		OrganizationNode cloneNode;
		cloneNode = (OrganizationNode) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			OrganizationNode child = (OrganizationNode) enumeration
					.nextElement();
			if (filter.isPermitted(child)) {
				cloneNode.add(child.deepClone(filter));
			}
		}
		return cloneNode;

	}

	/**
	 * clone按照filter链过滤后得结构树 深层clone
	 * 
	 * @param filterChain
	 *            过滤链 List<TreeFilter>
	 * @return
	 */
	public Node deepClone(List filterChain) {
		OrganizationNode cloneNode;
		cloneNode = (OrganizationNode) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			OrganizationNode child = (OrganizationNode) enumeration
					.nextElement();
			boolean token = true;
			for (Object object : filterChain) {
				Filter filter=(Filter)object;
				if (!filter.isPermitted(child)) {
					token = false;
					break;
				}
			}
			if (token) {
				cloneNode.add(child.deepClone(filterChain));
			}
		}
		return cloneNode;
	}

	/**
	 * clone按照filter链过滤后得结构树 深层clone
	 * 
	 * @param filterChain
	 *            过滤链 List<TreeFilter>
	 * @return
	 */
	public Node deepClone(CriteriaFilter filter) {
		List<Filter> filterChain = filter.getFilterChain();
		return deepClone(filterChain);
	}

	public void genPosterities() {
		initPosterities();
		Iterator iterator =iterator();
		for (; iterator.hasNext();) {
			OrganizationNode node = (OrganizationNode) iterator.next();
			OrganizationNode temp = node;
			do {
				temp.getPosterities().put(node.getOrgCode(), node);
			} while ((temp = (OrganizationNode) temp.getParent()) != null);
		}
	}

	private void initPosterities() {
		Iterator iterator = (Iterator) iterator();
		for (; iterator.hasNext();) {
			OrganizationNode node = (OrganizationNode) iterator.next();
			node.posterities = new HashMap<Serializable, OrganizationNode>();
			node.posterities.put(orgCode, node);
		}
	}
	
	public OrganizationNode loadOrganization(String orgId) {
		return posterities.get(orgId);
	}
	
	public List<Node> findAll(CriteriaFilter criteriaFilter) {
		List<Node> retval = new ArrayList<Node>();
		List<Filter> chainFilter = criteriaFilter.getFilterChain();

		for (OrganizationNode organization : posterities.values()) {
			boolean flag = true;
			
			for (Filter filter : chainFilter) {
				if (!filter.isPermitted(organization)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				retval.add(organization);
			}
		}
		
		return retval;
	}

	public Node find(CriteriaFilter criteriaFilter) {

		Node retval = null;

		List chainFilter = criteriaFilter.getFilterChain();

		for (Iterator iter = this.iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();

			boolean flag = true;
			for (java.util.Iterator iterator = chainFilter.iterator(); iterator.hasNext();) {
				Filter filter = (Filter) iterator.next();
				if (!filter.isPermitted(node)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				retval = node;
			}
		}
		return retval;

	}

	
}
