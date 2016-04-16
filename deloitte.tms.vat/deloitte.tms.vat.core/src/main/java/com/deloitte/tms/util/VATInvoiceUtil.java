package com.deloitte.tms.util;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.util.invoice.InvoiceResult;
import com.deloitte.tms.util.invoice.VATInvoiceRed;
import com.deloitte.tms.util.invoice.VATInvoiceSpecial;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class VATInvoiceUtil {

	private static String machine_tax_id = "31010105298235X";
	private static String machine_id = "0";

	private static String url = "http://192.168.1.103:8085/axis2/services/SajtIssueInvoiceService";
	/*private static SajtIssueInvoiceServiceStub stub;
	static {
		stub = new SajtIssueInvoiceServiceStub(url);
	}*/

	public static InvoiceResult printInv(String invoiceType,
			VATInvoiceSpecial resultSet) throws Exception {

		// 专票
		if (Integer.parseInt(invoiceType) == 1) {
			return printSpecialInv(resultSet);
			// 正常
		} else {
			return printNormalInv(resultSet);
		}

	}

	public static InvoiceResult printRedInv(String invoiceType,
			VATInvoiceRed resultSet) throws Exception {

		// 专票
		if (Integer.parseInt(invoiceType) == 1) {
			return printSpecialRedInv(resultSet);
			// 正常
		} else {
			return printNormalRedInv(resultSet);
		}

	}

	/* 正常普通发票 */
	private static InvoiceResult printNormalInv(VATInvoiceSpecial resultSet) {

		if (resultSet == null) {
			return new InvoiceResult(false, "发票数据为空");
		}

		StringBuffer commandGen = new StringBuffer();
		commandGen
				.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
				.append("<issueinv><invrecord><invrecordhead><machinetaxnr>")
				.append(machine_tax_id)
				.append("</machinetaxnr><machinenr>")
				.append(machine_id)
				.append("</machinenr><invkind><value>")
				.append(0)
				.append("</value></invkind><doctype><value>")
				.append(1)
				.append("</value></doctype><docnr>")
				.append(resultSet.getBus_code())
				.append("</docnr><docdate>")
				.append(resultSet.getDocdate())
				.append("</docdate><custnr>")
				.append(resultSet.getBuyer_code())
				.append("</custnr><custname>")
				.append(resultSet.getBuyer_name())
				.append("</custname><custtaxnr>")
				.append(resultSet.getBuyer_taxpayer_no())
				.append("</custtaxnr><custaddrtel>")
				.append(resultSet.getBuyer_address())
				.append(" ")
				.append(resultSet.getBuyer_phone())
				.append("</custaddrtel><custbankacct>")
				.append(resultSet.getBuyer_bank())
				.append(" ")
				.append(resultSet.getBuyer_bank_account())
				.append("</custbankacct><memo>")
				.append(resultSet.getInv_contents())
				.append("</memo><memo2></memo2><refinvcode></refinvcode><refinvnr></refinvnr>")
				.append("<rednoticenr></rednoticenr><issuer>")
				.append(resultSet.getWrite_inv())
				.append("</issuer><checker></checker><payee></payee><sellerbankacct>")
				.append(resultSet.getSale_bank())
				.append(" ")
				.append(resultSet.getSale_bank_account())
				.append("</sellerbankacct><selleraddresstel>")
				.append(resultSet.getSale_phone_address())
				.append(" ")
				.append(resultSet.getSale_phone())
				.append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>")
				.append(resultSet.getProduct_code())
				.append("</prodnr><prodname>")
				.append(resultSet.getProduct_name())
				.append("</prodname><spec>")
				.append(resultSet.getProduct_style())
				.append("</spec><unit>")
				.append(resultSet.getProduct_unit())
				.append("</unit><quantity>1</quantity><price>")
				.append(resultSet.getProduct_price())
				.append("</price><amount>")
				.append(resultSet.getProduct_price())
				.append("</amount><taxrate>")
				.append(resultSet.getTax_rate())
				.append("</taxrate><tax>")
				.append(resultSet.getTax_val())
				.append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
				.append("</invrecord></issueinv></siiscmd>");

		return remotePrintInvoice(commandGen);

	}

	/* 红冲普票单据 */
	private static InvoiceResult printNormalRedInv(VATInvoiceRed resultSet) {
		if (resultSet == null) {
			return new InvoiceResult(false, "发票数据为空");
		}

		StringBuffer commandGen = new StringBuffer();
		commandGen
				.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
				.append("<issueinv><invrecord><invrecordhead><machinetaxnr>")
				.append(machine_tax_id)
				.append("</machinetaxnr><machinenr>")
				.append(machine_id)
				.append("</machinenr><invkind><value>")
				.append(2)
				.append("</value></invkind><doctype><value>")
				.append(2)
				.append("</value></doctype><docnr>")
				.append(resultSet.getBus_code())
				.append("</docnr><docdate>")
				.append(resultSet.getDocdate())
				.append("</docdate><custnr>")
				.append(resultSet.getBuyer_code())
				.append("</custnr><custname>")
				.append(resultSet.getBuyer_name())
				.append("</custname><custtaxnr>")
				.append(resultSet.getBuyer_taxpayer_no())
				.append("</custtaxnr><custaddrtel>")
				.append(resultSet.getBuyer_address())
				.append(" ")
				.append(resultSet.getBuyer_phone())
				.append("</custaddrtel><custbankacct>")
				.append(resultSet.getBuyer_bank())
				.append(" ")
				.append(resultSet.getBuyer_bank_account())
				.append("</custbankacct><memo>")
				.append(resultSet.getInv_contents())
				.append("</memo><memo2></memo2><refinvcode>")
				.append(resultSet.getRefinvcode())
				.append("</refinvcode><refinvnr>")
				.append(resultSet.getRefInvnr())
				.append("</refinvnr>")
				.append("<rednoticenr>")
				.append(resultSet.getRednoticenr())
				.append("</rednoticenr><issuer>")
				.append(resultSet.getWrite_inv())
				.append("</issuer><checker></checker><payee></payee><sellerbankacct>")
				.append(resultSet.getSale_bank())
				.append(" ")
				.append(resultSet.getSale_bank_account())
				.append("</sellerbankacct><selleraddresstel>")
				.append(resultSet.getSale_phone_address())
				.append(" ")
				.append(resultSet.getSale_phone())
				.append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>")
				.append(resultSet.getProduct_code())
				.append("</prodnr><prodname>")
				.append(resultSet.getProduct_name())
				.append("</prodname><spec>")
				.append("</spec><unit>")
				.append("</unit><quantity>1</quantity><price>")
				.append(resultSet.getProduct_price())
				.append("</price><amount>")
				.append(resultSet.getProduct_price())
				.append("</amount><taxrate>")
				.append(resultSet.getTax_rate())
				.append("</taxrate><tax>")
				.append(resultSet.getTax_val())
				.append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
				.append("</invrecord></issueinv></siiscmd>");

		return remotePrintInvoice(commandGen);
	}

	/* 正常专用发票 */
	private static InvoiceResult printSpecialInv(VATInvoiceSpecial resultSet) {
		if (resultSet == null) {
			return new InvoiceResult(false, "发票数据为空");
		}

		StringBuffer commandGen = new StringBuffer();
		commandGen
				.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
				.append("<issueinv><invrecord><invrecordhead><machinetaxnr>")
				.append(machine_tax_id)
				.append("</machinetaxnr><machinenr>")
				.append(machine_id)
				.append("</machinenr><invkind><value>")
				.append(0)
				.append("</value></invkind><doctype><value>")
				.append(1)
				.append("</value></doctype><docnr>")
				.append(resultSet.getBus_code())
				.append("</docnr><docdate>")
				.append(resultSet.getDocdate())
				.append("</docdate><custnr>")
				.append(resultSet.getBuyer_code())
				.append("</custnr><custname>")
				.append(resultSet.getBuyer_name())
				.append("</custname><custtaxnr>")
				.append(resultSet.getBuyer_taxpayer_no())
				.append("</custtaxnr><custaddrtel>")
				.append(resultSet.getBuyer_address())
				.append(" ")
				.append(resultSet.getBuyer_phone())
				.append("</custaddrtel><custbankacct>")
				.append(resultSet.getBuyer_bank())
				.append(" ")
				.append(resultSet.getBuyer_bank_account())
				.append("</custbankacct><memo>")
				.append(resultSet.getInv_contents())
				.append("</memo><memo2></memo2><refinvcode></refinvcode><refinvnr></refinvnr>")
				.append("<rednoticenr></rednoticenr><issuer>")
				.append(resultSet.getWrite_inv())
				.append("</issuer><checker></checker><payee></payee><sellerbankacct>")
				.append(resultSet.getSale_bank())
				.append(" ")
				.append(resultSet.getSale_bank_account())
				.append("</sellerbankacct><selleraddresstel>")
				.append(resultSet.getSale_phone_address())
				.append(" ")
				.append(resultSet.getSale_phone())
				.append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>")
				.append(resultSet.getProduct_code())
				.append("</prodnr><prodname>")
				.append(resultSet.getProduct_name())
				.append("</prodname><spec>")
				.append(resultSet.getProduct_style())
				.append("</spec><unit>")
				.append(resultSet.getProduct_unit())
				.append("</unit><quantity>1</quantity><price>")
				.append(resultSet.getProduct_price())
				.append("</price><amount>")
				.append(resultSet.getProduct_price())
				.append("</amount><taxrate>")
				.append(resultSet.getTax_rate())
				.append("</taxrate><tax>")
				.append(resultSet.getTax_val())
				.append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
				.append("</invrecord></issueinv></siiscmd>");

		return remotePrintInvoice(commandGen);
	}

	/* 红冲专票单据 */
	private static InvoiceResult printSpecialRedInv(VATInvoiceRed resultSet) {
		if (resultSet == null) {
			return new InvoiceResult(false, "发票数据为空");
		}

		StringBuffer commandGen = new StringBuffer();
		commandGen
				.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
				.append("<issueinv><invrecord><invrecordhead><machinetaxnr>")
				.append(machine_tax_id)
				.append("</machinetaxnr><machinenr>")
				.append(machine_id)
				.append("</machinenr><invkind><value>")
				.append(0)
				.append("</value></invkind><doctype><value>")
				.append(2)
				.append("</value></doctype><docnr>")
				.append(resultSet.getBus_code())
				.append("</docnr><docdate>")
				.append(resultSet.getDocdate())
				.append("</docdate><custnr>")
				.append(resultSet.getBuyer_code())
				.append("</custnr><custname>")
				.append(resultSet.getBuyer_name())
				.append("</custname><custtaxnr>")
				.append(resultSet.getBuyer_taxpayer_no())
				.append("</custtaxnr><custaddrtel>")
				.append(resultSet.getBuyer_address())
				.append(" ")
				.append(resultSet.getBuyer_phone())
				.append("</custaddrtel><custbankacct>")
				.append(resultSet.getBuyer_bank())
				.append(" ")
				.append(resultSet.getBuyer_bank_account())
				.append("</custbankacct><memo>")
				.append(resultSet.getInv_contents())
				.append("</memo><memo2></memo2><refinvcode>")
				.append(resultSet.getRefinvcode())
				.append("</refinvcode><refinvnr>")
				.append(resultSet.getRefInvnr())
				.append("</refinvnr>")
				.append("<rednoticenr>")
				.append(resultSet.getRednoticenr())
				.append("</rednoticenr><issuer>")
				.append(resultSet.getWrite_inv())
				.append("</issuer><checker></checker><payee></payee><sellerbankacct>")
				.append(resultSet.getSale_bank())
				.append(" ")
				.append(resultSet.getSale_bank_account())
				.append("</sellerbankacct><selleraddresstel>")
				.append(resultSet.getSale_phone_address())
				.append(" ")
				.append(resultSet.getSale_phone())
				.append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>")
				.append(resultSet.getProduct_code())
				.append("</prodnr><prodname>")
				.append(resultSet.getProduct_name())
				.append("</prodname><spec>")
				.append("</spec><unit>")
				.append("</unit><quantity>1</quantity><price>")
				.append(resultSet.getProduct_price())
				.append("</price><amount>")
				.append(resultSet.getProduct_price())
				.append("</amount><taxrate>")
				.append(resultSet.getTax_rate())
				.append("</taxrate><tax>")
				.append(resultSet.getTax_val())
				.append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
				.append("</invrecord></issueinv></siiscmd>");

		return remotePrintInvoice(commandGen);
	}

	private static InvoiceResult remotePrintInvoice(StringBuffer commandGen) {

		/*SaveDocument sd = new SaveDocument();
		sd.setCommand(commandGen.toString());
		SaveDocumentResponse codeResponse = stub.saveDocument(sd);

		// S成功，F失败 推送信息
		if (StringUtils.endsWithIgnoreCase(codeResponse.get_return()
				.getStatus(), "S")) {

			String returnMsg = codeResponse.get_return().getCmdMessage();
			String str_code = getINVInformation(returnMsg, "<binvcode>",
					"</binvcode>");
			String str_num = getINVInformation(returnMsg, "<binvnr>",
					"</binvnr>");

			String pcommand = GeneratPrintCommand(str_code, str_num, 0);

			SaveDocument sd2 = new SaveDocument();
			sd2.setCommand(pcommand);
			SaveDocumentResponse printResponse = stub.saveDocument(sd2);
			// 打印发票成功
			if (StringUtils.endsWithIgnoreCase(printResponse.get_return()
					.getStatus(), "S")) {
				return new InvoiceResult(true, "打印发票成功！");
			} else {
				return new InvoiceResult(false, "打印发票失败！");
			}
		} else {
			// 错误信息
			return new InvoiceResult(false, "获取发票代码失败！");
		}*/
		
		return null;
	}

	public static String getINVInformation(String returnMsg, String search1,
			String search2) {
		int start = StringUtils.indexOf(returnMsg, search1);
		int end = StringUtils.indexOf(returnMsg, search2);
		String[] inv_code = StringUtils.substring(returnMsg, start, end).split(
				">");
		String code = inv_code[1];

		return code;
	}

	public static String GeneratPrintCommand(String code, String num,
			int invKind) {
		StringBuffer printQuery = new StringBuffer();
		printQuery
				.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.cn\">")
				.append("<printinv><key><invkind><value>").append(invKind)
				.append("</value></invkind><invcode>").append(code)
				.append("</invcode><invnr>").append(num)
				.append("</invnr></key></printinv></siiscmd>");
		return printQuery.toString();
	}

}
