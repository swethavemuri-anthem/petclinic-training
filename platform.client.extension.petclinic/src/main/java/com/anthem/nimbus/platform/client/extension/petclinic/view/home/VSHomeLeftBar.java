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

	@Link(url="/h/cmcaseview/vpMemberDetails", imgSrc="memberOverviewIcon")
	@Label(value = "Member Overview")
	private String memberOverview;

	@Link(url="/pc/a/vpDashboard", imgSrc="home.svg") 
	private String home;
	
	@Link(url="/pc/v/", imgSrc="toolbox.svg") 
	private String veterenerians;
	
	@Link(url="/pc/o/", imgSrc="chart.svg") 
	private String owners;
	
}
