/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeLeftBar {


	@Link(url="/h/petclinicdashboard/vpDashboard", imgSrc="tasksIcon") 
	private String home;
	
	@Link(url="/h/veterinarianview/vpVeterenarians", imgSrc="documentsIcon") 
	private String veterenerians;
	
	@Link(url="/h/ownerlandingview/vpOwners", imgSrc="caseHistoryIcon") 
	private String owners;
	
}
