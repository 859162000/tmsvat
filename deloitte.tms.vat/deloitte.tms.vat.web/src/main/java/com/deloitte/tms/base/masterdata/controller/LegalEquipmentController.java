package com.deloitte.tms.base.masterdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipmentInParam2;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.service.EquipmentService;
import com.deloitte.tms.base.masterdata.service.LegalEntityService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEquipmentService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
import com.deloitte.tms.vat.core.common.TreeGenerator;

@Controller
public class LegalEquipmentController extends BaseController{
	
	private static final Logger logger = LoggerFactory
			.getLogger(LegalEquipmentController.class);

	public static final String SPE="#xDeloitte#x";
	public static final String IDSPE="#xfxDeloitte#";
	public static final String attributes1Logic="legalName" + SPE + "equName" + SPE + "newLegalId" + SPE + "newLegalName" + SPE + "newEquId" + SPE + "newEquName";
	
	@Resource
	EquipmentService equipmentService;

	@Resource
	LegalEntityService legalEntityService;
	
	@Resource
	TmsMdLegalEquipmentService legalEquipmentService;
	
	
	//legalEquMgt.do
	
	//@RequestMapping("legalEquMgt.do")
	public String goIndexPage(){
		
		return "base/masterdata/legalEquipmentMgt";
	}
	
