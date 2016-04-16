package com.deloitte.tms.pl.core.commons.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {
	public static String  process(String template_str,Object content)
	{
		return process(new String[]{},template_str,content);
	}
	public static String  process(String[] templatePaths,String template_str,Object content){
		Configuration freeMarkerEngine = new Configuration();
    	freeMarkerEngine.setDefaultEncoding("UTF-8"); 
    	List loaders = new ArrayList();
    	for (int i = 0; i < templatePaths.length; i++) {
        	File file = new File(templatePaths[i]);
        	if(file.exists() && file.isDirectory()) {
        		try {
					loaders.add(new FileTemplateLoader(file));
				}
				catch (IOException e) {
					throw new RuntimeException("模板路径值:" + templatePaths[i]+"加载错误", e);
				}
        	} else {
        		System.out.println("模板路径值:" + templatePaths[i]+"不存在或不是一个文件夹");
        	}
		}
    	//默认加载class跟目录
    	loaders.add(new ClassTemplateLoader(FreeMarkerUtils.class,"/"));
    	freeMarkerEngine.setTemplateLoader(new MultiTemplateLoader((TemplateLoader[]) loaders.toArray(new TemplateLoader[loaders.size()])));
    	Template template;
		try {
			template = freeMarkerEngine.getTemplate(template_str);
		} catch (IOException e1) {
			throw new RuntimeException("模板文件:" + template_str+"没有找到",e1);
		}
    	StringWriter tempWriter = new StringWriter();
		BufferedWriter bw = new BufferedWriter(tempWriter);
		Map rootMap = new HashMap();
		rootMap.put("pojo", content);
		try {
			template.process(rootMap, bw);
		} catch (TemplateException e1) {
			throw new RuntimeException("模板文件:" + template_str+"解析错误",e1);
		} catch (IOException e1) {
			throw new RuntimeException("模板文件:" + template_str+"发生io错误",e1);
		}
		try {
			bw.flush();
		}
		catch (IOException e) {
			throw new RuntimeException("内容写为字符串时发生错误",e);
		}
		String tempResult=tempWriter.toString();
		return tempResult;
	}
	public static void main(String[] args) {
		TestModel testModel=new TestModel();
		testModel.setName("王波");
		List<TestModel> childs=new ArrayList<TestModel>();
		for(int i=0;i<10;i++){
			TestModel child=new TestModel();
			child.setName("name"+i);
			child.setEmail("email"+i);
			child.setSex("sex"+i);
			childs.add(child);
		}
		testModel.setChilds(childs);
		String content=FreeMarkerUtils.process("resources/wordtemplate/template.xml", testModel);
		System.out.println(content);
	}
}
