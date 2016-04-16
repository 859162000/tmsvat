package com.deloitte.tms.pl.core.commons.springmvc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class ParamsToMapMessageConverter implements HttpMessageConverter<Map<String,String>> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return Map.class.isAssignableFrom(clazz);
    }
 
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return Map.class.isAssignableFrom(clazz);
    }
 
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supports = new ArrayList();
        supports.add(MediaType.APPLICATION_FORM_URLENCODED);
        return supports;
    }
 
    @Override
    public Map<String, String> read(Class<? extends Map<String, String>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String params = IOUtils.toString(inputMessage.getBody(), "utf-8");
        return paramsToMap(params);
    }
 
    @Override
    public void write(Map<String, String> stringObjectMap, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(stringObjectMap.toString().getBytes());
    }
    /**
     * Convert http request params, such as "name=tom&age=20"
     * generally speaking,request body should be decode after convert complete.
     *
     * @param params request params
     * @return map
     */
    public static Map<String, String> paramsToMap(String params) {
        Map<String, String> map = new LinkedHashMap();
        if (params != null && !params.equals("")) {
            String[] array = params.split("&");
            for (String pair : array) {
                if ("=".equals(pair.trim())) {
                    continue;
                }
                String[] entity = pair.split("=");
                if (entity.length == 1) {
                    map.put(decode(entity[0]), null);
                } else {
                    map.put(decode(entity[0]), decode(entity[1]));
                }
            }
        }
        return map;
    }
    public static String decode(String content) {
        try {
            return URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}