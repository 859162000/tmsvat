package com.deloitte.tms.pl.autoproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deloitte.tms.pl.autoproject.exporter.AutoProjectExporter;
import com.deloitte.tms.pl.autoproject.exporter.Exporter;
import com.deloitte.tms.pl.autoproject.exporter.MVCExporter;
import com.deloitte.tms.pl.autoproject.exporter.OneAndRelationExporter;
import com.deloitte.tms.pl.autoproject.exporter.OneToManyExporter;
import com.deloitte.tms.pl.autoproject.exporter.TableDesToClassExporter;
import com.deloitte.tms.pl.autoproject.model.DefaultPojo;
import com.deloitte.tms.pl.autoproject.model.OneToManyPojo;
import com.deloitte.tms.pl.autoproject.model.POJOClass;
import com.deloitte.tms.pl.autoproject.model.TestModelMany;
import com.deloitte.tms.pl.autoproject.model.TestModelOne;
import com.deloitte.tms.pl.autoproject.model.VOPojo;
import com.deloitte.tms.pl.autoproject.model.tabledes.TableDes;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;

public class AutoProjectUtils {
	
	static final Log log = LogFactory.getLog(AutoProjectUtils.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resultfolder="D:/workspace/sources/ling2/ling2.autoproject/result";
//		AutoProjectUtils.execute(resultfolder,TestModel.class);
		
		OneToManyPojo oneToManyPojo=new OneToManyPojo();
		oneToManyPojo.setOne(revertClass2Pojo(TestModelOne.class));
		oneToManyPojo.addMany(revertClass2Pojo(TestModelMany.class));
//		executeOneToMany(resultfolder,oneToManyPojo);
	}
	/**
	 * d7 
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeIrIsOneToComplex(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/model/Param.ftl", "model/"+oneToManyPojo.getOne().getShortName()+"Param.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/dao/Dao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/dao/impl/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/service/Service.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/service/impl/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/java.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/js.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/view.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/i18n.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.properties"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/i18n.zh_CN.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.zh_CN.properties"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/select_single.ftl", "view/component/"+oneToManyPojo.getOne().getShortName()+"SingleSelect.view.xml"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "iris/view/core.ftl", "view/component/"+oneToManyPojo.getOne().getShortName()+"xml.txt"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * iris define
	 * @param resultfolder
	 * @param class1
	 */
	public static void executeIrisDefine(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/model/Param.ftl", "model/"+oneToManyPojo.getOne().getShortName()+"Param.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/model/Node.ftl", "model/Iris"+oneToManyPojo.getOne().getShortName()+"Node.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/dao/Dao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/dao/impl/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/service/Service.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/service/impl/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/service/impl/Provider.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"Provider.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/java.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/js.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/view.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/i18n.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.properties"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/i18n.zh_CN.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Manager.zh_CN.properties"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/select_single.ftl", "view/component/"+oneToManyPojo.getOne().getShortName()+"SingleSelect.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "irisdefine/view/core.ftl", "view/component/"+oneToManyPojo.getOne().getShortName()+"xml.txt"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d5
	 * @param resultfolder
	 * @param class1
	 */
	public static void execute(String resultfolder,Class class1)
	{
		List<AutoProjectExporter> exporters=new ArrayList<AutoProjectExporter>();
//		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/IDao.ftl", "dao/"+class1.getSimpleName()+"Dao.java"));
//		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/DaoImpl.ftl", "dao/impl/"+class1.getSimpleName()+"DaoImpl.java"));
//		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/IService.ftl", "service/"+class1.getSimpleName()+"Service.java"));
//		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/ServiceImpl.ftl", "service/impl/"+class1.getSimpleName()+"ServiceImpl.java"));
//		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/View.ftl", "view/"+class1.getSimpleName()+"View.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/ViewTemplate.ftl", "view/"+class1.getSimpleName()+"Manager.view.xml"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "template/ViewJsp.ftl", "view/"+class1.getSimpleName()+"Manager.jsp"));
		for(AutoProjectExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d5
	 * @param resultfolder
	 * @param class1
	 */
	public static void executeJYKL(String resultfolder,Class class1)
	{
		List<AutoProjectExporter> exporters=new ArrayList<AutoProjectExporter>();
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/IDao.ftl", "dao/"+class1.getSimpleName()+"Dao.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/DaoImpl.ftl", "dao/impl/"+class1.getSimpleName()+"DaoImpl.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/IService.ftl", "service/"+class1.getSimpleName()+"Service.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ServiceImpl.ftl", "service/impl/"+class1.getSimpleName()+"ServiceImpl.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/View.ftl", "view/"+class1.getSimpleName()+"View.java"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ViewTemplate.ftl", "view/"+class1.getSimpleName()+"Manager.view.xml"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ViewJsp.ftl", "view/"+class1.getSimpleName()+"Manager.jsp"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ImportView.ftl", "view/"+class1.getSimpleName()+"Import.view.xml"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ImportJsp.ftl", "view/"+class1.getSimpleName()+"Import.jsp"));
		
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/hbm.ftl", "view/"+class1.getSimpleName()+".hbm.xml"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/ImportXml.ftl", "view/"+class1.getSimpleName()+"Import.xml"));
		exporters.add(new AutoProjectExporter(resultfolder, class1, "jykl/config.ftl", "view/"+class1.getSimpleName()+"config.txt"));
		
		for(AutoProjectExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeOneToMany(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager_one.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager_one.js"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7 
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeOneToManyMultipleFile(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<Exporter> exporters=new ArrayList<Exporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/InParam.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"InParam.java"));	
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		//其他inparam手工产生吧
//		for(POJOClass pojoClass:oneToManyPojo.getMany()){
//			
//		}
		//处理关系
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "multiple/main_relation.ftl","_relation.view.xml"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "multiple/main_relation_javascript.ftl","_relation.js"));
		
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_query_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_query_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_add_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_add_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_modify_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_modify.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/View_modify_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_modify.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager_one.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager_one.js"));
		for(Exporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	public static void executeOneToManyRelationFile(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<Exporter> exporters=new ArrayList<Exporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java_txt"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "multiple/InParam.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"InParam.java_txt"));	
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java_txt"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "multiple/main_relation.ftl","_relation.view.xml"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "multiple/main_relation_javascript.ftl","_relation.js"));
		for(Exporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	public static void executeOneToManyCpicRelationFile(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<Exporter> exporters=new ArrayList<Exporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java_txt"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java_txt"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/InParam.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"InParam.java_txt"));	
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java_txt"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "cpic/main_relation.ftl","_relation.view.xml"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "cpic/main_relation_javascript.ftl","_relation.js"));
		for(Exporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7 
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeOneToManyCpicMultipleFile(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<Exporter> exporters=new ArrayList<Exporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
//		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/InParam.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"InParam.java"));	
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		//其他inparam手工产生吧
//		for(POJOClass pojoClass:oneToManyPojo.getMany()){
//			
//		}
		//处理关系
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "cpic/main_relation.ftl","_relation.view.xml"));
		exporters.add(new OneAndRelationExporter(resultfolder, oneToManyPojo, "cpic/main_relation_javascript.ftl","_relation.js"));
		
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_query_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_query_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_add_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_add_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.js"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_modify_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_modify.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "cpic/View_modify_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_modify.js"));
		for(Exporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7 
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeOneToComplex(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"View.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"manager.js"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeSelect(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_select_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"select.view.xml"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * d7
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeaddview(String resultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_add_d7.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.view.xml"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/View_add_d7_javascript.ftl", "view/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+"_add.js"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	public static void executeSelfTree(String resultfolder,Class selftree)
	{
		List<AutoProjectExporter> exporters=new ArrayList<AutoProjectExporter>();
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/IDao.ftl", "dao/"+selftree.getSimpleName()+"Dao.java"));
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/DaoImpl.ftl", "dao/impl/"+selftree.getSimpleName()+"DaoImpl.java"));
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/IService.ftl", "service/"+selftree.getSimpleName()+"Service.java"));
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/ServiceImpl.ftl", "service/impl/"+selftree.getSimpleName()+"ServiceImpl.java"));
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/View.ftl", "view/"+selftree.getSimpleName()+"View.java"));
		exporters.add(new AutoProjectExporter(resultfolder, selftree, "selftree/View_d7.ftl", "view/"+StringUtils.toFirstLetterLowerCase(selftree.getSimpleName())+"manager.view.xml"));

		for(AutoProjectExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * UIGrid
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeMvcUIGrid(String resultfolder,String viewresultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "bootstrape/controller.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Controller.java"));
		exporters.add(new MVCExporter(viewresultfolder, oneToManyPojo, "uigrid/js.ftl", "js/"+oneToManyPojo.getOne().getShortName()+"Controller.js"));
		exporters.add(new MVCExporter(viewresultfolder, oneToManyPojo, "uigrid/html.ftl", "views/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+".html"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * UIGrid
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeMvcBootstrape(String resultfolder,String viewresultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder, oneToManyPojo, "bootstrape/controller.ftl", "view/"+oneToManyPojo.getOne().getShortName()+"Controller.java"));
		exporters.add(new MVCExporter(viewresultfolder, oneToManyPojo, "bootstrape/js.ftl", "js/"+oneToManyPojo.getOne().getShortName()+"Controller.js"));
		exporters.add(new MVCExporter(viewresultfolder, oneToManyPojo, "bootstrape/html.ftl", "views/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+".html"));
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	/**
	 * UIGrid
	 * @param resultfolder
	 * @param oneToManyPojo
	 */
	public static void executeMvcJsp(String resultfolder,String viewresultfolder,OneToManyPojo oneToManyPojo)
	{
		List<OneToManyExporter> exporters=new ArrayList<OneToManyExporter>();
		exporters.add(new OneToManyExporter(resultfolder+"\\src\\main\\java\\", oneToManyPojo, "onetomany/IDao.ftl", "dao/"+oneToManyPojo.getOne().getShortName()+"Dao.java"));
		exporters.add(new OneToManyExporter(resultfolder+"\\src\\main\\java\\", oneToManyPojo, "onetomany/DaoImpl.ftl", "dao/impl/"+oneToManyPojo.getOne().getShortName()+"DaoImpl.java"));
		exporters.add(new OneToManyExporter(resultfolder+"\\src\\main\\java\\", oneToManyPojo, "onetomany/IService.ftl", "service/"+oneToManyPojo.getOne().getShortName()+"Service.java"));
		exporters.add(new OneToManyExporter(resultfolder+"\\src\\main\\java\\", oneToManyPojo, "onetomany/ServiceImpl.ftl", "service/impl/"+oneToManyPojo.getOne().getShortName()+"ServiceImpl.java"));
		exporters.add(new OneToManyExporter(viewresultfolder+"\\src\\main\\java\\", oneToManyPojo, "bootstrape/controller.ftl", "controller/"+oneToManyPojo.getOne().getShortName()+"Controller.java"));
		exporters.add(new MVCExporter(viewresultfolder+"\\src\\main\\webapp\\WEB-INF\\", oneToManyPojo, "bootstrape/jsp.ftl", "views/vat/"+oneToManyPojo.getOne().getShortName().toLowerCase()+"/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+".jsp"));
		exporters.add(new MVCExporter(viewresultfolder+"\\src\\main\\webapp\\WEB-INF\\", oneToManyPojo, "bootstrape/i18n.ftl", "views/vat/"+oneToManyPojo.getOne().getShortName().toLowerCase()+"/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+".properties"));
		exporters.add(new MVCExporter(viewresultfolder+"\\src\\main\\webapp\\WEB-INF\\", oneToManyPojo, "bootstrape/i18n.zh_CN.ftl", "views/vat/"+oneToManyPojo.getOne().getShortName().toLowerCase()+"/"+oneToManyPojo.getOne().getDeclarationNameFirstLetterLower()+".zh_CN.properties"));
		
		for(OneToManyExporter exporter:exporters)
		{
			exporter.execute();
		}
	}
	public static void executeTableDesToClass(String resultfolder,TableDes table)
	{
		TableDesToClassExporter exporter=new TableDesToClassExporter(resultfolder, table, "tabledestoclass/riskmanager.ftl", table.getName()+".java");
		exporter.execute();
	}
	public static POJOClass revertClass2Pojo(Class defClass) {	
		DefaultPojo result = new DefaultPojo(defClass);
		return result;
	}
	public static POJOClass revertClass2VoPojo(Class defClass) {		
		VOPojo result = new VOPojo(defClass);
		return result;
	}
}
