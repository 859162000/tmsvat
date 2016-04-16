package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("taobao")
public class TaoBaoOauth2User extends Oauth2User{

}
