package com.deloitte.tms.pl.core.service.impl;
//package com.ling2.core.service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Stack;
//
//import org.marmot.view.DataSet;
//
//import com.bstek.dorado.data.Record;
//import com.ling2.core.commons.exception.BusinessException;
//import com.ling2.core.dao.IDao;
//import com.ling2.core.model.ITreeNode;
//
//public abstract class BaseTreeNodeServiceImpl extends BaseService implements
//		ITreeNodeService {
//	public IDao getDao() {
//		return getTreeNodeDao();
//	}
//
//	public List loadTreeNodeForTree(Long parentid) {
//		return getTreeNodeDao().loadTreeNodeForTree(parentid);
//	}
//
//	public void saveTreeNodes(Collection<ITreeNode> treeNodes) {
//		int state;
//		for (ITreeNode treeNode : treeNodes) {
//			Record record=(Record)treeNode;
//			state=record.getState();
//			if (state==DataSet.NEW) {
//				addTreeNode(treeNode);
//			} else if (state==DataSet.MODIFIED) {
//				modifyTreeNode(treeNode);
//			} else if (state==DataSet.DELETED) {
//				removeTreeNode(treeNode);
//			}
//			if (treeNode.getChilds() != null) {
//				this.saveTreeNodes(treeNode.getChilds());
//			}
//			afterTreeNodeOperation(treeNode);
//		}
//	}
//	public void addTreeNode(ITreeNode treeNode)
//	{
//		beforeSaveTreeNode(treeNode);
//		onSaveTreeNode(treeNode);
//		afterSaveTreeNode(treeNode);
//	}
//	public void modifyTreeNode(ITreeNode treeNode)
//	{
//		beforeUpdateTreeNode(treeNode);
//		onUpdateTreeNode(treeNode);
//		afterUpdateTreeNode(treeNode);
//	}
//	public void removeTreeNode(ITreeNode treeNode)
//	{
//		beforeRemoveTreeNode(treeNode);
//		onRemoveTreeNode(treeNode);
//		afterRemoveTreeNode(treeNode);
//	}
//	public String buildTreeSeq(ITreeNode treeNode) {
//		Stack<ITreeNode> stack = getTreeNodeStack(treeNode);
//		int orglevel = stack.size();
//		StringBuffer orgseq = new StringBuffer();
//		ITreeNode nodetemp;
//		for (int i = 0; i < orglevel; i++) {
//			nodetemp = stack.pop();
//			orgseq.append(nodetemp.getId() + ".");
//		}
//		treeNode.setDatalevel(orglevel);
//		return orgseq.toString();
//	}
//
//	public void beforeSaveTreeNode(ITreeNode treeNode) {
//	}
//
//	public void onSaveTreeNode(ITreeNode treeNode) {
//		getTreeNodeDao().save(treeNode);
//	}
//
//	public void afterSaveTreeNode(ITreeNode treeNode) {
//		treeNode.setDataseq(buildTreeSeq(treeNode));
//		getTreeNodeDao().update(treeNode);
//	}
//
//	public void beforeUpdateTreeNode(ITreeNode treeNode) {
//	}
//
//	public void onUpdateTreeNode(ITreeNode treeNode) {
//		getTreeNodeDao().update(treeNode);
//	}
//
//	public void afterUpdateTreeNode(ITreeNode treeNode) {
//		treeNode.setDataseq(buildTreeSeq(treeNode));
//		// 更新父节点后完全重构path
//		getTreeNodeDao().update(treeNode);
//	}
//
//	public void beforeRemoveTreeNode(ITreeNode treeNode) {
//	}
//
//	public void onRemoveTreeNode(ITreeNode treeNode) {
//		List childs = loadTreeNodeForTree(treeNode.getId());
//		if (childs.size() > 0) {
//			throw new BusinessException("请先删除下级数据");
//		}
//		getTreeNodeDao().remove(treeNode);
//	}
//
//	public void afterRemoveTreeNode(ITreeNode treeNode) {
//	}
//	public void afterTreeNodeOperation(ITreeNode treeNode) {
//	}
//	public ITreeNode findTreeNodebyId(Long id) {
//		return getTreeNodeDao().findTreeNodebyId(id);
//	}
//	public ITreeNode findTreeNodeParentbyChildId(Long id)
//	{
//		return getTreeNodeDao().findTreeNodeParentbyChildId(id);
//	}
//	public Stack<ITreeNode> getTreeNodeStack(ITreeNode treeNode) {
//		Stack<ITreeNode> stack = new Stack<ITreeNode>();
//		if (treeNode != null) {
//			stack.add(treeNode);
//			Long parentid = treeNode.getParentid();
//			if (parentid != null) {
//				ITreeNode treeNodetemp = findTreeNodebyId(parentid);
//				while (treeNodetemp != null) {
//					stack.add(treeNodetemp);
//					treeNodetemp =findTreeNodeParentbyChildId(treeNodetemp.getId()) ;
//				}
//			}
//		}
//		return stack;
//	}
//
//}
