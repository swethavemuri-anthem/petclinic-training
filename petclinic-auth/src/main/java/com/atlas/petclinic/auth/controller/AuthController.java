/**
 * 
 */
package com.atlas.petclinic.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.antheminc.oss.nimbus.domain.session.SessionProvider;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;
import com.antheminc.oss.nimbus.entity.client.user.SelectedClientEntity;
import com.atlas.petclinic.auth.config.ClientUserDetailsImpl;
import com.atlas.petclinic.auth.config.ClientUserDetailsServiceImpl;
import com.atlas.petclininc.auth.utils.JWTAuthTokenUtil;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Swetha Vemuri
 *
 */
@Controller
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired 
	private ClientUserDetailsServiceImpl userDetailsService;
	
	@Autowired
    private JWTAuthTokenUtil tokenUtil;
	
	@Autowired
	public SessionProvider sessionProvider;	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, HttpServletResponse response) throws Exception {
		ModelAndView login = new ModelAndView("login", "loginuser", new LoginUser());
		return login;
	}
	/**
	 * ANTM
	 * 	|
	 * 	-->	GBD	
	 * 			|
	 * 			--> NICU
	 * 			--> LTSS
	 * 	--> COMM
	 * 			|
	 * 			--> HRS
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/postlogin", method = RequestMethod.POST)
    public ModelAndView postLogin(@ModelAttribute("loginuser")LoginUser loginuser, Model model) throws Exception {
		
		// only if the username is authenticated - then show this page with the org selection
		// Client implementations may vary
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginuser.getUsername(),
                		loginuser.getUsername()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
		ModelAndView login = new ModelAndView("postlogin", "selectedcliententity", new SelectedClientEntity());
		
		Map<String, List<String>> orgappMap = new HashMap<>();
		List<String> gbdapp = new ArrayList<>();
		gbdapp.add("LTSS");
		gbdapp.add("NICU");
		
		List<String> commapp = new ArrayList<>();
		commapp.add("HRS");
		
		orgappMap.put("GBD", gbdapp);
		orgappMap.put("COMM", commapp);
		
		model.addAttribute("client", "Anthem");
		model.addAttribute("orgs", orgappMap);
		
		// In actual implementation - need to get the orgs and apps from the client user hierarchy
		// ClientUserDetails userDetails = userDetailsService.getClientUserDetails();
		// ClientUser cu = userDetails.getAuthenticatedClientUser();
    		return login;
    }
	
	@RequestMapping(value = "/redirectlogin", method = RequestMethod.POST)
    public RedirectView handleLogin(@ModelAttribute("selectedcliententity") SelectedClientEntity selectedcliententity, 
    		Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) throws Exception {
		redirect.addFlashAttribute("selectedcliententity", selectedcliententity);
		sessionProvider.setAttribute("selectedcliententity", prepareCommandUrl(selectedcliententity));
		return new RedirectView("/#/authlanding");
    }
	
	@RequestMapping(value = "/handlelogin", method = RequestMethod.GET)
    public ResponseEntity handleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClientUserDetailsImpl cuDeatils = (ClientUserDetailsImpl)userDetailsService.getClientUserDetails();
    	ClientUser cu = cuDeatils.getAuthenticatedClientUser();
    	String token = tokenUtil.generateToken(cuDeatils);
    	System.out.println("session provider " + sessionProvider.getAttribute("selectedcliententity"));
    	return ResponseEntity.ok(new RedirectHandle("/h/petclinicdashboard",cu,token,sessionProvider.getAttribute("selectedcliententity")));
    }
	
	private String prepareCommandUrl(SelectedClientEntity ce) {
		if(ce != null) {
			String client = ce.getClient();
			String org = ce.getOrg();
			String app = ce.getApp();
			
			String commandurl = "/" + client + "/" + org + "/" + app;
			return commandurl;
			
		}
		return null;
	}
	
	@Getter @Setter
    public static class RedirectHandle {
    	public RedirectHandle(String route) {
    		this.redirectRoute = route;
    		this.authenticatedUser = null;
    		
    	}
    	
    	public RedirectHandle(String route, ClientUser cu) {
    		this.redirectRoute = route;
    		this.authenticatedUser = cu;
    	}
    	
    	public RedirectHandle(String route, ClientUser cu, String token, String commandUrl) {
    		this.redirectRoute = route;
    		this.authenticatedUser = cu;
    		this.token = token;
    		this.commandUrl = commandUrl;
    	}
    	
    	private String redirectRoute;
    	private ClientUser authenticatedUser;
    	private String token;
    	private String commandUrl;
    }
    
    @Getter @Setter
    public static class LoginUser {
    	private String username;
    	private String password;
    }
    	
}
