/**
 * 
 */
package com.atlas.client.extension.petclinic.view.home;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.MenuLink;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeLeftBar {

	@Label("Home")
	@MenuLink(url="petclinicdashboard/vpDashboard", imgSrc="tasksIcon") 
	private String home;
	
	@Label("Veterinarians")
	@MenuLink(url="veterinarianview/vpVeterenarians", imgSrc="caseHistoryIcon")
	private String vets;
	
	@Label("Owners")
	@MenuLink(url="ownerlandingview/vpOwners", imgSrc="caseHistoryIcon")
	private String owners;
	
	@Label("Pets")
	@MenuLink(url="petview/vpAllPets", imgSrc="caseHistoryIcon")
	private String pets;
	
	@Label("Notes")
	@MenuLink(url="petclinicdashboard/vpNotes", imgSrc="notesIcon")
	private String notes;
	
}
