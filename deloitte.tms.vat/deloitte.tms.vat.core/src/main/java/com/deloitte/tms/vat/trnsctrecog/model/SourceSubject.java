
/**    
 * Copyright (C),Deloitte
 * @FileName: TradeAffirm.java  
 * @Package: com.deloitte.tms.vat.trade.pm.model  
 * @Description: //模块目的、功能描述  
 * @Author weijia  
 * @Date 2016年3月15日 下午4:42:07  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**  
 * 来源数据结构定义model
 * 功能详细描述
 * @author weijia
 * @create 2016年3月15日 下午4:42:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Entity
@Table(name = SourceSubject.TABLE)
@ModelProperty(comment = "来源数据结构")
public class SourceSubject extends BaseEntity{
	/**
	 * 
	 */
	public static final String TABLE = TablePreDef.TMS_BA_TAG+"TAX_CATEGORY";
	public static final String SEQ = TABLE;
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CATEGORY_ID",  length = 36)
	String id;
	
	@Column(name = "CATEGORY_NAME",length=200,nullable=false)
	@ModelProperty(comment = "来源数据结构编码")
	String sourceSubjectCode;

	@Column(name = "IS_USED",length=10,nullable=false)
	@ModelProperty(comment = "启用状态")
	String useStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceSubjectCode() {
		return sourceSubjectCode;
	}

	public void setSourceSubjectCode(String sourceSubjectCode) {
		this.sourceSubjectCode = sourceSubjectCode;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
}
