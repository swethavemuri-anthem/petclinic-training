/**
 * 
 */
package com.atlas.petclinic.auth.config;

import com.antheminc.oss.nimbus.app.extension.config.DefaultClientUserDetails;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;

/**
 * @author Swetha Vemuri
 *
 */
public class ClientUserDetailsImpl extends DefaultClientUserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientUser clientUser;
	
	public ClientUserDetailsImpl(ClientUser clientUser) {
		super(clientUser);
		this.clientUser = clientUser;
	}

	@Override
	public String getPassword() {
		return this.clientUser.getLoginId();
	}
}
