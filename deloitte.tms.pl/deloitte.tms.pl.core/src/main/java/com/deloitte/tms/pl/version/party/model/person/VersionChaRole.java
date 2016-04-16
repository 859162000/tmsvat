package com.deloitte.tms.pl.version.party.model.person;
//package com.ling2.version.party.model.person;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.ling2.core.commons.exception.BusinessException;
//import com.ling2.core.enums.ErrorCode;
//import com.ling2.version.party.model.ExtraRelationData;
//import com.ling2.version.party.model.Party;
//import com.ling2.version.party.model.organization.DepartmentNode;
//import com.ling2.version.party.model.organization.DivisionNode;
//import com.ling2.version.party.model.organization.OrganizationNode;
//import com.ling2.version.party.model.person.relation.Relation;
//import com.ling2.version.party.model.person.support.CompositeDepend;
//import com.ling2.version.party.model.person.support.Depend;
//import com.ling2.version.party.model.person.support.DependFactory;
//
//public class VersionChaRole extends ChaRole {
//
//	protected Log log = LogFactory.getLog(this.getClass());
//	
//	private Set<TargetDepend> targetDepends = new HashSet<TargetDepend>();
//	
//	/** Set<MpElement> **/
//	private Set<MpElement> elements = new HashSet<MpElement>();
//	
//	/** 考核结果 **/
//	private Set<ChkResult> results = new HashSet<ChkResult>();
//	
//	public Set<ChkResult> getResults() {
//		return results;
//	}public void addTargetDepend(CheckTarget target, Object value, String jobCode) {
//		TargetDepend targetDepend = new TargetDepend();
//		targetDepend.setValue(value);
//		targetDepend.setJobCode(jobCode);
//		
//		String code = target.getTargetCode();
//		String targetType = target.getTargetType();
//		String attendElementCode = target.getAttendElementCode();
//		
//		targetDepend.setCode(code);
//		targetDepend.setTargetType(targetType);
//		targetDepend.setAttendElementCode(attendElementCode);
//		this.targetDepends.add(targetDepend);
//	}
//
//	
//	public TargetDepend findTargetDepend(String code, String targetJobCode) {
//		for (TargetDepend targetDepend : this.targetDepends) {
//			if (code.equals(targetDepend.getCode()) && targetJobCode.equals(targetDepend.getJobCode())) {
//				return targetDepend;
//			}
//		}
//		return null;
//	}/**
//	 * 查找佣金项
//	 * 
//	 * @param eleCode
//	 * @return
//	 * @author dada
//	 */
//	public Collection<MpElement> findMpElements(String eleCode) {
//		Collection<MpElement> retval = new HashSet<MpElement>();
//		for (MpElement element : elements) {
//			if (element.getCode().equals(eleCode)) {
//				retval.add(element);
//			}
//		}
//		return retval;
//	}
//	
//	
//	public MpElement findMpElement(String eleCode) {
//		
//		for (MpElement element : elements) {
//			if (element.getCode().equals(eleCode)) {
//				return element;
//			}
//		}
//		return null;
//	}
//	
//	
//	public Set<MpElement> findMpElements() {
//		return elements;
//	}
//	
//	/**
//	 * 添加佣金项目
//	 * 
//	 * @param mpElement
//	 * @author dada
//	 * @throws BusinessException 
//	 */
//	public void addMpElement(MpElement mpElement) throws BusinessException {
//		this.elements.add(mpElement);
//		mpElement.setRole(this);
//		addDepend(mpElement);
//	}public Set<MpElement> getElements() {
//		return elements;
//	}
//	
//	public Set<CommissionAccount> genComAccount() {
//		Set<CommissionAccount> retval = new HashSet<CommissionAccount>();
//		Set<String> temp = new HashSet<String>();
//		
//		for (MpElement element : elements) {
//			String msType = element.getMsType();
//			String eleCode = element.getCode();
//			if (msType != null && !temp.contains(eleCode)) {
//				retval.addAll(element.genComAccount());
//				temp.add(eleCode);
//
//			}
//		}
//		return retval;
//	}public void initTargetDepends() {
//		targetDepends = new HashSet<TargetDepend>();
//	}
//	
//	/**
//	 * 添加考核结果
//	 * 
//	 * @param result
//	 * @author dada
//	 */
//	public void addResult(ChkResult result) {
//		this.results.add(result);
//	}
//	
//	public void removeResult(ChkResult result) {
//		this.results.remove(result);
//	}
//	
//	/**
//	 * 批量添加考核结果
//	 * 
//	 * @param results
//	 * @author dada
//	 */
//	public void addResults(Collection<ChkResult> results) {
//		this.results.addAll(results);
//	}	
//	public void initMpElements() {
//		this.elements = new HashSet();
//	}
//
//	public void addMpElement(MpElement element) {
//		this.elements.add(element);
//		element.setRole(this);
//	}
//
//	public void removeMpElement(MpElement element) {
//		this.elements.remove(element);
//	}
//
//	public void addMpElements(Collection c) {
//		for (Iterator iter = c.iterator(); iter.hasNext();) {
//			MpElement element = (MpElement) iter.next();
//			addMpElement(element);
//		}
//	}
//
//	public void addAccount(CommissionAccount account) {
//		this.accounts.add(account);
//		ChaRole channelRole = new ChaRole();
//		channelRole.setId(id);
//		account.setChannelRole(channelRole);
//	}
//
//	public void addAccounts(Collection c) {
//		for (Iterator iter = c.iterator(); iter.hasNext();) {
//			CommissionAccount account = (CommissionAccount) iter.next();
//			addAccount(account);
//		}
//	}
//
//	public void removeAccount(CommissionAccount account) {
//		this.accounts.remove(account);
//	}
//
//	public void removeAccounts(Collection c) {
//		this.accounts.removeAll(c);
//	}
//
//	/**
//	 * 建立节算佣金项
//	 * 
//	 * 发起者为从角色 根据佣金项的结构<ELEMNT>获取主角色
//	 * 
//	 * @param value
//	 *            佣金项的值
//	 * @param element
//	 *            佣金项的结构
//	 */
//	public void genMpElement(Number value, Element element) {
//		PermitFilter filter = PermitFactory.create().add(PermitMode.status(StatusDef.FORBID_NEW_POLICY));
//		genMpElement(value, element, filter);
//	}
//
//	public void genMpElement(Number value, Element element, PermitFilter filter) {
//		List relRoles = element.getAction().findMatchedRoles(this);
//		for (Iterator iter = relRoles.iterator(); iter.hasNext();) {
//			ChaRole relRole = (ChaRole) iter.next();
//			if (filter.isPermitted(relRole)) {
//				Long eleValue = new Long(Math.round(value.doubleValue()));
//				relRole.addMpElement(element, eleValue, this);
//			}
//		}
//	}
//
//	public void genMpElement(Number value, Element element, Set depends) {
//		PermitFilter filter = PermitFactory.create().add(PermitMode.status(StatusDef.FORBID_NEW_POLICY));
//		genMpElement(value, element, depends, filter);
//	}
//
//	public void genMpElement(Number value, Element element, Set depends, PermitFilter filter) {
//		String eleCode = element.getEleCode();
//		List relRoles = element.getAction().findMatchedRoles(this);
//		for (Iterator iter = relRoles.iterator(); iter.hasNext();) {
//			ChaRole relRole = (ChaRole) iter.next();
//			if (filter.isPermitted(relRole)) {
//				Long eleValue = new Long(Math.round(value.doubleValue()));
//				relRole.addMpElement(element, eleValue, this);
//				MpElement mpElement = relRole.findMpElement(eleCode);
//				Set relations = mpElement.getRelations();
//				for (Iterator iterator = relations.iterator(); iterator.hasNext();) {
//					MpRelation relation = (MpRelation) iterator.next();
//					if (relation.getChaRoleId().equals(id)) {
//						for (Iterator iterator1 = depends.iterator(); iterator1.hasNext();) {
//							Depend depend = (Depend) iterator1.next();
//							MpPart part = MpFactory.createPart(depend);
//							relation.addPart(part);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public void addMpElement(Element element, Long eleValue, ChaRole role) {
//		String eleCode = element.getEleCode();
//		MpElement mpElement = findMpElement(eleCode);
//		if (mpElement == null) {
//			mpElement = MpFactory.createElement(element, this, eleValue);
//			elements.add(mpElement);
//		} else {
//			mpElement.addEleValue(eleValue);
//		}
//		MpRelation relation = MpFactory.genRelation(element, role, eleValue);
//		mpElement.addRelation(relation);
//	}
//
//	public MpElement findMpElement(String eleCode) {
//		for (Iterator iter = elements.iterator(); iter.hasNext();) {
//			MpElement mpElement = (MpElement) iter.next();
//			if (mpElement.getEleCode().equals(eleCode)) {
//				return mpElement;
//			}
//		}
//		return null;
//	}
//
//	public Set findMpElements(String eleCode) {
//		Set retval = new HashSet();
//		for (Iterator iter = elements.iterator(); iter.hasNext();) {
//			MpElement mpElement = (MpElement) iter.next();
//			if (mpElement.getEleCode().equals(eleCode)) {
//				retval.add(mpElement);
//			}
//		}
//		return retval;
//	}
//
//	/**
//	 * 根据部门等级获取符合条件的上级部门(包括所属部门)
//	 * 
//	 * @param deptLevel
//	 * @return
//	 */
//	public DepartmentNode findDepartment(String deptLevel) {
//		DepartmentNode department = this.getDepartment();
//		Set departments = department.findSuperiorDeparments();
//		Set directDepartments = department.findDirectDepartments();
//		departments.add(department);
//		departments.addAll(directDepartments);
//		for (Iterator iter = departments.iterator(); iter.hasNext();) {
//			DepartmentNode node = (DepartmentNode) iter.next();
//			if (node.getDeptLevel().equals(deptLevel)) {
//				return node;
//			}
//		}
//		return null;
//	}
//
//	public String toString() {
//		return "姓名: " + name + " 工号: " + empNo;
//	}
//
//	/**
//	 * 是否为指定部门等级的主管
//	 * 
//	 * @param deptLevel
//	 * @return
//	 */
//	public boolean isDirector(String deptLevel) {
//		Set directDepartments = department.findDirectDepartments();
//		directDepartments.add(department);
//		for (Iterator iter = directDepartments.iterator(); iter.hasNext();) {
//			DepartmentNode department = (DepartmentNode) iter.next();
//			if (department.getDeptLevel().equals(deptLevel) && department.getDirector() == this) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isDirector() {
//		return department.getDirector().equals(this);
//	}
//
//	public Object clone() {
//		ChaRole role = new ChaRole();
//		role.setId(id);
//		role.setComDate(comDate);
//		role.setName(name);
//		role.setEmpNo(empNo);
//		role.setJoinDate(joinDate);
//		role.setDepartment(department);
//		return role;
//	}
//
//	public void addPayment(Payment payment) {
//		this.payments.add(payment);
//	}
//
//	public Payment findPayment(String payItem) {
//		for (Iterator iter = payments.iterator(); iter.hasNext();) {
//			Payment payment = (Payment) iter.next();
//			if (payment.getPayItem().equals(payItem)) {
//				return payment;
//			}
//		}
//		return null;
//	}
//
//	public Set getDirectDepartments() {
//		Set retval = new HashSet();
//		retval.add(this);
//		Set directDepartments = department.findDirectDepartments();
//		retval.addAll(directDepartments);
//		return retval;
//	}
//
//	public DepartmentNode getBottomDirectDepartment() {
//		Set departmens = getDirectDepartments();
//		for (Iterator iter = departmens.iterator(); iter.hasNext();) {
//			DepartmentNode department = (DepartmentNode) iter.next();
//			if (department.isLeaf()) {
//				return department;
//			}
//		}
//		return null;
//	}
//
//	public void genChkResult(CheckDetail detail) throws BusinessException {
//		Map paramMap = ExpressionHelper.genParamMap(this, detail);
//		ChkResult result = ChkFactory.createResult(this, detail, paramMap);
//		addChkResult(result);
//	}
//
//	public void passCheck(Check check) {
//		String chkCode = check.getChkCode();
//		ChkResult chkResult = findChkResult(chkCode);
//		if (chkResult != null) {
//			chkResult.setChkResult(CheckDef.SUCCESS);
//		}
//	}
//
//	public void addChkResult(ChkResult result) {
//		this.checks.add(result);
//		result.setRole(this);
//	}
//
//	public void addChkResults(Collection c) {
//		for (Iterator iter = c.iterator(); iter.hasNext();) {
//			ChkResult result = (ChkResult) iter.next();
//			addChkResult(result);
//		}
//	}
//
//	public ChkResult findChkResult(String chkCode) {
//		for (Iterator iter = checks.iterator(); iter.hasNext();) {
//			ChkResult chkResult = (ChkResult) iter.next();
//
//			if (chkCode.equals(chkResult.getChkCode())) {
//				return chkResult;
//			}
//		}
//		return null;
//	}
//
//	public boolean isPersistJob(String jobCode, int months) {
//		Set depends = findDependsIgnoreMonths(DependDef.JOB, months - 1);
//		if (depends.size() != months) {
//			return false;
//		}
//
//		for (Iterator iter = depends.iterator(); iter.hasNext();) {
//			Depend depend = (Depend) iter.next();
//			String job = (String) depend.getValue();
//			if (!job.equals(jobCode)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//}
