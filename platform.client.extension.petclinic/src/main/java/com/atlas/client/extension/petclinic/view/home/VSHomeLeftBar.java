/**
 * 
 */
package com.atlas.client.extension.petclinic.view.home;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.MenuLink;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section.Type;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeLeftBar {


	@MenuLink(url="petclinicdashboard/vpDashboard", imgSrc="tasksIcon") 
	@Label("Home")
	private String home;
	
	@MenuLink(url="veterinarianview/vpVeterenarians", imgSrc="caseHistoryIcon")
	@Label("Veterenerians 1")
	private String vets;
	
	
	@MenuLink(url="ownerlandingview/vpOwners", imgSrc="caseHistoryIcon")
	@Label("Owners")
	private String owners;
	
}
