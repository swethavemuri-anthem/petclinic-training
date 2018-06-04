package com.anthem.ltss.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if(cookieName != null && (cookieName.equalsIgnoreCase("SMCHALLENGE") || 
					cookieName.equalsIgnoreCase("SMLOCALE") ||
					cookieName.equalsIgnoreCase("SMSESSION")
					)) {
				cookie.setMaxAge(0);
				cookie.setValue(null);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		String requestUrl = request.getRequestURL().toString();
		String[] requestParas = requestUrl.split("/ltss/");
		StringBuffer redirectUrl = new StringBuffer(requestParas[0]);
		redirectUrl.append("/ltss/login.html");
		response.sendRedirect(redirectUrl.toString());
	}

}
