package com.deloitte.tms.pl.version.party.model.organization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.enums.ErrorCode;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;
import com.deloitte.tms.pl.version.party.model.organization.relation.DeptRelation;
import com.deloitte.tms.pl.version.party.model.person.ChaRole;


public class DepartmentNode extends OrganizationNode {
	
	/** 部门级别 **/
	private String deptLevel;
	
	/** 部门级类型**/
	private String deptType;
	
	/** 渠道类型 **/
	private String chaType;
	
	/** 部门主管 **/
	private ChaRole director;
	
	/** 部门成员 **/
	private Set<ChaRole> members = new HashSet<ChaRole>();
	
	/** 直辖标志 **/
	private boolean direct;
	
	/** 正向关系 List<DeptRelation> * */
	private Set<DeptRelation> relations = new HashSet<DeptRelation>();

	/** 逆向关系 List<DeptRelation> * */
	private Set<DeptRelation> revRelations = new HashSet<DeptRelation>();

	public DepartmentNode(String orgCode, String orgName) {
		super(orgCode, orgName);
	}
	
	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getChaType() {
		return chaType;
	}

	public void setChaType(String chaType) {
		this.chaType = chaType;
	}

	public ChaRole getDirector() {
		return director;
	}

	public void setDirector(ChaRole director) {
		this.director = director;
	}

	public Set<ChaRole> getMembers() {
		return members;
	}

	public void setMembers(Set<ChaRole> members) {
		this.members = members;
	}
	
	public void setDirect(boolean direct) {
		this.direct = direct;
	}
	
