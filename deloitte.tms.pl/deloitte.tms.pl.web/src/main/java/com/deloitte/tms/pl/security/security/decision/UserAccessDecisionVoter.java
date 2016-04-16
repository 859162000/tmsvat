package com.deloitte.tms.pl.security.security.decision;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.attribute.AttributeType;
import com.deloitte.tms.pl.security.security.attribute.SecurityConfigAttribute;

/**
 * @since 2013-1-25
 * @author Jacky.gao
 */
public class UserAccessDecisionVoter extends AbstractAccessDecisionVoter {
	
	@Override
	protected AttributeType getAttributeType() {
		return AttributeType.user;
	}

	public int vote(Authentication authentication, Object object,Collection<ConfigAttribute> attributes) {
		SecurityUser loginUser=(SecurityUser)authentication.getPrincipal();
		for(ConfigAttribute attribute:attributes){
			SecurityConfigAttribute configAttribute=(SecurityConfigAttribute)attribute;
			boolean granted=configAttribute.isGranted();
			if(!configAttribute.getAttributeType().equals(AttributeType.user)){
				continue;
			}
			SecurityUser user=(SecurityUser)configAttribute.getMember();
			if(loginUser.getUsername().equals(user.getUsername())){
				if(granted){
					return ACCESS_GRANTED;
				}else{
					return ACCESS_DENIED;					
				}
			}
		}
		return ACCESS_ABSTAIN;
	}
}
