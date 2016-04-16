/**
 * @(#) IModelHistoryDAO.java
 */

package com.deloitte.tms.pl.core.dao;

import com.deloitte.tms.pl.core.model.impl.LingModelhistory;
import com.deloitte.tms.pl.core.model.impl.ModelHistoryParam;
import com.deloitte.tms.pl.core.model.impl.ModelHistoryResult;

import java.util.List;
import java.util.Map;
import java.util.Collection;

public interface IModelHistoryDAO extends IDao
{
	void initModelsStates( );

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

	/**
	 * @param entity
	 * @param state
	 */
	void SaveHistory( Object entity, String state );

	/**
	 * @param entities
	 * @param state
	 */
	void SaveHistoryAll( Collection entities, String state );



	/**
	 * @param ClassName
	 * @param state
	 * @return
	 */
	Boolean appendModelHistory( String ClassName, Boolean state );



	/**
	 * @param param
	 * @return
	 */
	ModelHistoryResult SaveHistory( ModelHistoryParam param );

	List<LingModelhistory> findModelhistories(Map params);

}
