package com.MovieVault.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MovieVault.mb.AuthenticationBean;

@WebFilter("/admin/*")
public class FilterAdmin implements Filter{

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpServletResponse servletResponse =(HttpServletResponse) response;
		AuthenticationBean loginBean=(AuthenticationBean) servletRequest.getSession().getAttribute("authBean");
		if(loginBean!=null && loginBean.isLoggedIn()){
			chain.doFilter(servletRequest, servletResponse);
		}
		else{
			servletResponse.sendRedirect(servletRequest.getContextPath());
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	
}
