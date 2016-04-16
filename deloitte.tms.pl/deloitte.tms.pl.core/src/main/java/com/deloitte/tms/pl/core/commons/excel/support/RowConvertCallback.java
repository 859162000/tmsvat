package com.deloitte.tms.pl.core.commons.excel.support;

import jxl.Cell;

public interface RowConvertCallback {

	public Object convert(Cell[] cells);
}
