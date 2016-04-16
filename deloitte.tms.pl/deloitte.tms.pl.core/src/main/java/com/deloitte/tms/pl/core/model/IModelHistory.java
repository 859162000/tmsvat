/**
 * @(#) IModelHistory.java
 */

package com.deloitte.tms.pl.core.model;

import java.io.Serializable;
import java.util.Date;

public interface IModelHistory
{
	/**
	 * @param operatorDate
	 */
	void setOperatorDate( Date operatorDate );
	
	/**
	 * @return 
	 */
	String getOperatorTable( );
	
	/**
	 * @param operatorTable
	 */
	void setOperatorTable( String operatorTable );
	
	/**
	 * @return 
	 */
	String getOperatorMessage( );
	
	/**
	 * @param operatorMessage
	 */
	void setOperatorMessage( String operatorMessage );
	
	/**
	 * @return 
	 */
	String getOperatorState( );
	
	/**
	 * @param modelOperation
	 */
	void setOperatorState( String modelOperation );
	
	/**
	 * @return 
	 */
	Long getId( );
	
	/**
	 * @param id
	 */
	void setId( Long id );
	
	/**
	 * @param operatorName
	 */
	void setOperatorName( String operatorName );
	
	/**
	 * @return 
	 */
	Date getOperatorDate( );
	
	/**
	 * @return 
	 */
	String getOperatorName( );
	
	public Serializable getOperatorKey();

	public void setOperatorKey(Serializable operatorKey);
}
