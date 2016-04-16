
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceInWarehouse.java  
 * @Package: com.deloitte.tms.base.invoiceout.print.model  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年3月16日 下午8:22:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.vat.trnsctrecog.model.SourceSubject;


/**  
 * 销项发票请领入库数据字典
 * 功能详细描述
 * @author sqing
 * @create 2016年3月16日 下午8:22:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Entity
@Table(name = InvoiceTrxDataDict.TABLE)
@ModelProperty(comment = "数据字典")
public class InvoiceTrxDataDict {
	public static final String TABLE = TablePreDef.BASEPRE+"CATEGORY";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CATEGORY_ID",  length = 36)
	protected String id;//

	@Column(name = "LABEL_",length=500)
	@ModelProperty(comment = "标识")
	String lable;

	@Column(name = "PARENTID",length=36)
	@ModelProperty(comment = "父节点ID")
	String parentid;
	
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
}
