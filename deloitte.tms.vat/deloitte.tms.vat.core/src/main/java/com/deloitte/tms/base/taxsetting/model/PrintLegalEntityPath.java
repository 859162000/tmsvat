package com.deloitte.tms.base.taxsetting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
@Entity
@Table(name = PrintLegalEntityPath.TABLE)
public class PrintLegalEntityPath extends BaseEntity{
	
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"Legal_Entity_Path";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy=GenerationType.TABLE,generator=SEQ+"_GENERATOR")
	@Column(name = "Org_Path_ID",  length = 36)
	String id;
	
	@Column(name = "org_id",length=36)
	String orgid;
	@Column(name = "ORG_NAME",length=150)
    String orgname;
	@Column(name = "org_code",length=150)
    String orgcode;
    /**
     * 机构类别,登记
     */
	@Column(name = "org_level",length=36)
    String orglevel;
    @Column(name = "org_seq",length=150)
    String orgseq;
    @Column(name = "parent_id",length=36)
    String parentCode;
    /**
     * 显示排序
     */
    @Column(name = "sort_Order",length=36)
    Integer sortOrder;
    /**
     * 机构数字上的等级
     */
    @Column(name = "org_level_sort",length=36)
    Integer orglevelsort;
    @Column(name = "chatype",length=36)
    String chatype;
    @Column(name = "comdate")
    Date comdate;
    @Column(name = "insertdate")
    Date insertdate;
    @Column(name = "org_id1",length=36)
    String orgid1;
    @Column(name = "org_name1",length=150)
    String orgname1;
    @Column(name = "org_code1",length=150)
    String orgcode1;
    @Column(name = "org_id2",length=36)
    String orgid2;
    @Column(name = "org_name2",length=150)
    String orgname2;
    @Column(name = "org_code2",length=150)
    String orgcode2;
    @Column(name = "org_id3",length=36)
    String orgid3;
    @Column(name = "org_name3",length=150)
    String orgname3;
    @Column(name = "org_code3",length=150)
    String orgcode3;
    @Column(name = "org_id4",length=36)
    String orgid4;
    @Column(name = "org_name4",length=150)
    String orgname4;
    @Column(name = "org_code4",length=150)
    String orgcode4;
    @Column(name = "org_id5",length=36)
    String orgid5;
    @Column(name = "org_name5",length=150)
    String orgname5;
    @Column(name = "org_code5",length=150)
    String orgcode5;
    @Column(name = "dept_id1",length=36)
    String deptid1;
    @Column(name = "dept_code1",length=36)
    String deptcode1;
    @Column(name = "dept_name1",length=150)
    String deptname1;
    @Column(name = "dept_id2",length=36)
    String deptid2;
    @Column(name = "dept_code2",length=150)
    String deptcode2;
    @Column(name = "dept_name2",length=150)
    String deptname2;
//    @Column(name = "latitude",length=10)
//    String latitude;
//    @Column(name = "longitude",length=10)
//    String longitude;
//    @Column(name = "shortName",length=50)
//    String shortName;
//    @Column(name = "zoom",length=4)
//    String zoom;
    
    @Column(name = "areatype",length=36)
	String areatype;
    
//	public String getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(String latitude) {
//		this.latitude = latitude;
//	}
//
//	public String getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(String longitude) {
//		this.longitude = longitude;
//	}
//
//	public String getShortName() {
//		return shortName;
//	}
//
//	public void setShortName(String shortName) {
//		this.shortName = shortName;
//	}
//
//	public String getZoom() {
//		return zoom;
//	}
//
//	public void setZoom(String zoom) {
//		this.zoom = zoom;
//	}
//	/**
//	 * 在指定日期中判断关系是否有效
//	 * 
//	 * @param date
//	 * @return
//	 * @author dada
//	 */
//	public boolean isEffect(Date date) {
//		java.util.Date dt2 = new java.sql.Timestamp(date.getTime());
//		boolean retval = false;
//		if (effectDate != null
//				&& effectDate.compareTo(dt2) <= 0
//				&& (quitDate == null || quitDate.compareTo(dt2) > 0)) {
//			retval = true;
//		}
//		return retval;
//	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

