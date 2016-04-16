package com.deloitte.tms.pl.flow.utils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.flow.dao.FlowDao;
import com.deloitte.tms.pl.flow.model.Flow;


public class FlowHelper {

	private static final long MAX_VALUE = 35353535L;
	
	private static final int MAX_LENGTH = 36;
	
	private static final char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	public static final int DEFAULT_FLOW_NO = 1;
	
	static FlowDao flowDao;
	
	private static FlowDao getFlowDao() {
		if(flowDao==null){
			flowDao=SpringUtil.getBean(FlowDao.BEAN_ID);
		}
		return flowDao;
	}
	
	private static Flow getFlow(String type){
		Flow flow=getFlowDao().getFlowByOrgId(null, type);
		if(flow==null){
			throw new BusinessException("流水号没有定义");
		}
		return flow;
	}
	
	public static String getNextFlowNo(String type){
		Flow flow=getFlow(type);
		String nextFlowNo=getNextFlowNo(flow);
		getFlowDao().update(flow);
		return nextFlowNo;
	}
	public static String getNextFlowNo(Flow flow){
		Integer maxLength=flow.getMaxLength();
		if(maxLength==null){
			maxLength=MAX_LENGTH;
		}
		long flowNo = flow.getFlowNo()==null?0:flow.getFlowNo().longValue();
		if (flowNo >= MAX_VALUE) {
			throw new RuntimeException("流水号已经用完!");
		}
		String nextFlowNo=getNextFlowNo(flow,maxLength, flowNo);
		if(flow.getPreName()!=null){
			nextFlowNo=flow.getPreName()+nextFlowNo;
		}
		return nextFlowNo;
	}
	/**
	 * 获取下一个流水号，流水号的范围是0000 ~ ZZZZ(35 35 35 35)，传递的参数flow的值(flowNo)会被更改
	 * 如果要缩小流水号范围，只需要更改MAX_VALUE的值，比如从0000 ~ Z999 则MAX_VALUE = 35 9 9 9
	 * 
	 * @param flow
	 *            根据该对象的flowNo值计算下一个flowNo，并更新该值()
	 * @return 返回下一个flowNo对应的字符代码
	 */
	private static String getNextFlowNo(Flow flow,Integer maxLength,long flowNo) {	
		
		//渠道人员代码是6位流水号
		int[] flowNoArray = new int[maxLength];
		String strFlowNo = String.valueOf(flowNo);
		int length = strFlowNo.length();

		if (length < maxLength) {
			for (int i = 0; i < (maxLength - length); i++) {
				strFlowNo = "0" + strFlowNo;
			}
			length = strFlowNo.length();
		}

		int start = 0;
		int count = 0;
		while ((length - start) > (maxLength - count)) {
			String strNo = strFlowNo.substring(start, start + 2);
			int value = Integer.parseInt(strNo);

			flowNoArray[count] = value;
			start += 2;
			count++;
		}

		if (start < length) {
			int len = length - start;
			int splitCount = count > 0 ? count - 1 : 0;
			String compareFlowNo = strFlowNo.substring(start);
			int number = Integer.parseInt(compareFlowNo); // 不包含字母部分
			int compareNumber = (int) Math.pow(10, len);

			for (int i = start; i < length; i++) {
				flowNoArray[count++] = Integer.parseInt(strFlowNo.substring(i, i + 1));
			}

			number++;
			if (number >= compareNumber) {
				// 表明产生了进位
				flowNoArray[splitCount] += 1; // 加1

				for (int i = splitCount; i < maxLength - 1; i++) {
					if (flowNoArray[i] > 35) {
						flowNoArray[i] = 35;
						flowNoArray[i + 1]++;
						splitCount++;
					} else {
						break;
					}
				}

				for (int i = splitCount + 1; i < maxLength; i++) {
					flowNoArray[i] = 0;
				}
			} else {
				String strNumber = String.valueOf(number);
				int numLen = strNumber.length();
				if (numLen < len) {
					for (int i = 0; i < len - numLen; i++) {
						strNumber = "0" + strNumber;
					}
				}
				splitCount = maxLength - strNumber.length();
				for (int i = 0; i < strNumber.length(); i++) {
					flowNoArray[splitCount++] = Integer.parseInt(strNumber.substring(i, i + 1));
				}
			}
		} else {
			flowNoArray[maxLength - 1]++;
		}

		StringBuffer codeBuffer = new StringBuffer();
		StringBuffer numberBuffer = new StringBuffer();
		for (int i = 0; i < flowNoArray.length; i++) {
			if (flowNoArray[i] >= 10) {
				codeBuffer.append(convert(flowNoArray[i]));
			} else {
				codeBuffer.append(flowNoArray[i]);
			}
			numberBuffer.append(flowNoArray[i]);
		}

		flow.setFlowNo(new Long(Long.parseLong(numberBuffer.toString())));
		return codeBuffer.toString();
	}
	/**
	 * 将数字转换成字母
	 * 
	 * @param strNo
	 * @return
	 */
	private static char convert(int flowNo) {
		int count = flowNo;
		count -= 10;
		return letters[count];
	}
	public static void main(String[] args) {
		Flow flow=new Flow();
		flow.setFlowNo(100l);
		String result=FlowHelper.getNextFlowNo(flow);
		System.out.println(result);
	}
}
