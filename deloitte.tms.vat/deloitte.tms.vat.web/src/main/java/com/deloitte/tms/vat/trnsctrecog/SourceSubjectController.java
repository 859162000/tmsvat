
/**    
 * Copyright (C),Deloitte
 * @FileName: SourceSubjectController.java  
 * @Package: com.deloitte.tms.vat.controller  
 * @Description: //模块目的、功能描述  
 * @Author weijia  
 * @Date 2016年3月15日 下午4:37:49  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.trnsctrecog.model.SourceSubject;
import com.deloitte.tms.vat.trnsctrecog.model.SourceSubjectInParam;
import com.deloitte.tms.vat.trnsctrecog.service.ISourceSubjectService;


/**  
 * 来源数据结构定义controller
 * 功能详细描述
 * @author weijia
 * @create 2016年3月15日 下午4:37:49 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("sourceSubject")
public class SourceSubjectController {
	@Resource
	private ISourceSubjectService sourceSubjectService;
	
	@ResponseBody
	@RequestMapping(value = "/loadSourceSubjectPage", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadSourceSubjectPage(@RequestParam Map<String,Object> parameter) throws Exception {
		//DaoPage daoPage=sourceSubjectService.findSourceSubjectByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
		DaoPage daoPage=null;
		return daoPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadSourceSubjectPageList", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadSourceSubjectPageList(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=sourceSubjectService.findSourceSubjectByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadSourceSubjectPartList", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadSourceSubjectPartList(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=sourceSubjectService.findSourceSubjectPartByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	
	
	@RequestMapping(value = "/sourceSubjectManageInit", method = RequestMethod.GET)
	public String SourceSubjectManageInit()throws Exception{
		return "vat/trnsctrecog/sourceSubjectManageInit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveSourceSubject", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveSourceSubject(SourceSubjectInParam inParam) throws Exception {
		System.out.println("保存成功");
		/*
		SourceSubject entity=SourceSubjectService.convertSourceSubjectInParamToEntity(inParam);
		if(entity.getId()==null){
			SourceSubjectService.save(entity);
		}
		else{
			SourceSubjectService.update(entity);
		}
		inParam.setId(entity.getId());*/
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveSourceSubjectPart", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveSourceSubjectPart(SourceSubjectInParam inParam) throws Exception {
		System.out.println("保存成功");
		/*
		SourceSubject entity=SourceSubjectService.convertSourceSubjectInParamToEntity(inParam);
		if(entity.getId()==null){
			SourceSubjectService.save(entity);
		}
		else{
			SourceSubjectService.update(entity);
		}
		inParam.setId(entity.getId());*/
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeSourceSubjects", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeSourceSubjects(@RequestParam(value="id") String SourceSubjectKeys) {
		AssertHelper.notEmpty_assert(SourceSubjectKeys,"需要删除的用户不能为空");
		//String[] SourceSubjectIds=SourceSubjectKeys.split(",");
		//for(String SourceSubjectId:SourceSubjectIds){
		//}
	}

	@ResponseBody
	@RequestMapping(value = "/removeSourceSubjectsPart", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeSourceSubjectsPart(@RequestParam(value="id") String SourceSubjectKeys) {
		AssertHelper.notEmpty_assert(SourceSubjectKeys,"需要删除的用户不能为空");
		//String[] SourceSubjectIds=SourceSubjectKeys.split(",");
		//for(String SourceSubjectId:SourceSubjectIds){
		//}
	}
	@ResponseBody
	@RequestMapping(value = "/loadSourceSubject", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<SourceSubjectInParam> loadSourceSubject(Map<String, Object> map) throws Exception {
//		List result=SourceSubjectService.findSourceSubjectByParams(map);
		return null;
	}
	
	public SourceSubjectInParam loadAddSourceSubject(Map<String, Object> map) throws Exception {
		SourceSubjectInParam inParam=new SourceSubjectInParam();
		return inParam;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/loadModifySourceSubject", method = RequestMethod.GET)
	public SourceSubjectInParam loadModifySourceSubject(@RequestParam Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		/*
		SourceSubject entity=(SourceSubject)SourceSubjectService.get(SourceSubject.class,id.toString());
		SourceSubjectInParam inParam=SourceSubjectService.convertSourceSubjectToInParam(entity);*/
		SourceSubjectInParam inParam = null;
		
		return inParam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadModifySourceSubjectPart", method = RequestMethod.GET)
	public SourceSubjectInParam loadModifySourceSubjectPart(@RequestParam Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		/*
		SourceSubject entity=(SourceSubject)SourceSubjectService.get(SourceSubject.class,id.toString());
		SourceSubjectInParam inParam=SourceSubjectService.convertSourceSubjectToInParam(entity);*/
		SourceSubjectInParam inParam = null;
		
		return inParam;
	}
	
	
	
	public void updateModifySourceSubject(SourceSubjectInParam inParam) throws Exception {
//		SourceSubject entity=(SourceSubject)SourceSubjectService.get(SourceSubject.class,inParam.getId());
		SourceSubject entity=null;
		ReflectUtils.copyProperties(inParam, entity);
//		SourceSubjectService.update(entity);
	}
}
