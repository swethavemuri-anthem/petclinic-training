/**
 * 
 */
package com.atlas.client.extension.petclinic.view.home;

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
	@Label("Home")
	private String home;
	
	@Link(url="/h/veterinarianview/vpVeterenarians", imgSrc="documentsIcon") 
	@Label("Veterenerians")
	private String veterenerians;
	
	@Link(url="/h/ownerlandingview/vpOwners", imgSrc="caseHistoryIcon")
	@Label("Owners")
	private String owners;
	
}