	public boolean isDirect() {
		return direct;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	/**
	 * 浅层克隆渠道部门
	 * 
	 * @author dada
	 */
	public Object clone() {
		DepartmentNode department = (DepartmentNode) super.clone();
		department.setChaType(chaType);
		department.setDeptLevel(deptLevel);
		department.setDirector(null);
		department.setDirect(direct);
		department.setDeptType(deptType);
		return department;
	}
	
	/**
	 * 在机构中寻找指定ID的渠道角色
	 * 
	 * @param chaRoleId
	 * @return
	 * @author dada
	 */
	public ChaRole findRole(Long roleId) {
		OrganizationNode root = (OrganizationNode) this.getRoot();
		Map<Long, ChaRole> corpora = root.getCorpora();
		ChaRole chaRole = (ChaRole) corpora.get(roleId);
		return chaRole;
	}
	
	/**
	 * 于全局对象中维持渠道人员引用
	 * 
	 * @param role
	 * @throws BusinessException
	 * @author dada
	 */
	protected void addCorpora(ChaRole role) throws BusinessException {
		Long roleId = (Long) role.getId();
		ChaRole temp = findRole(roleId);
		if (temp != null) {
			throw new BusinessException(ErrorCode.DATA, role + " 于关系表中存在多条记录！");
		}
		
		OrganizationNode organization = this;
		do {
			Long id = (Long) role.getId();
			organization.getCorpora().put(id, role);
		} while ((organization = (OrganizationNode) organization.getParent()) != null);
	} 

	/**
	 * 删除全局对象中渠道人员的引用
	 * 
	 * @param role
	 * @author dada
	 */
	protected void removeCorpora(ChaRole role) {
		Long roleId = (Long) role.getId();
		OrganizationNode organization = this;
		do {
			organization.getCorpora().remove(roleId);
		} while ((organization = (OrganizationNode) organization.getParent()) != null);
	}
	
	/**
	 * 于渠道机构中添加机构渠道人员，
	 * 同时在各级机构的全局人员中添加该渠道成员的引用。
	 * 
	 * @see #addCorpora(ChaRole)
	 * @param role
	 * @throws BusinessException
	 * @author dada
	 */
	public void addMember(ChaRole role) throws BusinessException {
		this.members.add(role);
		role.setDepartment(this);
		addCorpora(role);
	}
	
	/**
	 * 于渠道机构中批量添加机构渠道人员，
	 * 同时在各级机构的全局人员中添加这批渠道成员的引用。
	 * 
	 * @see #addMember(ChaRole)
	 * @param roles
	 * @throws BusinessException
	 * @author dada
	 */
	public void addMembers(Collection<ChaRole> roles) throws BusinessException {
		for (ChaRole role : roles) {
			addMember(role);
		}
	}
	
	/**
	 * 于渠道机构中注销机构渠道人员，
	 * 同时在各级机构的全局人员中注销该渠道人员的引用。
	 * 
	 * @see #removeCorpora(ChaRole)
	 * @param role
	 * @author dada
	 */
	public void removeMember(ChaRole role) {
		this.members.remove(role);
		removeCorpora(role);
	}
	
	/**
	 * 于渠道机构中批量注销机构渠道人员，
	 * 同时在各级机构的全局人员中注销这批渠道人员的引用。
	 * 
	 * @see #removeMember(ChaRole)
	 * @param roles
	 * @author dada
	 */
	public void removeMembers(Collection<ChaRole> roles) {
		for (ChaRole role : roles) {
			removeMember(role);
		}
	}
	
	/**
	 * 于渠道机构中添加主管人员，
	 * 同时在各级机构的全局人员中添加该主管的引用。
	 * 如果机构中已经存在该主管信息，
	 * 则判断其所属机构或本机构是否为直辖机构，
	 * 如果是则将高层机构设为主管管辖机构，低层机构保留对主管的引用。
	 * 如果均为非直辖机构，则数据库数据异常。
	 * 
	 * @see #addCorpora(ChaRole)
	 * @param role
	 * @throws BusinessException
	 */
	public void addDirector(ChaRole role) throws BusinessException {
		if (director != null) {
			throw new BusinessException(ErrorCode.DATA, this + " 已经存在主管！");
		}
		Long roleId = (Long) role.getId();
		ChaRole temp = findRole(roleId);
		
		if(temp == null){
			this.director = role;
			role.setDepartment(this);
			addCorpora(role);
		} else {
			DepartmentNode department = temp.getDepartment();
			if(direct || department.isDirect()){
				
				if (isNodeAncestor(department)) {
					temp.setDepartment(department);
				} else {
					temp.setDepartment(this);
				}
				
				this.director = temp;
				addCorpora(director);
			} else {				
				throw new BusinessException(ErrorCode.DATA, role + " 是其他部门的主管或成员！");
			}
			
		}
	}
	
	/**
	 * 于渠道机构中移除主管，如果该主管同时管辖多个部门，
	 * 则需要在全局对象中保持其引用。
	 * 
	 * @see #removeCorpora(ChaRole)
	 * @author dada
	 */
	public void removeDirector() {
		removeCorpora(director);
		this.director = null;
	}
	
	/**
	 * 返回本渠道机构最上层的渠道机构
	 * 
	 * @see #isSupreme()
	 * @return
	 * @author dada
	 */
	public DepartmentNode supremeDepartment() {
		DepartmentNode department = this;
		while (!department.isSupreme()) {
			department = (DepartmentNode) department.getParent();
		}
		return department;
	}
	
	/**
	 * 判断是否最顶层的渠道机构。
	 * 
	 * 判断标准：
	 * 1. 无上级机构或上级机构为行政机构
	 * 
	 * @return
	 * @author dada
	 */
	public boolean isSupreme() {
		boolean retval = false;
		if (getParent() == null || getParent() instanceof DivisionNode) {
			retval = true;
		}
		return retval;
	}
	
	/**
	 * 查找本渠道机构的上层渠道机构(含本机构)
	 * 
	 * @see #isSupreme()
	 * @return
	 * @author dada
	 */
	public Set<DepartmentNode> findSuperiorDeparments() {
		Set<DepartmentNode> retval = new HashSet<DepartmentNode>();
		DepartmentNode department = this;
		while (!department.isSupreme()) {
			department = (DepartmentNode) department.getParent();
			retval.add(department);
		}
		return retval;
	}
	
	/**
	 * 获取当前部门辖属的直辖部门
	 * 
	 * 判断标准：
	 * 1. 渠道机构拥有相同的主管
	 * 2. 渠道机构是直辖机构
	 * 
	 * @return
	 * @author dada
	 */
	public DepartmentNode findDirectDepartment() {
		for (Iterator<Node> iter = getChildren().iterator(); iter.hasNext();) {
			DepartmentNode child = (DepartmentNode) iter.next();
			if (child.isDirect() && child.getDirector() == director) {
				return child;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取当前部门的所有直辖部门(包含当前部门)
	 * 
	 * @see #findDirectDepartment()
	 * @return
	 * @author dada
	 */
	public Set<DepartmentNode> findDirectDepartments() {
		Set<DepartmentNode> retval = new HashSet<DepartmentNode>();
		DepartmentNode directDepartment = findDirectDepartment();
		while (directDepartment != null) {
			retval.add(directDepartment);
			directDepartment = directDepartment.findDirectDepartment();
		}
		return retval;
	}
	
	/**
	 * 指定部门是否当前部门的直辖部门
	 * 
	 * 判断标准：
	 * 1. 指定部门是当前部门的下级部门
	 * 2. 指定部门和当前部门拥有相同的主管
	 * 3. 指定部门是直辖机构
	 * 
	 * @param department
	 * @return
	 * @author dada
	 */
	public boolean isDirect(DepartmentNode department) {
		boolean retval = false;
		
		if(department == this) {
			return false;
		}
		Set<DepartmentNode> directDepartments = findDirectDepartments();
		for (DepartmentNode directDepartment : directDepartments) {
			if (department == directDepartment || department.getParent() == directDepartment) {
				retval = true;
			}
		}

		return retval;
	}
	
	/**
	 * 最接近的一层行政机构
	 * 
	 * @return
	 * @author dada
	 */
	public DivisionNode recentDivision() {
		OrganizationNode organization = this;
		while ((organization = (OrganizationNode) organization.getParent()) != null) {
			if (organization instanceof DivisionNode) {
				return (DivisionNode) organization;
			}
		}
		return null;
	}
	
	/**
	 * 根据关系类型获取正向关系
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Collection<DeptRelation> findRelations(String relCode) {
		Collection<DeptRelation> retval = new ArrayList<DeptRelation>();

		for (DeptRelation relation : relations) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				retval.add(relation);
			}
		}
		return retval;
	}
	
	/**
	 * 根据关系类型获取逆向关系
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public DeptRelation findRevRelation(String relCode) {
		for (DeptRelation revRelation : revRelations) {
			if (revRelation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				return revRelation;
			}
		}
		return null;
	}
	/**
	 * 移除一个正向关系
	 * @param relCode
	 * @param departmentNode 要移除的下级
	 */
	public void removeRelation(String relCode,DepartmentNode departmentNode)
	{
		Object temp=null;
		for (DeptRelation relation : relations) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				if(relation.getTgtDepartment().getOrgCode().equals(departmentNode.getOrgCode()))
				{
					temp=relation;					
				}
			}
		}
		if(temp!=null)
		{
			relations.remove(temp);
		}
		departmentNode.removeRevRelation_p(relCode);
	}
	/**
	 * 移除一个逆向关系
	 * @param relCode
	 */
	private void removeRevRelation_p(String relCode)
	{
		Object temp=null;
		for (DeptRelation revRelation : revRelations) {
			if (revRelation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				temp=revRelation;
			}
		}
		if(temp!=null)
		{
			revRelations.remove(temp);
		}
	}
	/**
	 * 移除一个逆向关系
	 * @param relCode
	 * @param departmentNode 要移除的下级
	 */
	public void removeRevRelation(String relCode)
	{
		Object temp=null;
		DepartmentNode departmentNode=findRevRelationDepartment(relCode);
		for (DeptRelation revRelation : revRelations) {
			if (revRelation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				temp=revRelation;
			}
		}
		if(temp!=null)
		{
			revRelations.remove(temp);
		}
		departmentNode.removeRelation_p(relCode,this);
	}
	/**
	 * 移除一个正向关系
	 * @param relCode
	 */
	private void removeRelation_p(String relCode,DepartmentNode departmentNode)
	{
		Object temp=null;
		for (DeptRelation relation : relations) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				if(relation.getTgtDepartment().getOrgCode().equals(departmentNode.getOrgCode()))
				{
					temp=relation;
				}
			}
		}
		if(temp!=null)
		{
			relations.remove(temp);
		}
	}
	/**
	 * 根据关系类型获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public Collection<DepartmentNode> findRelationDepartments(String relCode) {
		Collection<DepartmentNode> roles = new ArrayList<DepartmentNode>();
		Collection<DeptRelation> relations = findRelations(relCode);
		
		for (DeptRelation relation : relations) {
			roles.add(relation.getTgtDepartment());
		}
		return roles;
	}
	/**
	 * 根据关系类型递归获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public Collection<DepartmentNode> findRecursiveRelationDepartments(String relCode) {
		Collection<DepartmentNode> roles = new ArrayList<DepartmentNode>();
		return findRecursiveRelationDepartments(this,roles,relCode);
	}
	/**
	 * 根据关系类型递归获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	private Collection<DepartmentNode> findRecursiveRelationDepartments(DepartmentNode departmentNode,Collection<DepartmentNode> roles,String relCode) {
		Collection<DeptRelation> relations = departmentNode.findRelations(relCode);		
		for (DeptRelation relation : relations) {
			roles.add(relation.getTgtDepartment());
			findRecursiveRelationDepartments(relation.getTgtDepartment(),roles,relCode);
		}
		return roles;
	}
	/**
	 * 根据关系类型获取指定关系的逆向角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public DepartmentNode findRevRelationDepartment(String relCode) {
		DeptRelation revRelation = findRevRelation(relCode);
		if (revRelation != null) {
			return revRelation.getTgtDepartment();
		}
		return null;
	}
	
	public void addRelation(DeptRelation relation) throws BusinessException {
		DepartmentNode tgtDepartmentNode = relation.getTgtDepartment();
		if (tgtDepartmentNode != null) {
			relations.add(relation);

			DeptRelation revRelation = reverseRelation(relation);
			String relCode = revRelation.getRelCode();
			if (tgtDepartmentNode.findRevRelation(relCode) == null) {
				tgtDepartmentNode.revRelations.add(revRelation);
			} else {				
				throw new BusinessException(ErrorCode.DATA, this + " 和 "
						+ tgtDepartmentNode + " 存在多条同类型" + relation.getRelCode());
			}
		}
	}
	
	public void addReverseRelation(DeptRelation relation) throws BusinessException {
		String relCode = relation.getRelCode();
		if (findRevRelation(relCode) == null) {
			this.revRelations.add(relation);
		} else {
			throw new BusinessException(ErrorCode.DATA, this + " 关系数据 " + relCode + " 存在多条同类型");
		}
	}
	/**
	 * 获取反转关系
	 * 
	 * @param relation
	 * @return
	 */
	protected DeptRelation reverseRelation(DeptRelation relation) {
		DeptRelation revRelation = new DeptRelation();
		String relCode = relation.getRelCode();

		revRelation.setSrcDepartment(relation.getTgtDepartment());
		revRelation.setTgtDepartment(relation.getSrcDepartment());
		revRelation.setRelCode(relCode);
		return revRelation;
	}
}
