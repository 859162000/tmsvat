package com.deloitte.tms.pl.core.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;

public class XmlUtils {

	/**
	 * 从字符串中获取文档
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Document getDocument(String xml){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentBuilderFactory.newDocumentBuilder();
			return builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("xml写入失败:"+e.getMessage());
		}
	}
	/**
	 * 写xml
	 * @param document
	 * @param filePath 目标文件完整路径
	 * @throws Exception
	 */
	public static void write2Xml(Document document,String filePath){
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer();		
			transformer.transform(new DOMSource(document), new StreamResult(
					new FileOutputStream(filePath)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("xml写入失败:"+e.getMessage());
		}
	}
	public static Element getRootElement(Document document,String rootName){
		NodeList list =document.getElementsByTagName(rootName);
//		if(list.getLength()<1){
//			throw new BusinessException("没有找到对应根节点");
//		}else 
		if(list.getLength()>1){
			throw new BusinessException("根节点数量大于1");
		}else{
			return (Element) list.item(0);
		}
	}
	public static Element getChildElementByName(Element element,String rootName){
		NodeList list =element.getElementsByTagName(rootName);
//		if(list.getLength()<1){
//			throw new BusinessException("没有找到对应根节点");
//		}else 
		if(list.getLength()>1){
			throw new BusinessException("根节点数量大于1");
		}else{
			return (Element) list.item(0);
		}
	}
	public static NodeList getChildElementsByName(Element element,String rootName){
		NodeList list =element.getElementsByTagName(rootName);
		return list;
	}
}
