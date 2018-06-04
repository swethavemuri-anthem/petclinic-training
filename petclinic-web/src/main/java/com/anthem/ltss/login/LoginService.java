/**
 * 
 */
package com.anthem.ltss.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anthem.cm.ltss.extension.conf.LTSSBeanResolver;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.cmd.Command;
import com.antheminc.oss.nimbus.domain.cmd.CommandBuilder;
import com.antheminc.oss.nimbus.domain.cmd.CommandMessage;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.MultiOutput;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.Output;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecutorGateway;
import com.antheminc.oss.nimbus.domain.session.SessionProvider;
import com.antheminc.oss.nimbus.entity.client.access.ClientUserRole;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;
import com.antheminc.oss.nimbus.support.JustLogit;

/**
 * MockLoginService is used for converting the logged in user into application specific clientuser object and storing in session.
 * The method also returns a redirect url based on the logged in user.
 * 
 * @author Swetha Vemuri
 *
 */
@Service
public class LoginService {
	
	private final static Map<String, String> successRedirect = new HashMap<String, String>();
		static {
			successRedirect.put("supervisor", "/#/cs/a");
			successRedirect.put("casemanager", "/#/h/cmdashboard");
			successRedirect.put("DEFAULT", "/#/h/cmdashboard");
		}
	private static String userKey = "client-user-key";
	private JustLogit logit = new JustLogit(this.getClass());
	
	public String onAuthenticationSuccess(String username) {
		String redirect = "DEFAULT";
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,username);
		SecurityContextHolder.getContext().setAuthentication(auth);
		SessionProvider sessionProvider = LTSSBeanResolver.getBean(SessionProvider.class);
		ClientUser loggedInUser = sessionProvider.getLoggedInUser();

		if((loggedInUser != null && 
				!StringUtils.equals(loggedInUser.getLoginId(), username)) || loggedInUser == null) {
			String searchUri = "Anthem/fep/icr/p/clientuser/_search?fn=query&where=clientuser.loginId.eq('"+username+"')&fetch=1";
			Command cmd = CommandBuilder.withUri(searchUri).getCommand();
			cmd.setAction(Action._search);
			
			CommandMessage cmdMsg = new CommandMessage();
			cmdMsg.setCommand(cmd);
			CommandExecutorGateway gateway = LTSSBeanResolver.getBean(CommandExecutorGateway.class);
			MultiOutput multiOp = gateway.execute(cmdMsg);
			List<Output<?>> ops  = multiOp.getOutputs();
			Object output = ops.get(0).getValue();
			if(output != null){
				logit.debug(() -> "Authenticated clientuser : "+output);
				sessionProvider.setLoggedInUser((ClientUser)output);
				logit.debug(() -> "setting userendpointsession with authenticated user -> key: "+userKey+" value: "+output);
			} else 
				logit.debug(() -> "Platform clientUser not found for userId :"+username+". Is the user registered in platform ?");
		}			
		
		if(successRedirect.containsKey(username)){
			for(Map.Entry<String, String> entry : successRedirect.entrySet()) {
				if(StringUtils.equalsIgnoreCase(entry.getKey(),username)) {
					redirect = entry.getValue();
				}
			}
		} else {
			return successRedirect.get(redirect);
		}
		return redirect;
	}
	
	
	/**
	 * This method searches for userrole basing on Id
	 * @author Dileep Roopreddy
	 * @param userRoleIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClientUserRole> getUserRoles(List<String> userRoleIds) {
		logit.debug(() -> "Handling redirection for user with roles " + userRoleIds);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(String userRole: userRoleIds) {
			if(i == userRoleIds.size() - 1) {
				sb.append("\"").append(userRole).append("\"");
			}
			else{
				sb.append("\"").append(userRole).append("\"").append(",");
			}
			i++;
		}
		
		// get client user role(s)
		String searchRolesUri = "Anthem/fep/ltss/p/userrole/_search?fn=query&where=userrole.code.in(" + sb.toString() + ")";
		MultiOutput multiOp = executeCommand(searchRolesUri, Action._search);
		if(multiOp.getSingleResult() != null) {
			return (List<ClientUserRole>) multiOp.getSingleResult();
		}
		
		return null;
	}
	
	/**
	 * Execute query in the platform
	 * @param searchRolesUri
	 * @param action
	 * @return
	 */
	private MultiOutput executeCommand(String searchRolesUri, Action action) {
		Command cmd = CommandBuilder.withUri(searchRolesUri).getCommand();
		cmd.setAction(action);
		CommandMessage cmdMsg = new CommandMessage();
		cmdMsg.setCommand(cmd);
		CommandExecutorGateway commandExecutorGateway = LTSSBeanResolver.getBean(CommandExecutorGateway.class);
		MultiOutput multiOp = commandExecutorGateway.execute(cmdMsg);
		return multiOp;
	}
}
