package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("qzone")
public class QQOauth2User extends Oauth2User{

}
