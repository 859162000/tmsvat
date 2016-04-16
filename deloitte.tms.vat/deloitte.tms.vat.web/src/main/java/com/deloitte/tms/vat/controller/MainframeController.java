package com.deloitte.tms.vat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.exception.NoneLoginException;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.security.model.impl.RoleResource;
import com.deloitte.tms.pl.security.model.impl.UrlComponent;
import com.deloitte.tms.pl.security.security.SecurityUtils;
import com.deloitte.tms.pl.security.security.UserAuthentication;
import com.deloitte.tms.pl.security.service.IUrlService;
import com.deloitte.tms.vat.core.common.MenuNode;
import com.deloitte.tms.vat.core.common.MenuNode2;

@Controller
public class MainframeController extends BaseController {

	@Resource
	IUrlService urlService;

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/mainframe")
	public String indexPage() {

		return "mainframe";
	}

	@RequestMapping(value = "/mainframemenu")
	public void getMenu(HttpServletResponse response) {
		ArrayList<MenuNode> menuList = new ArrayList<MenuNode>();
		Map<String, MenuNode> map = new HashMap();

		List<DefaultUrl> list = urlService.loadAllUrls();

		for (DefaultUrl defaultUrl : list) {
			MenuNode node = new MenuNode();
			node.setIcon(defaultUrl.getIcon());
			node.setMenuid(defaultUrl.getId());
			node.setMenuname(defaultUrl.getName());
			node.setUrl(defaultUrl.getUrl());
			if (defaultUrl.getParent() != null) {
				node.setPmenuid(defaultUrl.getParent().getId());
			}

			// menuList.add(node);
			map.put(defaultUrl.getId(), node);
		}

		for (DefaultUrl defaultUrl : list) {
			if (defaultUrl.getParent() != null) {
				if (defaultUrl.getParent().getId() == null) {
					menuList.add((MenuNode) map.get(defaultUrl.getId()));
				} else {

					String pId = defaultUrl.getParent().getId();
					MenuNode pNode = (MenuNode) map.get(pId);
					MenuNode cNode = (MenuNode) map.get(defaultUrl.getId());
					pNode.addMenu(cNode);
				}
			} else {
				menuList.add((MenuNode) map.get(defaultUrl.getId()));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(menuList);
		JSONObject resusts  = new JSONObject();
		resusts.put("menus",jsonArray.toString());
		log.debug(resusts.toString());
		try {
			retJson(response, resusts);
			//retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/mainframemenu2")
	public void getMenu2(HttpServletResponse response) {
		ArrayList<MenuNode2> menuList = new ArrayList<MenuNode2>();
		Map<String, MenuNode2> map = new HashMap();

		List<DefaultUrl> list = urlService.loadAllUrls();

		for (DefaultUrl defaultUrl : list) {
			MenuNode2 node = new MenuNode2();
			node.setIcon(defaultUrl.getIcon());
			node.setId(defaultUrl.getId());
			node.setText(defaultUrl.getName());
			node.setUrl(defaultUrl.getUrl());
			if (defaultUrl.getParent() != null) {
				node.setId(defaultUrl.getParent().getId());
			}

			// menuList.add(node);
			map.put(defaultUrl.getId(), node);
		}

		for (DefaultUrl defaultUrl : list) {
			if (defaultUrl.getParent() != null) {
				if (defaultUrl.getParent().getId() == null) {
					menuList.add((MenuNode2) map.get(defaultUrl.getId()));
				} else {

					String pId = defaultUrl.getParent().getId();
					MenuNode2 pNode = (MenuNode2) map.get(pId);
					MenuNode2 cNode = (MenuNode2) map.get(defaultUrl.getId());
					pNode.addChild(cNode);
				}
			} else {
				menuList.add((MenuNode2) map.get(defaultUrl.getId()));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(menuList);
		//JSONObject resusts  = new JSONObject();
		//resusts.put("menus",jsonArray.toString());
		log.debug(jsonArray.toString());
		try {
			//retJson(response, resusts);
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "mainframe/getUserManus")
	public void getUserManus(HttpServletResponse response) {
		ArrayList<MenuNode2> menuList = new ArrayList<MenuNode2>();
		Collection<DefaultUrl> subordinates= loadMeunUrlsFromCache(null);	
		for (DefaultUrl subordinate : subordinates) {			
			MenuNode2 menuNode=new MenuNode2();
			menuNode.setId(subordinate.getId());
			menuNode.setIcon(subordinate.getIcon());
			menuNode.setId(subordinate.getId());
			menuNode.setText(subordinate.getName());
			menuNode.setUrl(subordinate.getUrl());
			menuList.add(menuNode);
			buildMenu(menuNode,subordinate.getChildren());
		}
		JSONArray jsonArray = JSONArray.fromObject(menuList);
		//JSONObject resusts  = new JSONObject();
		//resusts.put("menus",jsonArray.toString());
		log.debug(jsonArray.toString());
		try {
			//retJson(response, resusts);
			retJsonArray(response, jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildMenu(MenuNode2 parent,
			Collection<DefaultUrl> subordinates) {
		for (DefaultUrl subordinate : subordinates) {			
			MenuNode2 menuNode=new MenuNode2();
			menuNode.setId(subordinate.getId());
			menuNode.setIcon(subordinate.getIcon());
			menuNode.setId(subordinate.getId());
			menuNode.setText(subordinate.getName());
			menuNode.setUrl(subordinate.getUrl());
			parent.addChild(menuNode);
			buildMenu(menuNode,subordinate.getChildren());
		}
	}
	public static final String URL_FOR_NAVI_CACHE_KEY="url_for_navi_cache_key_";

	@Resource
	IUrlService iUrlService;

	public List<DefaultUrl> loadUrlsByRoleId(String roleId) {
		return iUrlService.loadUrlsByRoleId(roleId);
	}

	public List<UrlComponent> loadComponentUrlsByRoleId(final String roleId) {
		return iUrlService.loadComponentUrlsByRoleId(roleId);
	}
	public List<DefaultUrl> loadUrlsByParentId(String parentId)
	{
		return iUrlService.loadUrlsByParentId(parentId);
	}
	
	public List<UrlComponent> loadUrlsByUrlIdAndroleId(String urlId,
			String roleId) {
		return iUrlService.loadUrlsByUrlIdAndroleId(urlId, roleId);
	}

	
	public void saveRoleUrls(String roleId, Collection<String> ids) {
		List<RoleResource> roleResources=iUrlService.loadRoleResourceByRoleId(roleId);
		iUrlService.removeAll(roleResources);
		for(String urlId:ids){
			RoleResource rr=new RoleResource();
			rr.setId(UUID.randomUUID().toString());
			rr.setRoleId(roleId);
			rr.setUrlId(urlId);
			iUrlService.save(rr);			
		}
		
	}

	
	public Integer loadChildNumByParentId(String parentId) {
		return iUrlService.loadChildNumByParentId(parentId);
	}

	
	public List<DefaultUrl> loadAllUrls() {
		return iUrlService.loadAllUrls();
	}

	
	public void deleteDefaultUrlById(String id) {
		// TODO Auto-generated method stub
		iUrlService.deleteDefaultUrlById(id);
	}
	@SuppressWarnings("unchecked")
	public Collection<DefaultUrl> loadMeunUrlsFromCache(String parentId) {
		SecurityUser user = ContextUtils.getCurrentUser();
		if (user == null) {
			throw new NoneLoginException("Please login first");
		}
		String companyId=user.getCompanyId();
		List<DefaultUrl> cacheUrls=(List<DefaultUrl>)CacheUtils.getCacheObject(URL_FOR_NAVI_CACHE_KEY);
		if(cacheUrls==null){
			cacheNavigatorUrls();
			cacheUrls=(List<DefaultUrl>)CacheUtils.getCacheObject(URL_FOR_NAVI_CACHE_KEY);
		}
		Collection<DefaultUrl> urls = getCacheUrls(cacheUrls,companyId,parentId);
		UserAuthentication authentication = new UserAuthentication(user);
		Collection<DefaultUrl> result = new ArrayList<DefaultUrl>();
		authorityCheck(urls,authentication,result);
		return result;
	}
	private List<DefaultUrl> getCacheUrls(List<DefaultUrl> urls,String companyId,String parentId){
		List<DefaultUrl> resultUrls=new ArrayList<DefaultUrl>();
		this.buildCacheUrls(urls,resultUrls,companyId, parentId);
		return resultUrls;
	}	
	private void buildCacheUrls(List<DefaultUrl> urls,List<DefaultUrl> resultUrls,String companyId,String parentId){
		for(DefaultUrl url:urls){
			if(url.getChildren()!=null&&url.getChildren().size()>0){
				url.setHasChild(true);
			}else {
				url.setHasChild(false);
			}
			if(StringUtils.isEmpty(parentId)){
				if(StringUtils.isEmpty(url.getParentId()) && url.getCompanyId()!=null && url.getCompanyId().equals(companyId)){
					resultUrls.add(url);
				}
			}else{
				if(StringUtils.isNotEmpty(url.getParentId()) && url.getParentId().equals(parentId)){
					resultUrls.add(url);
				}
			}
			if(url.getChildren()!=null){
				this.buildCacheUrls(url.getChildren(), resultUrls, companyId, parentId);
			}
		}
	}
	public void cacheNavigatorUrls(){
		Collection<DefaultUrl> urls = loadUrls();
		CacheUtils.putCacheObject(URL_FOR_NAVI_CACHE_KEY, urls);
	}
	private Collection<DefaultUrl> loadUrls(){
		List<DefaultUrl> allurls=getAllDefaultUrls();
		Map<String, Collection<DefaultUrl>> subordinateRelations = new HashMap<String, Collection<DefaultUrl>>();
		for (DefaultUrl url : allurls) {
			//根据上級ID把下属的机构都归在一个集合下
			if (subordinateRelations.keySet().contains(url.getParentId())) {
				Collection<DefaultUrl> subordinates = subordinateRelations.get(url.getParentId());
				subordinates.add(url);
			} else {
				Collection<DefaultUrl> subordinates = new ArrayList<DefaultUrl>();
				subordinates.add(url);
				subordinateRelations.put(url.getParentId(), subordinates);
			}			
		}
		//根据顶层节点组件树
		Collection<DefaultUrl> urls=subordinateRelations.get(null);
		for(DefaultUrl parent:urls){
			assembleTree(parent,subordinateRelations);
		}		
		/* 
		 //改变方式为一次加载,再组合
		urls= loadUrlsByParentId(parentId);
		for(DefaultUrl url:urls){
			url.setChildren(this.loadUrls(url.getId()));
		}*/
		return urls;
	}
	protected void assembleTree(DefaultUrl superior, Map<String, Collection<DefaultUrl>> subordinateRelations) {
		String superiorId = superior.getId();
		superior.setChildren(new ArrayList());
		Collection<DefaultUrl> subordinates = subordinateRelations.get(superiorId);
		if (subordinates == null || subordinates.isEmpty()) {
			return;
		}		
		for (DefaultUrl subordinate : subordinates) {			
			superior.getChildren().add(subordinate);
			subordinate.setParent(superior);
			assembleTree(subordinate, subordinateRelations);
		}
	}
	private List<DefaultUrl> getAllDefaultUrls(){
		return iUrlService.loadAllUrls();
	}
	private void authorityCheck(Collection<DefaultUrl> urls,UserAuthentication authentication,Collection<DefaultUrl> result){
		for (DefaultUrl url : urls) {
			String targetUrl = url.getUrl();
			List<DefaultUrl> children=url.getChildren();
			int childrenCount = 0;
			if(children!=null){
				childrenCount=children.size();
			}
			if (childrenCount==0 && StringUtils.isEmpty(targetUrl)) {
				continue;
			}
			if(StringUtils.isEmpty(targetUrl)){
				targetUrl = url.getName();				
			}
			try {
//				if("销项发票开具".equals(targetUrl)){
//					System.out.println(11);
//				}
				SecurityUtils.checkUrl(authentication, targetUrl);
//				System.out.println("check:"+targetUrl+" pass");
				DefaultUrl newUrl=buildNewUrl(url);
				result.add(newUrl);
				if(children!=null){
					List<DefaultUrl> childrenUrls=new ArrayList<DefaultUrl>();
					newUrl.setChildren(childrenUrls);
					authorityCheck(children,authentication,childrenUrls);				
				}
			} catch (AccessDeniedException ex) {
//				System.out.println("check:"+targetUrl+" fail");
			}
		}
	}
	private DefaultUrl buildNewUrl(DefaultUrl oldUrl){
		DefaultUrl url=new DefaultUrl();
		url.setId(oldUrl.getId());
		url.setName(oldUrl.getName());
		url.setDesc(oldUrl.getDesc());
		url.setUrl(oldUrl.getUrl());
		url.setIcon(oldUrl.getIcon());
		url.setParentId(oldUrl.getParentId());
		url.setCompanyId(oldUrl.getCompanyId());
		return url;
	}
}
