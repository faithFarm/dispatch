package org.dispatch.faithfarm.security;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dispatch.faithfarm.domain.IpPermission;
import org.dispatch.faithfarm.domain.SystemUser;
import org.dispatch.faithfarm.hibernate.data.GenericDao;

public class AuthenticationFilter implements Filter {

	private final static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		LOGGER.setLevel(Level.SEVERE);
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		String contextPath = request.getContextPath();
		String ip=request.getRemoteAddr().toString();
		
		boolean access=false;
		
		if ("0:0:0:0:0:0:0:1".equals(ip))
			access=true;
		else {
			GenericDao dao = new GenericDao();
			List<IpPermission> list = dao.listAll(new IpPermission().getClass());
			for (int i=0;i<list.size();i++) {
				IpPermission obj = (IpPermission)list.get(i);
				if (obj.getIpAddress().equals(ip)) 
					access=true;
					
			}
		}
		/*
		if (!"174.141.99.194".equals(ip)&&!"75.147.217.62".equals(ip) && //Boynton Beach Farm
					!"70.89.102.41".equals(ip) && //FTL Farm
					!"67.238.59.138".equals(ip) && //OKE Farm
					!"76.109.62.180".equals(ip) && //EHW Farm
					!"75.149.244.9".equals(ip) && //BYN CORP
					!"76.109.55.65".equals(ip) && //Miles Office
					!"75.149.244.10".equals(ip) && //Judy's Office
					!"127.0.0.1".equals(ip)  ) {
				LOGGER.log(Level.SEVERE,"INVALID IP ADDRESS TRIED TO ACCESS THE SYSTEM: "+request.getRemoteAddr().toString());
				response.sendRedirect(contextPath + "/denied.html");
		}*/
		if (!access) {
			LOGGER.log(Level.SEVERE,"INVALID IP ADDRESS TRIED TO ACCESS THE SYSTEM: "+request.getRemoteAddr().toString());
			response.sendRedirect(contextPath + "/denied.html");
		}
		else {	
			HttpSession session = request.getSession(false);
			if (null == session) {
				response.sendRedirect(contextPath + "/pages/security/login.jsp");
			} else {
				SystemUser user = (SystemUser) session.getAttribute("USER_"+session.getId());
				if (user == null ) {
					response.sendRedirect(contextPath + "/pages/security/login_error.jsp");
				} else
					chain.doFilter(request, response);
	
			}
		}//end else
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {
	}


}
