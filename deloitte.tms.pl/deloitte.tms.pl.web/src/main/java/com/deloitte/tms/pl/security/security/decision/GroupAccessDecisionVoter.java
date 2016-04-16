package com.deloitte.tms.pl.security.security.decision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityGroup;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.attribute.AttributeType;
import com.deloitte.tms.pl.security.security.attribute.SecurityConfigAttribute;

/**
 * @since 2013-1-25
 * @author Jacky.gao
 */
public class GroupAccessDecisionVoter extends AbstractAccessDecisionVoter {
	@Override
	protected AttributeType getAttributeType() {
		return AttributeType.group;
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		SecurityUser loginUser=(SecurityUser)authentication.getPrincipal();
		int voteResult=voteGroups(loginUser,attributes,GroupVoteType.user);
		if(voteResult!=ACCESS_ABSTAIN){
			return voteResult;
		}
		voteResult=voteGroups(loginUser,attributes,GroupVoteType.dept);
		if(voteResult!=ACCESS_ABSTAIN){
			return voteResult;
		}
		voteResult=voteGroups(loginUser,attributes,GroupVoteType.position);
		if(voteResult!=ACCESS_ABSTAIN){
			return voteResult;
		}
		voteResult=voteGroups(loginUser,attributes,GroupVoteType.group);
		if(voteResult!=ACCESS_ABSTAIN){
			return voteResult;
		}
		return ACCESS_ABSTAIN;
	}
	private int voteGroups(SecurityUser loginUser,Collection<ConfigAttribute> attributes,GroupVoteType type){
		for(ConfigAttribute attribute:attributes){
			SecurityConfigAttribute configAttribute=(SecurityConfigAttribute)attribute;
			AttributeType attributeType=configAttribute.getAttributeType();
			if(!attributeType.equals(AttributeType.group)){
				continue;
			}
			SecurityGroup group=(SecurityGroup)configAttribute.getMember();
			if(type.equals(GroupVoteType.group)){
				for(SecurityGroup g:loginUser.getGroups()){
					if(group.getId().equals(g.getId())){
						if(configAttribute.isGranted()){
							return ACCESS_GRANTED;
						}else{
							return ACCESS_DENIED;
						}
					}
				}
			}else if(type.equals(GroupVoteType.user)){
				for(SecurityUser user:group.getUsers()){
					if(user.getUsername().equals(loginUser.getUsername())){
						if(configAttribute.isGranted()){
							return ACCESS_GRANTED;
						}else{
							return ACCESS_DENIED;
						}
					}
				}
			}else if(type.equals(GroupVoteType.dept) && loginUser.getDepts()!=null){
				return decitionDepts(buildDeptSecurityConfigAttributes(group.getDepts(),configAttribute.isGranted()), loginUser.getDepts());
			}else if(type.equals(GroupVoteType.position) && loginUser.getPositions()!=null){
				for(SecurityPosition p:group.getPositions()){
					for(SecurityPosition userPosition:loginUser.getPositions()){
						if(p.getId().equals(userPosition.getId())){
							if(configAttribute.isGranted()){
								return ACCESS_GRANTED;
							}else{
								return ACCESS_DENIED;
							}
						}
					}
				}
			}
		}
		return ACCESS_ABSTAIN;
	}
	
	private List<ConfigAttribute> buildDeptSecurityConfigAttributes(List<SecurityDept> depts,boolean granted){
		List<ConfigAttribute> list=new ArrayList<ConfigAttribute>();
		for(SecurityDept dept:depts){
			SecurityConfigAttribute attr=new SecurityConfigAttribute(AttributeType.dept,granted,dept.getCompanyId());
			attr.setMember(dept);
			list.add(attr);
		}
		return list;
	}
}
enum GroupVoteType{
	group,user,dept,position
}