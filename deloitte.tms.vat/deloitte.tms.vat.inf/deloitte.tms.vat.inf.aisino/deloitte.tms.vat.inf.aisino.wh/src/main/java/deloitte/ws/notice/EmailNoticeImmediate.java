//package deloitte.ws.notice;
//
//import org.apache.log4j.Logger;
//import com.xzsoft.xip.wf.common.api.TaskNoticeService;
//public class EmailNoticeImmediate {
//
//	private TaskNoticeService taskNoticeService;
//	private static final Logger log = Logger.getLogger(EmailNoticeImmediate.class.getName());
//
//	public TaskNoticeService getTaskNoticeService() {
//		return taskNoticeService;
//	}
//
//	public void setTaskNoticeService(TaskNoticeService taskNoticeService) {
//		this.taskNoticeService = taskNoticeService;
//	}
//
//	public void Email4Notice(String paramJson){
//		try {
//			
//			StringBuffer sb=new StringBuffer();
//			sb.append("{");
//			sb.append("\"taskId:\"001\",");
//			sb.append("\"recUserId:\"009\",");
//			sb.append("\"recUserName:\"joyce zhang\",");
//			sb.append("\"taskTitle:\"test invoice ws\",");
//			sb.append("\"phoneNum:\"13628637708\",");
//			sb.append("\"email:\"jiji0716@163.com\",");
//			sb.append("\"webHost:\"www.baidu.com\",");
//			sb.append("\"submitter:\"Nicole wan\"}");
//			paramJson=sb.toString();
//			System.out.print(paramJson);
//			
//			taskNoticeService.notice4Email(paramJson);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//		
//	}
//}
