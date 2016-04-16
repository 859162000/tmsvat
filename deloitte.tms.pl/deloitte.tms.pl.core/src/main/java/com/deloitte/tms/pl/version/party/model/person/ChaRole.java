package com.deloitte.tms.pl.version.party.model.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.enums.ErrorCode;
import com.deloitte.tms.pl.version.party.model.ExtraRelationData;
import com.deloitte.tms.pl.version.party.model.Party;
import com.deloitte.tms.pl.version.party.model.organization.DepartmentNode;
import com.deloitte.tms.pl.version.party.model.organization.DivisionNode;
import com.deloitte.tms.pl.version.party.model.organization.OrganizationNode;
import com.deloitte.tms.pl.version.party.model.person.relation.Relation;
import com.deloitte.tms.pl.version.party.model.person.support.CompositeDepend;
import com.deloitte.tms.pl.version.party.model.person.support.Depend;
import com.deloitte.tms.pl.version.party.model.person.support.DependFactory;

public class ChaRole implements Party {

	protected Log log = LogFactory.getLog(this.getClass());

	private Long id;

	private String name;

	private String empNo;

	private String jobCode;

	private String status;

	private Date comDate;

	private Date joinDate;
	
	private String chaType;
	
	
	/** 人员申请类型s*/
	private String applyType;

	private DepartmentNode department;

	/** 同业加盟 */
	private Boolean join;
	
	/** 正向关系 List<Relation> * */
	private Set<Relation> relations = new HashSet<Relation>();

	/** 逆向关系 List<Relation> * */
	private Set<Relation> revRelations = new HashSet<Relation>();

	/** Set<Depend> **/
	private Set<Depend> depends = new HashSet<Depend>();
	
	/** 组合企业片段 **/
	private Set<CompositeDepend> compositeDepends = new HashSet<CompositeDepend>();
	
	private Object extraData;
	
	/** 上班部门 */
	private DepartmentNode workDepartment;
	
	/** 上班单位 */
	private DivisionNode workDivision;
	
	private Long recommenderId;
	
	private boolean virtual;
	
	protected List<ExtraRelationData> extraRelationData=new ArrayList<ExtraRelationData>();
	/**
	 * 获取附加类型数据
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Collection<ExtraRelationData> findExtraRelationDatas(String relCode) {
		Collection<ExtraRelationData> retval = new ArrayList<ExtraRelationData>();
		for (ExtraRelationData relation : extraRelationData) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				retval.add(relation);
			}
		}
		return retval;
	}
	/**
	 * 获取一条附加数据
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Object findExtraRelationData(String relCode) {
		Collection<ExtraRelationData> extraRelationDatas= findExtraRelationDatas(relCode);
		if(extraRelationDatas.size()==1)
		{
			return extraRelationDatas.iterator().next().getExtraData();
		}else {
			if(extraRelationDatas.size()>1)
			{
				throw new BusinessException(ErrorCode.DATA, this +" 存在多条同类型" + relCode);
			}else {
				return null;
			}
		}
	}
	public void addExtraRelationData(ExtraRelationData relation) throws BusinessException {
		String relCode=relation.getRelCode();
		Object object=findExtraRelationData(relCode);
		if(object==null)
		{
			extraRelationData.add(relation);
		}
		else {				
			throw new BusinessException(ErrorCode.DATA, this +" 存在多条同类型" + relation.getRelCode());
		}
	}

	public Long getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(Long recommenderId) {
		this.recommenderId = recommenderId;
	}

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public DepartmentNode getWorkDepartment() {
		return workDepartment;
	}

	public void setWorkDepartment(DepartmentNode workDepartment) {
		this.workDepartment = workDepartment;
	}

	public DivisionNode getWorkDivision() {
		return workDivision;
	}

	public void setWorkDivision(DivisionNode workDivision) {
		this.workDivision = workDivision;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	public Boolean getJoin() {
		return join;
	}

	public void setJoin(Boolean join) {
		this.join = join;
	}

	public String getChaType() {
		return chaType;
	}

	public void setChaType(String chaType) {
		this.chaType = chaType;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = (Long) id;
	}

	public DepartmentNode getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentNode department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ChaRole getDirector() {
		ChaRole director = null;
		if (department != null) {
			director = department.getDirector();
		}
		return director;
	}

	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

	public Set<Relation> getRevRelations() {
		return revRelations;
	}

	public void setRevRelations(Set<Relation> revRelations) {
		this.revRelations = revRelations;
	}
	
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}


	@Override
	public String toString() {
		return "姓名：" + name + " 工号：" + empNo+" id:"+id;
	}

	/**
	 * 获取所有本月的数据依赖
	 * 
	 * @see #findDepends(int)
	 * @return
	 * @author dada
	 */
	public Set<Depend> findDepends() {
		int month = 0;
		return findDepends(month);
	}

