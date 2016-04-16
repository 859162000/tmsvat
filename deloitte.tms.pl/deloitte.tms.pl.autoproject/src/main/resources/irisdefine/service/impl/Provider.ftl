<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_firstLower = pojo.getDeclarationNameFirstLetterLower()>
package ${pojo.getPackageName()}.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.iris.define.cache.IrisBusinessContext;
import ${pojo.getPackageName()}.model.Iris${declarationName}Node;
import com.deloitte.iris.define.cache.model.IrisNode;
import com.deloitte.iris.define.cache.service.IrisProvider;
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
import ${pojo.getPackageName()}.model.${declarationName};
import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.reflect.ReflectUtils;

@Component(${declarationName}Provider.BEAN_ID)
public class ${declarationName}Provider implements IrisProvider{
	public static final String BEAN_ID="iris.${declarationName_firstLower}Provider";

	@Resource
	${declarationName}Dao ${declarationName_firstLower}Dao;
	@Override
	public IrisNode build(IrisBusinessContext context) throws BusinessException {
		List<${declarationName}> ${declarationName_firstLower}s=${declarationName_firstLower}Dao.findAll${declarationName}s();
		Map<String, Collection<${declarationName}>> subordinateRelations = new HashMap<String, Collection<${declarationName}>>();
		//上级id为null的一级数据放到root下
		Iris${declarationName}Node root = assembleRoot();
		for (${declarationName} ${declarationName_firstLower} : ${declarationName_firstLower}s) {
			${declarationName} default${declarationName}=(${declarationName})${declarationName_firstLower};
			String superiorId = default${declarationName}.getParentId();
			//根据上級ID把下属的机构都归在一个机构下
			if (subordinateRelations.keySet().contains(superiorId)) {
				Collection<${declarationName}> subordinates = subordinateRelations.get(superiorId);
				subordinates.add(default${declarationName});
			} else {
				Collection<${declarationName}> subordinates = new ArrayList<${declarationName}>();
				subordinates.add(default${declarationName});
				subordinateRelations.put(superiorId, subordinates);
			}			
		}
		
		//构建机构树,此时构建的就是一棵以当前考核机构为根，向下伸展的机构树
		//包含了当前考核机构和下属所有分支机构的树，分支和分支之间保留了上下级关系。
		assembleTree(root, subordinateRelations);
		return root;
	}
	protected void assembleTree(Iris${declarationName}Node superior, Map<String, Collection<${declarationName}>> subordinateRelations) {
		String superiorId = superior.getId();
		Collection<${declarationName}> subordinates = subordinateRelations.get(superiorId);

		if (subordinates == null || subordinates.isEmpty()) {
			return;
		}
		
		for (${declarationName} default${declarationName} : subordinates) {
			Iris${declarationName}Node subordinate = assemble(default${declarationName});
			superior.add(subordinate);
			assembleTree(subordinate, subordinateRelations);
		}
	}
	
	protected Iris${declarationName}Node assemble(${declarationName} ${declarationName_firstLower}) {
		Iris${declarationName}Node node=new Iris${declarationName}Node(${declarationName_firstLower}.getId(),${declarationName_firstLower}.getName());
		ReflectUtils.copyProperties(${declarationName_firstLower}, node);
		return node;
	}
	protected Iris${declarationName}Node assembleRoot() {
		Iris${declarationName}Node node=new Iris${declarationName}Node(null,"Root");
		return node;
	}
}
