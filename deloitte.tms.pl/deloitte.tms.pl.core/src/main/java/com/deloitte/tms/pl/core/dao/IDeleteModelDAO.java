/**
 * @(#) IDeleteModelDAO.java
 */

package com.deloitte.tms.pl.core.dao;

import java.util.Map;

public interface IDeleteModelDAO
{
	/**
	 * @return 
	 */
	Map<String,Boolean> initModelsStates( );
	
	void refreshModelStates( );
	
	/**
	 * @return 
	 */
	Map<String,Boolean> getModelsStates( );
	
	/**
	 * @param modelsStates
	 */
	void setModelsStates( Map<String,Boolean> modelsStates );
	
	/**
	 * @param className
	 * @return 
	 */
	Boolean getModelsStateByClassName( String className );
}