//	/**
//	 * 判断关系现在是否有效
//	 * 
//	 * @see #isEffect(Date)
//	 * @return
//	 * @author dada
//	 */
//	public boolean isEffect() {
//		Date date = new Date();
//		return isEffect(date);
//	}
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
	}

	public String getOrgseq() {
		return orgseq;
	}

	public void setOrgseq(String orgseq) {
		this.orgseq = orgseq;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getOrglevelsort() {
		return orglevelsort;
	}

	public void setOrglevelsort(Integer orglevelsort) {
		this.orglevelsort = orglevelsort;
	}

	public String getChatype() {
		return chatype;
	}

	public void setChatype(String chatype) {
		this.chatype = chatype;
	}

	public Date getComdate() {
		return comdate;
	}

	public void setComdate(Date comdate) {
		this.comdate = comdate;
	}

	public Date getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}

	public String getOrgid1() {
		return orgid1;
	}

	public void setOrgid1(String orgid1) {
		this.orgid1 = orgid1;
	}

	public String getOrgname1() {
		return orgname1;
	}

	public void setOrgname1(String orgname1) {
		this.orgname1 = orgname1;
	}

	public String getOrgcode1() {
		return orgcode1;
	}

	public void setOrgcode1(String orgcode1) {
		this.orgcode1 = orgcode1;
	}

	public String getOrgid2() {
		return orgid2;
	}

	public void setOrgid2(String orgid2) {
		this.orgid2 = orgid2;
	}

	public String getOrgname2() {
		return orgname2;
	}

	public void setOrgname2(String orgname2) {
		this.orgname2 = orgname2;
	}

	public String getOrgcode2() {
		return orgcode2;
	}

	public void setOrgcode2(String orgcode2) {
		this.orgcode2 = orgcode2;
	}

	public String getOrgid3() {
		return orgid3;
	}

	public void setOrgid3(String orgid3) {
		this.orgid3 = orgid3;
	}

	public String getOrgname3() {
		return orgname3;
	}

	public void setOrgname3(String orgname3) {
		this.orgname3 = orgname3;
	}

	public String getOrgcode3() {
		return orgcode3;
	}

	public void setOrgcode3(String orgcode3) {
		this.orgcode3 = orgcode3;
	}

	public String getOrgid4() {
		return orgid4;
	}

	public void setOrgid4(String orgid4) {
		this.orgid4 = orgid4;
	}

	public String getOrgname4() {
		return orgname4;
	}

	public void setOrgname4(String orgname4) {
		this.orgname4 = orgname4;
	}

	public String getOrgcode4() {
		return orgcode4;
	}

	public void setOrgcode4(String orgcode4) {
		this.orgcode4 = orgcode4;
	}

	public String getOrgid5() {
		return orgid5;
	}

	public void setOrgid5(String orgid5) {
		this.orgid5 = orgid5;
	}

	public String getOrgname5() {
		return orgname5;
	}

	public void setOrgname5(String orgname5) {
		this.orgname5 = orgname5;
	}

	public String getOrgcode5() {
		return orgcode5;
	}

	public void setOrgcode5(String orgcode5) {
		this.orgcode5 = orgcode5;
	}

	public String getDeptid1() {
		return deptid1;
	}

	public void setDeptid1(String deptid1) {
		this.deptid1 = deptid1;
	}

	public String getDeptcode1() {
		return deptcode1;
	}

	public void setDeptcode1(String deptcode1) {
		this.deptcode1 = deptcode1;
	}

	public String getDeptname1() {
		return deptname1;
	}

	public void setDeptname1(String deptname1) {
		this.deptname1 = deptname1;
	}

	public String getDeptid2() {
		return deptid2;
	}

	public void setDeptid2(String deptid2) {
		this.deptid2 = deptid2;
	}

	public String getDeptcode2() {
		return deptcode2;
	}

	public void setDeptcode2(String deptcode2) {
		this.deptcode2 = deptcode2;
	}

	public String getDeptname2() {
		return deptname2;
	}

	public void setDeptname2(String deptname2) {
		this.deptname2 = deptname2;
	}

//	public Date getEffectDate() {
//		return effectDate;
//	}
//
//	public void setEffectDate(Date effectDate) {
//		this.effectDate = effectDate;
//	}
//
//	public Date getQuitDate() {
//		return quitDate;
//	}
//
//	public void setQuitDate(Date quitDate) {
//		this.quitDate = quitDate;
//	}
//
//	public Date getEstDate() {
//		return estDate;
//	}
//
//	public void setEstDate(Date estDate) {
//		this.estDate = estDate;
//	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
