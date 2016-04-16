/**
 * @(#) ResultBase.java
 */

package com.deloitte.tms.pl.core.commons;


public abstract class ResultBase
{
	protected boolean isException;
	
	public boolean isException() {
		return isException;
	}
	public void setException(boolean isException) {
		this.isException = isException;
	}
	
	
}
