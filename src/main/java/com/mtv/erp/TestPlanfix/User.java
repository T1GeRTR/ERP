package com.mtv.erp.TestPlanfix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
		name = "user"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "",
		propOrder = {"id", "name", "lasName", "login"}
)
public class User {
	public Phones phones;
	public int id;
	public String name;
	public String lastName;
	public String login;
	public String email;
	public Object jabber;
	public String role;
	public String status;
	public int iIActivated;
	public Notify notify;
	public Object birthdate;
	public String sex;
	public int coeff;
	public String timezone;
	public int useDst;
	public int isInvisibleOutOfGroup;
	public int isBlindOutOfGroup;
	public Post post;
	public String userPic;
	public int isOnline;
	public UserGroups userGroups;
}
