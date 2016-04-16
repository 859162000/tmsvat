package com.ling2.message.autoproject;

import com.deloitte.tms.pl.autoproject.model.OneToManyPojo;
import com.deloitte.tms.pl.autoproject.utils.AutoProjectUtils;
import com.deloitte.tms.pl.message.template.model.MessageParameter;
import com.deloitte.tms.pl.message.template.model.MessageTemplate;
import com.deloitte.tms.pl.system.model.ByteContent;

public class MessageAutoProject {

	public static void main(String[] args) {
		String resultfolder="D:\\workspace\\source\\ling3\\ling3.message\\src\\main\\java";	
		genMessage(resultfolder);
	}
	private static void genMessage(String resultfolder) {
		OneToManyPojo oneToManyPojo=new OneToManyPojo();
		oneToManyPojo.setOne(AutoProjectUtils.revertClass2Pojo(MessageTemplate.class));
		oneToManyPojo.addMany(MessageParameter.class);
		oneToManyPojo.addOne(ByteContent.class);
		AutoProjectUtils.executeOneToComplex(resultfolder,oneToManyPojo );
	}
}
