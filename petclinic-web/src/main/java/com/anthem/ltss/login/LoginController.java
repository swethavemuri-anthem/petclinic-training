/**
 *
 */
package com.anthem.ltss.login;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.anthem.cm.ltss.extension.model.clientuser.core.LtssClientUser;
//import com.anthem.cm.ltss.extension.web.ClientUserDetails;
import com.antheminc.oss.nimbus.entity.client.access.ClientUserRole;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;
import com.antheminc.oss.nimbus.support.JustLogit;


/**
 * @author Swetha Vemuri
 * @author Dileep Roopreddy
 */
@Controller
public class LoginController {

    private static final String ROLE_CASEMANAGER = "CMAN";
    private static final String ROLE_CASESUPERVISOR = "CSUP";
    private static final String ROLE_CASESPECIALIST = "CSPL";
    private static final String ROLE_ENROLLMENT_ANALYST = "ESPL";
    public static final String DEFAULT_DASHBOARD = "/ltss/#/h/cmdashboard";
    public final static Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	response.sendRedirect("/petclinic/#/h/petclinicdashboard/vpDashboard");
        
    }
    
    @RequestMapping(value = "/**/handleLogin", method = RequestMethod.GET)
    @ResponseBody
    public String handleLogin() throws Exception {
    		return "OK";
    }
    
    /**
     * This method does a role code based redirection
     *
     * @param request
     * @param response
     * @throws Exception
     * @author Dileep Roopreddy
     */
//    @RequestMapping(value = "/**/processLogin", method = RequestMethod.GET)
//    public void postLoginRedirection(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    		
//    		LOG.info("***********Login Request**********");
//    		Enumeration<String> headerNames = request.getHeaderNames();
//		while(headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			LOG.info("Header Name:"+headerName+", Value:"+request.getHeader(headerName));
//		}  		
//        try {
//            ClientUserDetails clientUserDetails = (ClientUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            
//
//            _logger.debug(() -> "Handling redirection for user " + clientUserDetails.getUsername());
//
//            List<ClientUserRole> rolesList = loginService.getUserRoles(clientUserDetails.getUserRoleIds());
//
//            if (rolesList == null || rolesList.isEmpty()) {
//                //Default
//                response.sendRedirect(DEFAULT_DASHBOARD);
//                return;
//            }
//
//            for (ClientUserRole role : rolesList) {
//                _logger.debug(() -> "User Role:" + role.getDisplayName());
//                switch (role.getCode()) {
//
//                    case ROLE_CASEMANAGER: {
//                        response.sendRedirect("/ltss/#/h/view_CMLandingPage");
//                        break;
//                    }
//                    case ROLE_CASESUPERVISOR: {
//                        response.sendRedirect("/ltss/#/h/vrCSLandingPage");
//                        break;
//                    }
//                    case ROLE_CASESPECIALIST: {
//                        response.sendRedirect("/ltss/#/h/casespecialistview");
//                        break;
//                    }
//                    case ROLE_ENROLLMENT_ANALYST: {
//                        response.sendRedirect("/ltss/#/h/enrollmentanalystview");
//                        break;
//                    }
//                    default: {
//                        response.sendRedirect(DEFAULT_DASHBOARD);
//                        break;
//                    }
//                }
//                return;
//            }
//
//        } catch (Exception e) {
//            _logger.error(() -> "Error redirecting for user " + e);
//            response.sendRedirect(DEFAULT_DASHBOARD);
//        }
//    }
    
//	/**
//	 * This method will be used for all api calls from offline application to get the logged in user details
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/**/whoami",method = RequestMethod.GET)
//	@ResponseBody
//	public ClientUser getLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		try {
//			ClientUserDetails clientUserDetails = (ClientUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			LtssClientUser clientUser = (LtssClientUser)clientUserDetails.getClientUser();
//			_logger.debug(() -> "Getting User Details with Username for the offline Application from the Header " + clientUser.getLoginId());
//			return clientUser;
//				
//		} catch(Exception e) {
//			_logger.error(() -> "Error retrieving User details " + e);
//			return null;
//		}
//	}

    private JustLogit _logger = new JustLogit(this.getClass());
}
