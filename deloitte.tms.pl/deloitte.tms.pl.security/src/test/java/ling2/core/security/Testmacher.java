//package ling2.core.security;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Testmacher {
//	 public static void main(String[] args) {
//		 String meString="access_token=BD3C4A2944029907DCB8215D316B796F&expires_in=7776000&refresh_token=D1A07A000C1EA229EAB1DA2837EBBC0D";
//		 String access_token="";
//		 Matcher m1 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(responsetoken);
//	      if (m.find()) {
//	    	  access_token = m1.group(1);
//	      } else {
//	        Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(responsetoken);
//	        if (m2.find()) {
//	        	access_token = m2.group(1);
//	        }
//	      }
//	      sys
//	 }
//}
