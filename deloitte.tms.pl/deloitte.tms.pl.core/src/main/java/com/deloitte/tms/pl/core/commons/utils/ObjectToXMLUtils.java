package com.deloitte.tms.pl.core.commons.utils;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 对象序列化到XML文件的方法
 * 
 * @author LuckyStar
 */
public class ObjectToXMLUtils {
	
	/**
	 * 文件流转换为对象
	 * @param fin
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public static Object objectXmlDecoder(InputStream fin)
			throws FileNotFoundException, IOException, Exception {
		{
			XMLDecoder decoder = new XMLDecoder(fin);
			Object obj = null;
			obj = decoder.readObject();
			fin.close();
			decoder.close();
			return obj;
		}
	}

	/**
	 * 对象序列化到文件
	 * @param obj
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public static void objectXmlEncoder(Object obj, String fileName)
			throws FileNotFoundException, IOException, Exception {

		File fo = new File(fileName);

//		if (!fo.exists()) {
//			String path = fileName.substring(0, fileName.lastIndexOf('.'));
//			File pFile = new File(path);
//			pFile.mkdirs();
//		}

		FileOutputStream fos = new FileOutputStream(fo);
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(obj);
		encoder.flush();
		encoder.close();
		fos.close();
	}

	/**
	 * 将POJO对象系列化转换成Xml格式字符串数据，以用于转换成Byte[]类型保存到数据库Blob字段。
	 * 
	 * @param  object POJO对象
	 * @return
	 * @author mqq
	 */
	public static String beanToXml(Object object) throws UnsupportedEncodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder xe = new XMLEncoder(new BufferedOutputStream(baos)); 
		xe.writeObject(object);
		xe.close();
		try{
		  return baos.toString("UTF-8");
		} catch (UnsupportedEncodingException uee){
		  throw uee;
		}
  }


	/**
	 * 将Xml格式字符串数据转换成POJO对象
	 * 
	 * @param  xmlData Xml格式字符串
	 * @return
	 * @author mqq
	 */
	public static Object xmlToBean(String xmlData) throws UnsupportedEncodingException {
		ByteArrayInputStream bais;
		try{
		  bais = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException uee){
		  bais = new ByteArrayInputStream(xmlData.getBytes());
		}
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(bais));
		Object result = d.readObject();
		d.close();
		return result;
  }
	public static String formatXML(String inputXML) throws Exception {  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(new StringReader(inputXML));  
	    String requestXML = null;  
	    XMLWriter writer = null;  
	    if (document != null) {  
	      try {  
	        StringWriter stringWriter = new StringWriter();  
	        OutputFormat format = new OutputFormat(" ", true);  
	        writer = new XMLWriter(stringWriter, format);  
	        writer.write(document);  
	        writer.flush();  
	        requestXML = stringWriter.getBuffer().toString();  
	      } finally {  
	        if (writer != null) {  
	          try {  
	            writer.close();  
	          } catch (IOException e) {  
	          }  
	        }  
	      }  
	    }  
	    return requestXML;  
	 } 

}