	@RequestMapping("legalEquMgtN.do")
	public String goIndexPage2(){
		
		return "base/masterdata/legalEquipmentMgtN";
	}
	
/**
 * 
 *list no used legal and equipment divided
 * 功能详细描述
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
	@RequestMapping("legalEquipment/listLegalFree.do")
	public void listLegalFree(HttpServletResponse response){
		
		
		try{
			StringBuffer legalSb= new StringBuffer("from  ");
			legalSb.append(TmsMdLegalEntity.class.getName() ).append("  where   1=1 ");
			
		    List<TmsMdLegalEntity>	legalList = legalEntityService.findBy(legalSb, new HashMap());	

		    List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		    
		    
		    Iterator<TmsMdLegalEntity> iter =  legalList.iterator();
		   
		    while(iter.hasNext()){
		    	
		    	TmsMdLegalEntity e = iter.next();
		    	
		    	Map<String, Object> map = new HashMap<String, Object>();
		    	
			       map.put("id", e.getId());
			       map.put("legalEntityName",e.getLegalEntityName());
			       
			       results.add(map);
		    }
		    
		    JSONArray jsonArray = JSONArray.fromObject(results);
		    
		    System.out.println("LegalEquipmentController > listLegalFree return: "+jsonArray);
		   
		    this.retJsonArray(response, jsonArray);
		    
		    System.out.println("---------------");
		    
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			this.returnFail(response);
		}
	}
	
	
	/**
	 * 
	 *list no used legal and equipment divided
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
		@RequestMapping("legalEquipment/listEquipmentFree.do")
		public void listEquipmentFree(HttpServletResponse response){
			
			
			try{
			


				
			    JSONArray jsonArray2 = new JSONArray();
			    
			    
			 
				
				StringBuffer equSb= new StringBuffer(" from  ");
				equSb.append( TmsMdEquipment.class.getName() ).append("  where 1=1");
				
				List<TmsMdEquipment> equList = equipmentService.findBy(equSb, new HashMap());
				
				
				Iterator<TmsMdEquipment> iter =  equList.iterator();
				
				
			    List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
			    
			    
			   
			    while(iter.hasNext()){
			    	
			    	TmsMdEquipment e = iter.next();
			    	
			    	Map<String, Object> map = new HashMap<String, Object>();
			    	
				       map.put("id", e.getId());
				       map.put("equipmentName", e.getEquipmentName());
				       
				       results.add(map);
			    }
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);
			    
			    System.out.println("LegalEquipmentController > listEquipmentFree return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
		
			}catch(Exception e){
				
				this.returnFail(response);
			}
		}
	
		
		@RequestMapping("legalEquipment/test2.do")
		public String test2(String attribute1){
			
		
			System.out.println(attribute1);
			return "base/masterdata/legalEquipmentMgt";
		}
		
		
		@RequestMapping("legalEquipment/test3.do")
		public String test3(@ModelAttribute("legalEquOnPage") TmsMdLegalEquipment tmsMdLegalEquipment ){
			
		
			System.out.println(tmsMdLegalEquipment.getAttribute1());
			return "base/masterdata/legalEquipmentMgt";
		}
		
	@RequestMapping("legalEquipment/test")
	public String testReq(Map<String , Object> map){
		
		System.out.println("test ------------------------------legalEquipment/test.do");
		Iterator s = map.keySet().iterator();
		
		
		while(s.hasNext()){
			
			Object o = s.next();
			System.out.println("---key: "+ o + ":value:"+ map.get(o) );
		}
		
		return "base/masterdata/legalEquipmentMgt";
	}
		
	/**
	 * 
	 * 把纳税主体 加入树， 作为当前选择 纳税主体的 下级 
	 * 功能详细描述
	 * @param response
	 * @param tmsMdLegalEquipment
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/addLegalIntoTree.do")
	public void addLegalIntoTree(Map<String, Object> map, HttpServletResponse response){
	//public void addLegalIntoTree(@ModelAttribute("tmsMdLegalEquipment") TmsMdLegalEquipment tmsMdLegalEquipment, HttpServletResponse response){
		
		System.out.println("addLegalIntoTree---------");
		String attributes1 = (String)map.get("attributes1");
		
		
		Iterator s = map.keySet().iterator();
		
		
		while(s.hasNext()){
			
			Object o = s.next();
			System.out.println("---key: "+ o + ":value:"+ map.get(o) );
		}
		
		try{
			
			
		if(AssertHelper.empty(attributes1)){
			//to-do
			return;
		}else{

			 
			
			String oldAttribute1 = attributes1;
			
			if(AssertHelper.empty(oldAttribute1)){
				
				this.returnFail(response); 
				return;
			}
			
			String[] arrayAttr= new String[6];
			arrayAttr = oldAttribute1.split(SPE);//to-do ??
			
			TmsMdLegalEquipment newEnitty = new TmsMdLegalEquipment();
			
			
			
			newEnitty.setParentId("xxx");
			
			newEnitty.setLegalEntityId(arrayAttr[2]);
			
			StringBuffer sb = new StringBuffer();
			sb.append(arrayAttr[3]).append(SPE).append(SPE).append(SPE).append(SPE).append(SPE);
			
			
			newEnitty.setAttribute1(sb.toString());
			
			this.equipmentService.save(newEnitty);
			
			this.returnOk( response);
		}
		
		}catch(Exception e){
			
			this.returnFail(response);
		}
	}
	
	/**
	 * 删除 legal 部门  和 对应的 打印机 关联关系, 把 当前节点的子节点一起删除, 用个check box判断 要不要删除当前节点子节点 以及 删除后怎么处理
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param response
	 * @param legalEquIds
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/delLegalEquipment.do")
	public void delLegalEquipment(HttpServletResponse response, @RequestParam("id") String legalEquIds){/*
		
		try{
		
		if(AssertHelper.empty(legalEquIds)){
			//to-do
			this.returnFail(response);
		}else{
			
			
			String[] ids = legalEquIds.split(this.IDSPE);
			
			
			if(ids==null || ids.length<1){
				this.returnFail(response);
			}
			
			
			ArrayList<TmsMdLegalEquipment> legEquList= new ArrayList<TmsMdLegalEquipment>();
			for(int i=0; i<ids.length; i++){
				
				legEquList.add( new TmsMdLegalEquipment(ids[i]) );
				
			}
			
			
			this.equipmentService.removeAll(legEquList);
			
			
			
			returnOk(response);
		}
		
		}catch(Exception e){
			
			this.returnFail(response);
		}
	*/}
	
	/**
	 * 
	 * 关联选中的设备给 当前 纳税主体 
	 * 功能详细描述
	 * @param response
	 * @param tmsMdLegalEquipment
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
	@RequestMapping("legalEquipment/linkEquToLegalTree.do")
	public void linkEquToLegalTree(HttpServletResponse response, @ModelAttribute("tmsMdLegalEquipment") TmsMdLegalEquipment tmsMdLegalEquipment){/*
		
		try{
			
		if(AssertHelper.empty(tmsMdLegalEquipment)){
			//to-do
			return;
		}else{
			
//public static final String attributes1Logic="legalName" + SPE + "equName" + SPE + "newLegalId" + SPE + "newLegalName" + SPE + "newEquId" + SPE + "newEquName";
			
			String oldAttribute1 = tmsMdLegalEquipment.getAttribute1();
			
			if(AssertHelper.empty(oldAttribute1)){
				
				this.returnFail(response); 
			}		
			
			String[] oldArrayAttr= new String[6];
			oldArrayAttr = oldAttribute1.split(SPE);//to-do ??
			
		
			
			///"legalName" + SPE + "equName" + SPE + "newLegalId" + SPE + "newLegalName" + SPE + "newEquId" + SPE + "newEquName";
			
			///"legalName" + SPE + "equName" + SPE + "newLegalId" + SPE + "newLegalName" + SPE + "newEquId" + SPE + "newEquName";
			
			
			// newEquName 5> equName 1
	
			
			StringBuffer newAttribute1Sb = new StringBuffer();
			newAttribute1Sb.append(oldArrayAttr[0]).append(SPE).append(oldArrayAttr[5]).append(SPE).append(SPE).append(SPE).append(SPE);
			
			
			//////////
			
			tmsMdLegalEquipment.setAttribute1(newAttribute1Sb.toString());
			
			tmsMdLegalEquipment.setEquipmentId(oldArrayAttr[4]);
			
			this.equipmentService.update(tmsMdLegalEquipment, false);
			
			this.returnOk( response);
		}
		
		}catch(Exception e){
			
			this.returnFail(response);
		}
	*/}
		
	
	/**
	 * 移除设备与纳税主体的绑定关系
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param response
	 * @param tmsMdLegalEquipment
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/delEquipmentFromLegal.do")
	public void delEquipmentFromLegal(HttpServletResponse response, @ModelAttribute("tmsMdLegalEquipment") TmsMdLegalEquipment tmsMdLegalEquipment){/*
		
		try{
			
		if(AssertHelper.empty(tmsMdLegalEquipment)){
			//to-do
			return;
		}else{
			
			//to-do need udpate attribute1's status? seems no need as this just  setEquipmentId ...
			
			 
			tmsMdLegalEquipment.setEquipmentId(null);
			
			/////////////////////////
			
			String oldAttribute1 = tmsMdLegalEquipment.getAttribute1();
			
			if(AssertHelper.empty(oldAttribute1)){
				
			}else{r
			
			String[] arrayAttr= new String[6];
			arrayAttr = oldAttribute1.split(SPE);//to-do ??
			
			StringBuffer newAttribut1Sb = new StringBuffer();
			newAttribut1Sb.append(oldAttribute1.split(SPE)[0]).append(SPE).append(SPE).append(SPE).append(SPE).append(SPE);
			
			tmsMdLegalEquipment.setAttribute1(newAttribut1Sb.toString());
		}
			
			//////////////
			
			
			this.equipmentService.update(tmsMdLegalEquipment, false);
			
			this.returnOk( response);
		}
		
		}catch(Exception e){
			
			this.returnFail(response);
		}
	*/}
	
	/**
	 * inin tree of legal equipment on page left
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/treeLegaEquipment.do")
	public void treeLegaEquipment(HttpServletResponse response){
		
		try{

		
			StringBuffer legalEquSb= new StringBuffer("select id, equipmentId, legalEntityId, parentLegalEquipmentId from  ");
			legalEquSb.append( TmsMdLegalEquipment.class.getName() ).append("  where   1=1 ");
			
		    List<TmsMdLegalEntity>	legaEqulList = this.equipmentService.findBy(legalEquSb, new HashMap());
			
		    List<FunctionTreeNode> treeNodes = FunctionTreeNode.generalConvertTreeNodeList(legaEqulList);
		  
		    List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);
		    
		    JSONArray jsonArray = JSONArray.fromObject(results);	
		    
		    System.out.println("------treeLegaEquipment return: "+jsonArray);
			retJsonArray(response, jsonArray);
		    
		}catch(Exception e){
			
		}
	}	
	
	
	
	
	@RequestMapping("legalEquipment/createtree.do")
	public void createTree(HttpServletResponse response) {
		// 读取层次数据结果集列表
		try {
			List<TmsMdEquipment> equipMentList = equipmentService.finAllEquipment();
			List<FunctionTreeNode> treeNodes = convertTreeNodeList(equipMentList);
			List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);//TreeGenerator.buildTree(treeNodes)	;
			JSONArray jsonArray = JSONArray.fromObject(results);	
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private List<FunctionTreeNode> convertTreeNodeList(
			List<TmsMdEquipment> defaultUrls) {
		List<FunctionTreeNode> nodes = null;

		if (defaultUrls != null) {
			nodes = new ArrayList<FunctionTreeNode>();
			for (TmsMdEquipment defaultUrl : defaultUrls) {
				FunctionTreeNode node = convertTreeNode(defaultUrl);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	private FunctionTreeNode convertTreeNode(TmsMdEquipment defaultUrl) {
		FunctionTreeNode node = null;
		if (defaultUrl != null) {
			node = new FunctionTreeNode();
			node.setId(defaultUrl.getId());//
			node.setChecked(false);
			node.setText(defaultUrl.getEquipmentName());
			node.setPid(defaultUrl.getParentEquipmentId());
		  
			

			Map<String, Object> map = new HashMap<String, Object>();

			node.setAttributes(map);
		}
		return node;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @author tigchen
	 *	new page
	 * 功能详细描述
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/listLegalAll.do")
	public void listLegalAll(HttpServletResponse response) {
		// 读取层次数据结果集列表
		try {
		
		    List<TmsMdLegalEntity>	legalList = legalEntityService.listAll();	

		    List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		    
		    
		    Iterator<TmsMdLegalEntity> iter =  legalList.iterator();
		   
		    while(iter.hasNext()){
		    	
		    	TmsMdLegalEntity e = iter.next();
		    	
		    	Map<String, Object> map = new HashMap<String, Object>();
		    	
			       map.put("id", e.getId());
			       map.put("legalEntityName",e.getLegalEntityName());
			       
			       results.add(map);
		    }
		    
		    JSONArray jsonArray = JSONArray.fromObject(results);
		    
		    System.out.println("LegalEquipmentController > listLegalAll return: "+jsonArray);
		   
		    this.retJsonArray(response, jsonArray);
		    
		    System.out.println("---------------");
		    
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param legalId
	 * @param pageNumber
	 * @param pageSize
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/getRelatedEqu.do")	
	public void getRelatedEqu(String legalId,Integer pageNumber, Integer pageSize,  HttpServletResponse response){
		
		try{
		
		if(AssertHelper.empty(legalId)){
			return;
		}
		if(AssertHelper.empty(pageNumber)){
			pageNumber=1;
		}
		if(AssertHelper.empty(pageSize)){
			pageSize=10;
		}
		
		DaoPage daoPage = this.legalEquipmentService.getEquPageByRelatedLeg(legalId, pageNumber, pageSize);
		
		List<Object[]> list=new ArrayList<Object[]>();
		
		List<TmsMdEquipmentInParam2> listOk = new ArrayList<TmsMdEquipmentInParam2>();
		
		int total=0;
		
		if(daoPage==null ){//其实肯定不为null
				
			//list.add(new TmsMdEquipment());
		}else{
			total=daoPage.getTotal();
			
			//todo usig another list of xxInPara
			list  = (List<Object[]>)daoPage.getResult();
			
			for(Object[] obj : list){
				
				String isDefault = (String)obj[0];
				TmsMdEquipment equ = (TmsMdEquipment)obj[1];
				TmsMdEquipmentInParam2 equInparam = new TmsMdEquipmentInParam2(isDefault, equ);
				if(null==equ.getParentEquipmentId()){
					equInparam.setParentEquipmentName("");
				}else{
					TmsMdEquipment parent = (TmsMdEquipment) equipmentService.get(TmsMdEquipment.class, equ.getParentEquipmentId());
					if(null!=parent){
						equInparam.setParentEquipmentName(parent.getEquipmentName());
					}
				}
				listOk.add(equInparam);
			}
		}
	/*	if(daoPage.getTotal()<1){
			
		}*/
		this.returnList(response,total , listOk);

	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param legalId
	 * @param pageNumber
	 * @param pageSize
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/getNoRelatedEqu.do")	
	public void getNoRelatedEqu(String legalId,Integer pageNumber, Integer pageSize,  HttpServletResponse response){
		
		try{
		
		if(AssertHelper.empty(legalId)){
			return;
		}
		if(AssertHelper.empty(pageNumber)){
			pageNumber=1;
		}
		if(AssertHelper.empty(pageSize)){
			pageSize=90000000;
		}
		
		DaoPage daoPage = this.legalEquipmentService.getEquPageByNoRelatedLeg(legalId, pageNumber, pageSize);
		
		List<TmsMdEquipmentInParam2> list=null;
		int total=0;
		
		if(daoPage==null ){//其实肯定不为null
				
			//list.add(new TmsMdEquipment());
		}else{
			total=daoPage.getTotal();
			list  = (List<TmsMdEquipmentInParam2>)daoPage.getResult();
		}
	/*	if(daoPage.getTotal()<1){
			
		}*/
		this.returnList(response,total , list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param legalId
	 * @param pageNumber
	 * @param pageSize
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/bindEqu.do")	
	public void bindEqu(String legalId,String equIds,  HttpServletResponse response){
		
		try{
		
		if(AssertHelper.empty(legalId)){
			return;
		}
		if(AssertHelper.empty(equIds)){
			return;
		}
		
		String[] equId = equIds.split(",");
		System.out.println("bindEqu > equId:"+equId+";legalId:"+legalId);
		
		this.legalEquipmentService.saveBindEqu(legalId, equId);
		this.returnOk(response, "绑定成功");
	
		}catch(Exception e){
			e.printStackTrace();
			try {
				this.returnFail(response, "绑定失败");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param legalId
	 * @param pageNumber
	 * @param pageSize
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/removeEqu.do")	
	public void removeEqu(String legalId,String equIds,  HttpServletResponse response){
		
		try{
		
		if(AssertHelper.empty(legalId)){
			return;
		}
		if(AssertHelper.empty(equIds)){
			return;
		}
		
		String[] equId = equIds.split(",");
		System.out.println("removeEqu > equId:"+equId+";legalId:"+legalId);
		
		this.legalEquipmentService.removeEquByInfo(legalId, equId);
		this.returnOk(response, "删除成功");
	
		}catch(Exception e){
			e.printStackTrace();
			try {
				this.returnFail(response, "删除失败");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param legalId
	 * @param pageNumber
	 * @param pageSize
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/updateDefaultEqu.do")	
	public void updateDefaultEqu(String legalId, String defaultEquIds, String defaultEqus, HttpServletResponse response){
		
		
		try{
		
		if(AssertHelper.empty(legalId)){
			return;
		}
		if(AssertHelper.empty(defaultEquIds) || AssertHelper.empty(defaultEqus)){
			return;
		}
		
		HashMap<String , String> defaultMap = new HashMap<String , String>();
		
		String[] k = defaultEquIds.split(",");
		String[] v = defaultEqus.split(",");
		
		int i=0;
		for(String one : k){
			
			String tempV=v[i];
			if(AssertHelper.empty(tempV)){
				tempV="0";
			}
			
			defaultMap.put(one, tempV);
			i++;
		}
		
		if(defaultMap.size()<1){
			this.returnFail(response, "请正确设置 默认打印设备 ");
		}
		
		
		System.out.println("updateDefaultEqu > defaultMap:"+defaultMap+";legalId:"+legalId);
		
		this.legalEquipmentService.updateDefaultEqu(legalId, defaultMap);
		this.returnOk(response, "更新信息成功");
	
		}catch(Exception e){
			e.printStackTrace();
			try {
				this.returnFail(response, "更新信息失败,请确保每条记录都设置正确");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * @author tigchen
	 *	new page
	 * 功能详细描述
	 * @param response
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("legalEquipment/searchLegal.do")
	public void searchLegal(HttpServletResponse response, String legalEntityName, String registrationNumber) {
		// 读取层次数据结果集列表
		try {
			
			HashMap<String, Object> para = new HashMap<String, Object>();
			if(AssertHelper.empty(legalEntityName) || ""==legalEntityName.trim()){
				
			}else{
				para.put("legalEntityName", legalEntityName.trim());
			}
			
			if(AssertHelper.empty(registrationNumber) || ""==registrationNumber.trim()){
				
			}else{
				para.put("registrationNumber", registrationNumber.trim());
			}
		
		    List<TmsMdLegalEntity>	legalList = legalEntityService.searchLegal(para);

		    List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		    
		    
		    Iterator<TmsMdLegalEntity> iter =  legalList.iterator();
		   
		    while(iter.hasNext()){
		    	
		    	TmsMdLegalEntity e = iter.next();
		    	
		    	Map<String, Object> map = new HashMap<String, Object>();
		    	
			       map.put("id", e.getId());
			       map.put("legalEntityName",e.getLegalEntityName());
			       
			       results.add(map);
		    }
		    
		    this.returnList(response, results.size(), results);
		    
		  /*  JSONArray jsonArray = JSONArray.fromObject(results);
		    
		    System.out.println("LegalEquipmentController > listLegalAll return: "+jsonArray);
		   
		    this.retJsonArray(response, jsonArray);
		    
		    System.out.println("---------------");*/
		    
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
