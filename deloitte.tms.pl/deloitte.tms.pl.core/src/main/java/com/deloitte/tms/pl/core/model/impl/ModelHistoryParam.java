/**
 * @(#) ModelHistoryParam.java
 */

package com.deloitte.tms.pl.core.model.impl;

import java.io.Serializable;

public class ModelHistoryParam
{
	protected String message;
	
	protected Object entity;
	
	protected String state;
	
	protected String operatorName;
	protected Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = (Long) id;
	}

	/**
	 * @param message
	 */
	public void setMessage( String message )
	{
		this.message=message;
	}
	
	/**
	 * @return 
	 */
	public String getMessage( )
	{
		return message;
	}
	
	/**
	 * @param entity
	 */
	public void setEntity( Object entity )
	{
		this.entity=entity;
	}
	
	/**
	 * @return 
	 */
	public Object getEntity( )
	{
		return entity;
	}
	
	/**
	 * @param state
	 */
	public void setState( String state )
	{
		this.state=state;
	}
	
	/**
	 * @return 
	 */
	public String getState( )
	{
		return state;
	}
	
	/**
	 * @param operatorName
	 */
	public void setOperatorName( String operatorName )
	{
		this.operatorName=operatorName;
	}
	
	/**
	 * @return 
	 */
	public String getOperatorName( )
	{
		return operatorName;
	}
	
	
}
