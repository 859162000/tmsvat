package com.deloitte.tms.pl.autoproject.exporter;

import java.io.File;

import com.deloitte.tms.pl.autoproject.model.OneToManyPojo;

public class MVCExporter extends OneToManyExporter{
	public MVCExporter(String baseDir, OneToManyPojo oneToManyPojo,
			String template, String result) {
		super(baseDir, oneToManyPojo, template, result);
	}

	protected void processResultFolder()
	{
		destination_str = rootdir_str+result_str;
		log.info(destination_str);
		destination = new File(destination_str);		
		ensureExistence(destination);
	}
}
