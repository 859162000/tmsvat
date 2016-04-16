package com.deloitte.tms.pl.security.model.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;

import com.deloitte.tms.pl.core.model.impl.BaseRelation;
import com.deloitte.tms.pl.security.model.SecurityUser;

/**
 * 用户信息基础类
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午2:03:05 
 * @version 1.0.0
 */
public abstract class AbstractUser extends BaseRelation implements SecurityUser{
	private static final long serialVersionUID = 5125145011670416263L;
	@SuppressWarnings("unchecked")
	public final Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles()==null?CollectionUtils.EMPTY_COLLECTION:getRoles();
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public int hashCode() {
		if(this.getUsername()!=null){
			return this.getUsername().hashCode();
		}else{
			return -1;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractUser && (obj.hashCode()==this.hashCode())){
			return true;
		}
		return false;
	}
	
}
