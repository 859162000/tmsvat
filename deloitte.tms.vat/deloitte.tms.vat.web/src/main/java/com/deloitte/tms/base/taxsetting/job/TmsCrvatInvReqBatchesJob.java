package com.deloitte.tms.base.taxsetting.job;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatInvReqBatchesJobService;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;
import com.deloitte.tms.vat.salesinvoice.view.TmsCrvatInvReqBatchesLController;
@Component(value="tmsCrvatInvReqBatchesJob")
public class TmsCrvatInvReqBatchesJob implements Job, JobTest {
	@Resource
	TmsCrvatInvReqBatchesJobService tmsCrvatInvReqBatchesJobServiceImpl;
	@Resource
	TmsCrvatInvReqBatchesLController  tmsCrvatInvReqBatchesLController;
	@Override
	public void execute() {
		//取得长江证券数据
		Map<String,List<TmsCrvatInvReqBatchesLInParam>> map =  tmsCrvatInvReqBatchesJobServiceImpl.analyzeTmsCrvatInvReqBatchesParam();  
		 saveInvoiceReqHead(map);
}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 保存申请单
	 * @throws ParseException 
	 */
	public void saveInvoiceReqHead(Map<String,List<TmsCrvatInvReqBatchesLInParam>> map){
		Iterator<Entry<String, List<TmsCrvatInvReqBatchesLInParam>>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, List<TmsCrvatInvReqBatchesLInParam>> entry = iterator.next();
			String crvatInvoiceReqNumber =  FlowHelper.getNextFlowNo("INVOICEREQ");//申请单号
			List<TmsCrvatInvReqBatchesLInParam> listTmsCrvatInvReqBatchesLInParam = entry.getValue();//申请单明细行
			if(listTmsCrvatInvReqBatchesLInParam.size()>0){
				TmsCrvatInvReqBatchesH tmsCrvatInvReqBatchesH = new TmsCrvatInvReqBatchesH();//特殊批量开票申请行
				
				ReflectUtils.copyProperties(listTmsCrvatInvReqBatchesLInParam.get(0), tmsCrvatInvReqBatchesH);
				if(listTmsCrvatInvReqBatchesLInParam.get(0).getTips()!=0){
					tmsCrvatInvReqBatchesH.setAttribute4("false");
				}
				BigDecimal invoiceAmounts = listTmsCrvatInvReqBatchesLInParam.get(listTmsCrvatInvReqBatchesLInParam.size()-1).getInvoiceAmounts();
				tmsCrvatInvReqBatchesH.setAttribute1(invoiceAmounts.toString());
				tmsCrvatInvReqBatchesH.setId(IdGenerator.getUUID());
				tmsCrvatInvReqBatchesH.setCrvatInvoiceReqNumber(crvatInvoiceReqNumber);
				tmsCrvatInvReqBatchesH.setStatus("50");//申请状态
				tmsCrvatInvReqBatchesJobServiceImpl.save(tmsCrvatInvReqBatchesH);
				
				
				for(TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam:listTmsCrvatInvReqBatchesLInParam){
					TmsCrvatInvReqBatchesL tmsCrvatInvReqBatchesL = new TmsCrvatInvReqBatchesL();
					ReflectUtils.copyProperties(tmsCrvatInvReqBatchesLInParam, tmsCrvatInvReqBatchesL);
					tmsCrvatInvReqBatchesL.setId(IdGenerator.getUUID());
					tmsCrvatInvReqBatchesL.setCrvatInvReqBatchesHId(tmsCrvatInvReqBatchesH.getId());
					tmsCrvatInvReqBatchesJobServiceImpl.save(tmsCrvatInvReqBatchesL);
				}
				
				try {
					tmsCrvatInvReqBatchesLController.batchSaveTmsCrvatInvReqBatchesLToPrintPool(tmsCrvatInvReqBatchesH.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		
		
		
		
	}
	
	
	
	
	

}
