package com.deloitte.tms.vat.bill.pm.model;

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

@Entity
@Table(name = BillBook.TABLE)
@ModelProperty(comment = "发票打印预约")
public class BillBook extends BaseEntity{
	/**
	 * 
	 */
	public static final String TABLE = TablePreDef.TMS_BA_TAG+"BILLBOOK";
	public static final String SEQ = TABLE;
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "BOOK_ID",  length = 36)
	String id;
	
	@Column(name = "BOOK_NAME",length=255,nullable=false)
	@ModelProperty(comment = "客户关系代码")
	String bookName;
	
	@Column(name = "ORG_ID",length=36,nullable=false)
	@ModelProperty(comment = "组织ID")
	String orgId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