	/**
	 * 通过月份查找指定月份的数据依赖
	 * 
	 * @param month
	 * @return
	 * @author dada
	 */
	public Set<Depend> findDepends(int month) {
		Set<Depend> retval = new HashSet<Depend>();
		for (Depend depend : depends) {
			if (depend.getMonth() == month) {
				retval.add(depend);
			}
		}
		return retval;
	}
	
	/**
	 * 通过依赖类型获取本月的数据依赖
	 * 
	 * @param type
	 * @return
	 * @author dada
	 */
	public Depend findDepend(String type) {
		int month = 0;
		return findDepend(type, month);
	}
	
	public Object getDependValue(String type) {
		int month = 0;
		Depend depend = findDepend(type, month);
		
		if (depend == null) {
			return null;
		}
		return depend.getValue();
	}
	
	/**
	 * 通过依赖类型获取指定月、指定类型的数据依赖
	 * 
	 * @param type
	 * @param month
	 * @return
	 * @author dada
	 */
	public Depend findDepend(String type, int month) {
		for (Depend depend : depends) {
			if (depend.getCode().equals(type) && depend.getMonth() == month) {
				return depend;
			}
		}
		return null;
	}
	
	/**
	 * 通过依赖类型忽略月份获取相应的依赖数据
	 * 
	 * @param type
	 * @return
	 * @author dada
	 */
	public Set<Depend> findDependsIgnoreMonth(String type) {
		Set<Depend> retval = new HashSet<Depend>();
		for (Depend depend : retval) {
			if (depend.getCode().equals(type)) {
				retval.add(depend);
			}
		}
		return retval;
	}

	/**
	 * 根据指定的依赖类型获取指定月区间的依赖数据
	 * 
	 * @param type
	 * @param month
	 * @return Set<Depend>
	 * @author dada
	 */
	public Set<Depend> findDependsMonthBetween(String type, int month) {
		Set<Depend> retval = new HashSet<Depend>();
		for (Depend depend : depends) {
			if (depend.getCode().equals(type) && depend.getMonth() <= month) {
				retval.add(depend);
			}
		}
		return retval;
	}

	/**
	 * 通过Relation中的伪劣tgtRoleId获取对应的渠道人员，
	 * 于全局渠道人员中获取该人员信息，同时在该人员中添加反向的关系。
	 * 
	 * @see #reverseRelation(Relation)
	 * @param relation
	 * @throws BusinessException
	 * @author dada
	 */
	public void addRelation(Relation relation) throws BusinessException {
		Long roleId = (Long) relation.getTgtRole().getId();
		
		ChaRole tgtRole = department.findRole(roleId);
		if (tgtRole != null) {
			relations.add(relation);

			Relation revRelation = reverseRelation(relation);
			String relCode = revRelation.getRelCode();
			if (tgtRole.findRevRelation(relCode) == null) {
				tgtRole.revRelations.add(revRelation);
			} else {
//				if (!RelationConstant.RECOMMENDACTION_RELATION.equals(relCode)) {
//					throw new BusinessException(ErrorCode.DATA, this + " 和 "
//							+ tgtRole + " 存在多条同类型" + relation.getComment());
//				}
				
				throw new BusinessException(ErrorCode.DATA, this + " 和 "
						+ tgtRole + " 存在多条同类型" + relation.getRelCode());
			}
		}
	}
	
