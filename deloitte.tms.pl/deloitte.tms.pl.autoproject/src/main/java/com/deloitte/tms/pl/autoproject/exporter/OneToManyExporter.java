package com.deloitte.tms.pl.autoproject.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deloitte.tms.pl.autoproject.model.DefaultPojo;
import com.deloitte.tms.pl.autoproject.model.OneToManyPojo;
import com.deloitte.tms.pl.autoproject.model.POJOClass;
import com.deloitte.tms.pl.autoproject.utils.AutoProjectUtils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleDate;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;

public class OneToManyExporter implements Exporter{
	// 输出文件默认目录
	String rootdir_str;
	OneToManyPojo oneToManyPojo;
	String template_str;
	String result_str;
	String modeldir_str;
	String destination_str;
	File destination;
	String[] templatePaths={};
	ArtifactCollector ac ;
	static final Log log = LogFactory.getLog(OneToManyExporter.class);

	private OneToManyExporter() {

	}

	public OneToManyExporter(String baseDir, OneToManyPojo oneToManyPojo, String template,
			String result) {
		this.rootdir_str = baseDir;
		this.oneToManyPojo = oneToManyPojo;
		this.template_str = template;
		this.result_str = result;
		ac = new ArtifactCollector();
	}

	public void execute() {
		processRootDir();
		processModelFolder();
		processResultFolder();
		processResultFile();
		ac.formatFiles();
	}
	protected void processResultFile()
	{
		try {
			Template template = getFreeMarkerEngine(templatePaths).getTemplate(template_str);
			StringWriter tempWriter = new StringWriter();
			BufferedWriter bw = new BufferedWriter(tempWriter);
			Map rootMap = new HashMap();
			rootMap.put("oneToManyPojo", oneToManyPojo);
			rootMap.put("version", "ling2.autoproject");
			rootMap.put("date", new SimpleDate(new Date(), TemplateDateModel.DATETIME)); 
			template.process(rootMap, bw);
			try {
				bw.flush();
			}
			catch (IOException e) {
				throw new RuntimeException("Error while flushing to string",e);
			}
			String tempResult=tempWriter.toString();
			log.info(tempResult);
			
			
			if(tempResult.trim().length()==0) {
				log.warn("Generated output is empty. Skipped creation for file " + destination_str);
				return;
			}
			
			Writer fileWriter = null;
			try { 
				String fileType="java";
				ac.addFile(destination, fileType);
				log.debug("Writing " + template_str + " to " + destination.getAbsolutePath() );
//				fileWriter = new FileWriter(destination);
				fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination), "UTF-8"));
	            fileWriter.write(tempResult);			
			} 
			catch (Exception e) {
			    throw new ExporterException("Error while writing result to file", e);	
			} finally {
				if(fileWriter!=null) {
					try {
						fileWriter.flush();
						fileWriter.close();
					}
					catch (IOException e) {
						log.warn("Exception while flushing/closing " + destination,e);
					}				
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static POJOClass revertClass2Pojo(Class class1) {
		DefaultPojo result = new DefaultPojo(class1);
		return result;
	}

	protected void processRootDir() {
		rootdir_str=ensureFolderEnd(rootdir_str);
		log.info(rootdir_str);
	}

	protected void processModelFolder() {
		log.info(oneToManyPojo.getOne().getPackageName());
		String modelpackage = oneToManyPojo.getOne().getPackageName();
		String modelfolder = modelpackage.replace(".", "/");
		log.info(modelfolder);
		modeldir_str = rootdir_str + modelfolder;
		modeldir_str = modeldir_str.replace("/model", "");
		modeldir_str=ensureFolderEnd(modeldir_str);
		log.info(modeldir_str);
	}
	protected void processResultFolder()
	{
		destination_str = modeldir_str + result_str;
		log.info(destination_str);
		destination = new File(destination_str);		
		ensureExistence(destination);
	}
	protected void ensureExistence(File destination) {
		// if the directory exists, make sure it is a directory
		File dir = destination.getAbsoluteFile().getParentFile();
		if (dir.exists() && !dir.isDirectory()) {
			throw new ExporterException("The path: " + dir.getAbsolutePath()
					+ " exists, but is not a directory");
		} // else make the directory and any non-existent parent directories
		else if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new ExporterException("unable to create directory: "
						+ dir.getAbsolutePath());
			}
		}
	}
	private String ensureFolderEnd(String folder) {
		if (!folder.endsWith("/")) {
			folder = folder + "/";
		}
		return folder;
	}
	private static Configuration getFreeMarkerEngine(String[] templatePaths) {
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
					throw new ExporterException("Problems with templatepath " + file, e);
				}
        	} else {
        		log.warn("template path" + file + " either does not exist or is not a directory");
        	}
		}
        loaders.add(new ClassTemplateLoader(AutoProjectUtils.class,"/")); // the template names are like pojo/Somewhere so have to be a rooted classpathloader
        
        freeMarkerEngine.setTemplateLoader(new MultiTemplateLoader((TemplateLoader[]) loaders.toArray(new TemplateLoader[loaders.size()])));
        return freeMarkerEngine;
	}
}
