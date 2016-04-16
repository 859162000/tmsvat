package com.deloitte.tms.util.invoice;

import java.math.BigDecimal;

import org.springframework.util.ReflectionUtils;

public class VATInvoiceRed {
    int invkind;
    int doctype;

    String bus_code;   //业务系统发票号   单据号
    String docdate;   //当前日期
    
    String buyer_code ;
    String buyer_name ;
    String buyer_taxpayer_no ;
    String buyer_bank ;
    String buyer_bank_account ;
    String buyer_address;
    String buyer_phone ;
    
    String rednoticenr;
    String refInvnr;
    String refinvcode;
    
    String write_inv;   //开票人
    String check_inv;    //复核人
    String reciver_inv;  //收款人
    
    String sale_name;
    String sale_bank_account;   //销方银行及帐号
    String sale_phone_address;  //销方地址电话
    String sale_phone;
    String sale_bank;
    
    String product_code;
    String product_name;
    int product_num;
    
    int tax_rate;
    BigDecimal tax_val;
    BigDecimal product_price;     //单价
    BigDecimal product_amount;    //金额
    int includetax;             //默认不含税0
    BigDecimal discountvalue;   //红冲单据无折扣金额
    BigDecimal discounttax;    //红冲单据无折扣金额
    String inv_contents;
    
	public int getInvkind() {
		return invkind;
	}
	public void setInvkind(int invkind) {
		this.invkind = invkind;
	}
	public int getDoctype() {
		return doctype;
	}
	public void setDoctype(int doctype) {
		this.doctype = doctype;
	}
	public String getBus_code() {
		return bus_code;
	}
	public void setBus_code(String bus_code) {
		this.bus_code = bus_code;
	}
	public String getDocdate() {
		return docdate;
	}
	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	public String getBuyer_code() {
		return buyer_code;
	}
	public void setBuyer_code(String buyer_code) {
		this.buyer_code = buyer_code;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getBuyer_taxpayer_no() {
		return buyer_taxpayer_no;
	}
	public void setBuyer_taxpayer_no(String buyer_taxpayer_no) {
		this.buyer_taxpayer_no = buyer_taxpayer_no;
	}
	public String getBuyer_bank() {
		return buyer_bank;
	}
	public void setBuyer_bank(String buyer_bank) {
		this.buyer_bank = buyer_bank;
	}
	public String getBuyer_bank_account() {
		return buyer_bank_account;
	}
	public void setBuyer_bank_account(String buyer_bank_account) {
		this.buyer_bank_account = buyer_bank_account;
	}
	public String getBuyer_address() {
		return buyer_address;
	}
	public void setBuyer_address(String buyer_address) {
		this.buyer_address = buyer_address;
	}
	public String getBuyer_phone() {
		return buyer_phone;
	}
	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

	public String getWrite_inv() {
		return write_inv;
	}
	public void setWrite_inv(String write_inv) {
		this.write_inv = write_inv;
	}
	public String getCheck_inv() {
		return check_inv;
	}
	public void setCheck_inv(String check_inv) {
		this.check_inv = check_inv;
	}
	public String getReciver_inv() {
		return reciver_inv;
	}
	public void setReciver_inv(String reciver_inv) {
		this.reciver_inv = reciver_inv;
	}
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	public String getSale_bank_account() {
		return sale_bank_account;
	}
	public void setSale_bank_account(String sale_bank_account) {
		this.sale_bank_account = sale_bank_account;
	}
	public String getSale_phone_address() {
		return sale_phone_address;
	}
	public void setSale_phone_address(String sale_phone_address) {
		this.sale_phone_address = sale_phone_address;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public BigDecimal getProduct_price() {
		return product_price;
	}
	public void setProduct_price(BigDecimal product_price) {
		this.product_price = product_price;
	}
	public BigDecimal getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(BigDecimal product_amount) {
		this.product_amount = product_amount;
	}
	public int getIncludetax() {
		return includetax;
	}
	public void setIncludetax(int includetax) {
		this.includetax = includetax;
	}
	public BigDecimal getDiscountvalue() {
		return discountvalue;
	}
	public void setDiscountvalue(BigDecimal discountvalue) {
		this.discountvalue = discountvalue;
	}
	public BigDecimal getDiscounttax() {
		return discounttax;
	}
	public void setDiscounttax(BigDecimal discounttax) {
		this.discounttax = discounttax;
	}
	public String getSale_phone() {
		return sale_phone;
	}
	public void setSale_phone(String sale_phone) {
		this.sale_phone = sale_phone;
	}
	public String getSale_bank() {
		return sale_bank;
	}
	public void setSale_bank(String sale_bank) {
		this.sale_bank = sale_bank;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(int tax_rate) {
		this.tax_rate = tax_rate;
	}

	public String getInv_contents() {
		return inv_contents;
	}
	public void setInv_contents(String inv_contents) {
		this.inv_contents = inv_contents;
	}
	public String getRednoticenr() {
		return rednoticenr;
	}
	public void setRednoticenr(String rednoticenr) {
		this.rednoticenr = rednoticenr;
	}
	public String getRefInvnr() {
		return refInvnr;
	}
	public void setRefInvnr(String refInvnr) {
		this.refInvnr = refInvnr;
	}
	public String getRefinvcode() {
		return refinvcode;
	}
	public void setRefinvcode(String refinvcode) {
		this.refinvcode = refinvcode;
	}
	public BigDecimal getTax_val() {
		return tax_val;
	}
	public void setTax_val(BigDecimal tax_val) {
		this.tax_val = tax_val;
	}
  
}