	/**
	 * 获取反转关系
	 * 
	 * @param relation
	 * @return
	 */
	protected Relation reverseRelation(Relation relation) {
		Relation revRelation = new Relation();
		String relCode = relation.getRelCode();
		if(relCode==null)
		{
			throw new BusinessException("关系类型不能为空");
		}
		int months = relation.getMonths();

		revRelation.setSrcRole(relation.getTgtRole());
		revRelation.setTgtRole(relation.getSrcRole());
		revRelation.setMonths(months);
		revRelation.setRelCode(relCode);
		revRelation.setStatu(relation.getStatu());
		revRelation.setBeginDate(relation.getBeginDate());
		revRelation.setEndDate(relation.getEndDate());
		return revRelation;
	}
	
	/**
	 * 根据关系类型获取正向关系
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Collection<Relation> findRelations(String relCode) {
		Collection<Relation> retval = new ArrayList<Relation>();

		for (Relation relation : relations) {
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
	public Relation findRevRelation(String relCode) {
		for (Relation revRelation : revRelations) {
			String relcodet=revRelation.getRelCode();
			if (relcodet.trim().equalsIgnoreCase(relCode)) {
				return revRelation;
			}
		}
		return null;
	}
	
	/**
	 * 根据关系类型获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public Collection<ChaRole> findRelationRoles(String relCode) {
		Collection<ChaRole> roles = new ArrayList<ChaRole>();
		Collection<Relation> relations = findRelations(relCode);
		
		for (Relation relation : relations) {
			roles.add(relation.getTgtRole());
		}
		return roles;
	}
	/**
	 * 根据关系类型获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public Collection<ChaRole> findRecursiveRelationRoles(String relCode) {
		Collection<ChaRole> roles = new ArrayList<ChaRole>();
		return findRecursiveRelationRoles(this,roles,relCode);
	}
	/**
	 * 根据关系类型获取指定关系的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	private Collection<ChaRole> findRecursiveRelationRoles(ChaRole chaRole,Collection<ChaRole> roles,String relCode) {
		Collection<Relation> relations = chaRole.findRelations(relCode);
		
		for (Relation relation : relations) {
			ChaRole chaRole2=relation.getTgtRole();
			roles.add(chaRole2);
			findRecursiveRelationRoles(chaRole2,roles,relCode);
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
	public ChaRole findRevRelationRole(String relCode) {
		Relation revRelation = findRevRelation(relCode);
		if (revRelation != null) {
			return revRelation.getTgtRole();
		}
		return null;
	}
	/** 获取所有祖先节点 */
	public List<ChaRole> findRevRelationAncestors(String relCode) {
		ChaRole current = this;
		List<ChaRole> retval = new ArrayList<ChaRole>();
		do {
			
			current = current.findRevRelationRole(relCode);
			if (current != null) {
				retval.add(current);
			}
		} while (current != null);
		return retval;
	}
	/**
	 * 移除一个正向关系 ,自动移除逆向关系
	 * @param relCode
	 */
	public void removeRelation(String relCode,ChaRole chaRole)
	{
		Object temp=null;
		for (Relation relation : relations) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				if(relation.getTgtRole().getId().equals(chaRole.getId()))
				{
					temp=relation;
				}
			}
		}
		if(temp!=null)
		{
			relations.remove(temp);
		}
		chaRole.removeRevRelation_p(relCode);
	}
	/**
	 * 移除一个逆向关系
	 * @param relCode
	 */
	private void removeRevRelation_p(String relCode)
	{
		Object temp=null;
		for (Relation revRelation : revRelations) {
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
	 * 移除一个逆向关系 ,自动移除正向关系
	 * @param relCode
	 */
	public void removeRevRelation(String relCode)
	{
		Object temp=null;
		ChaRole chaRole=findRevRelationRole(relCode);
		for (Relation revRelation : revRelations) {
			String relcodet=revRelation.getRelCode();
			if (relcodet.trim().equalsIgnoreCase(relCode)) {
				temp=revRelation;
			}
		}
		if(temp!=null)
		{
			revRelations.remove(temp);
		}
		chaRole.removeRelation_p(relCode,this);
	}
	/**
	 * 移除一个正向关系
	 * @param relCode
	 */
	private void removeRelation_p(String relCode,ChaRole chaRole)
	{
		Object temp=null;
		for (Relation relation : relations) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				if(relation.getTgtRole().getId().equals(chaRole.getId()))
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
	 * 以角色所属最高层的渠道机构为起点，获取其辖属所有符合部门等级的渠道机构。
	 * 
	 * @param deptLevel
	 * @return
	 * @author dada
	 */
	public Collection<DepartmentNode> findDepartments(String deptLevel) {
		Collection<DepartmentNode> departments = new ArrayList<DepartmentNode>();
		
		Map<Serializable, OrganizationNode> posterities = this.department.getPosterities();
		
		for (OrganizationNode organization : posterities.values()) {
			if (organization instanceof DepartmentNode) {
				DepartmentNode department = (DepartmentNode) organization;
				if (department.getDeptLevel().equals(deptLevel)) {
					departments.add(department);
				}
			}
		}
		return departments;
	}
	
	/**
	 * 根据指定的部门等级获取所有的角色
	 * 
	 * @param relCode
	 * @return
	 * @author dada
	 */
	public Collection<ChaRole> findDeptartmentRoles(String relCode) {
		Collection<ChaRole> roles = new HashSet<ChaRole>();
		Collection<DepartmentNode> departments = findDepartments(relCode);
		
		for (DepartmentNode department : departments) {
			roles.addAll(department.getCorpora().values());
		}
		return roles;
	}
	
	public void addDepend(String type, Object value) throws BusinessException {
		Depend depend = DependFactory.create(type, value);
		addDepend(depend);
	}
	
	public void addDepend(String type, Object value, int month) throws BusinessException {
		Depend depend = DependFactory.create(type, value, month);
		addDepend(depend);
	}
	
	public void addDepend(String type, Object value, String status) throws BusinessException {
		int month = 0;
		Depend depend = DependFactory.create(type, value, status, month);
		addDepend(depend);
	}
	
	public void addDepend(Depend depend) throws BusinessException {
		if (!depends.contains(depend)) {
			this.depends.add(depend);
			return;
		}
		throw new BusinessException(ErrorCode.DATA, this + " 存在多条同类型依赖 依赖类型：" + depend.getCode());
	}

	

	public Set<CompositeDepend> getCompositeDepends() {
		return compositeDepends;
	}
	
	/**
	 * 查找所有组合的企业对象
	 * 
	 * @param code
	 * @return
	 * @author dada
	 */
	public Collection<CompositeDepend> findCompositeDepends(String code) {
		Collection<CompositeDepend> depends = new HashSet<CompositeDepend>();
		for (CompositeDepend depend : compositeDepends) {
			if (depend.getCode().equals(code)) {
				depends.add(depend);
			}
		}
		return depends;
	}
	
	public CompositeDepend findCompositeDepend(String code) {
		for (CompositeDepend depend : compositeDepends) {
			if (depend.getCode().equals(code)) {
				return depend;
			}
		}
		return null;
	}
	
	public void initCompositeDepends() {
		this.compositeDepends = new HashSet<CompositeDepend>();
	}

	public void addCompositeDepend(CompositeDepend depend) {
		this.compositeDepends.add(depend);
	}
	
	/**
	 * 创建瞬态依赖
	 * 
	 * @param type
	 * @param value
	 * @throws BusinessException
	 * @author dada
	 */
	public void addTransientDepend(String type, Object value) throws BusinessException {
		Depend depend = DependFactory.create(type, value, Depend.TRANSIENT);
		addDepend(depend);
	}

	/**
	 * 创建瞬态依赖
	 * 
	 * @param type
	 * @param value
	 * @param month
	 * @throws BusinessException
	 * @author dada
	 */
	public void addTransientDepend(String type, Object value, int month) throws BusinessException {
		Depend depend = DependFactory.create(type, value, Depend.TRANSIENT, month);
		addDepend(depend);
	}
	
	/**
	 * 清除瞬态依赖
	 * 
	 * @author dada
	 */
	public void initTransientDepends() {
		Collection<Depend> retval = new HashSet<Depend>();
		
		for (Depend depend : depends) {
			if (Depend.TRANSIENT.equals(depend.getStatus())) {
				retval.add(depend);
			}
		}
		
		depends.removeAll(retval);
	}
}
