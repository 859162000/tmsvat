package com.deloitte.tms.base.taxsetting.model;

import java.util.Date;

import javax.persistence.Column;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;

public class TmsMdFlexStructuresInParam extends TmsMdFlexStructures {
		private String flexSegmentsId;//数据结构段定义

		private String segmentNum;//段码

		private String segmentName;//段名

		private String segmentAttribute;//段属性

		private String flexValueSetId;//值集ID

		private String rangeCode;//范围代码(HIGH/LOW)

		private Boolean displayFlag;//是否显示

		private Boolean requiredFlag;//是否必须

		private String defaultType;//默认类型

		private String defaultValue;//默认值
		
		public String getFlexSegmentsId() {
			return flexSegmentsId;
		}

		public void setFlexSegmentsId(String flexSegmentsId) {
			this.flexSegmentsId = flexSegmentsId;
		}

		public String getSegmentNum() {
			return segmentNum;
		}

		public void setSegmentNum(String segmentNum) {
			this.segmentNum = segmentNum;
		}

		public String getSegmentName() {
			return segmentName;
		}

		public void setSegmentName(String segmentName) {
			this.segmentName = segmentName;
		}

		public String getSegmentAttribute() {
			return segmentAttribute;
		}

		public void setSegmentAttribute(String segmentAttribute) {
			this.segmentAttribute = segmentAttribute;
		}

		public String getFlexValueSetId() {
			return flexValueSetId;
		}

		public void setFlexValueSetId(String flexValueSetId) {
			this.flexValueSetId = flexValueSetId;
		}

		public String getRangeCode() {
			return rangeCode;
		}

		public void setRangeCode(String rangeCode) {
			this.rangeCode = rangeCode;
		}

		public Boolean getDisplayFlag() {
			return displayFlag;
		}

		public void setDisplayFlag(Boolean displayFlag) {
			this.displayFlag = displayFlag;
		}

		public Boolean getRequiredFlag() {
			return requiredFlag;
		}

		public void setRequiredFlag(Boolean requiredFlag) {
			this.requiredFlag = requiredFlag;
		}

		public String getDefaultType() {
			return defaultType;
		}

		public void setDefaultType(String defaultType) {
			this.defaultType = defaultType;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
}
