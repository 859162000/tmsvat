
/**    
 * Copyright (C),Deloitte
 * @FileName: BaseOrg.java  
 * @Package: com.deloitte.tms.base.masterdata.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月17日 下午3:35:12  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.base.masterdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月17日 下午3:35:12 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity
@Table(name = BaseOrg.TABLE)
@ModelProperty(comment = "组织")
public class BaseOrg extends BaseEntity {

	public static final String TABLE = "BASE_ORG";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "ORG_ID",  length = 60)
	String id;
	
	@Column(name = "ORG_CODE",length=255,nullable=false)
	@ModelProperty(comment = "组织代码")
	String orgCode;
	
	@Column(name = "ORG_NAME",length=255)
	@ModelProperty(comment = "组织名称")
	String orgName;
	
	@Column(name = "ORG_TYPE",length=255)
	@ModelProperty(comment = "组织类型")
	String orgTye;
	
	@Column(name="PARENT_ID")
	@ModelProperty(comment = "父节点")
	String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgTye() {
		return orgTye;
	}

	public void setOrgTye(String orgTye) {
		this.orgTye = orgTye;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
