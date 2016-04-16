package com.deloitte.tms.pl.security.security.decision;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.attribute.AttributeType;
import com.deloitte.tms.pl.security.security.attribute.SecurityConfigAttribute;

/**
 * @since 2013-1-25
 * @author Jacky.gao
 */
public class PositionAccessDecisionVoter extends AbstractAccessDecisionVoter {

	@Override
	protected AttributeType getAttributeType() {
		return AttributeType.position;
	}

	public int vote(Authentication authentication, Object object,Collection<ConfigAttribute> attributes) {
		List<SecurityPosition> loginUserPositions=((SecurityUser)authentication.getPrincipal()).getPositions();
		for(ConfigAttribute attribute:attributes){
			SecurityConfigAttribute configAttribute=(SecurityConfigAttribute)attribute;
			if(!configAttribute.getAttributeType().equals(AttributeType.position)){
				continue;
			}
			SecurityPosition position=(SecurityPosition)configAttribute.getMember();
			for(SecurityPosition userPosition:loginUserPositions){
				if(position.getId().equals(userPosition.getId())){
					if(configAttribute.isGranted()){
						return ACCESS_GRANTED;
					}else{
						return ACCESS_DENIED;
					}
				}
			}
		}
		return ACCESS_ABSTAIN;
	}
}
