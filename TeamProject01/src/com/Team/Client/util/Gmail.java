package com.Team.Client.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("본인의 구글 아이디 필요없습니다.","본인의 구글 비밀번호");
	}
	
}	
