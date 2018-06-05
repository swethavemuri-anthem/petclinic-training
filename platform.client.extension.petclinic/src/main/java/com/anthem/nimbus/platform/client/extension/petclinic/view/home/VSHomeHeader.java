/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FieldValue;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Hints;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Hints.AlignOptions;
/* import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Label; DNE */
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Menu;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.PageHeader;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.PageHeader.Property;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Paragraph;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.entity.client.user.ClientUser;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeHeader {
	
//	@MapsTo.Type(ClientUser.class) @Getter @Setter
//	public static class ViewHomeUser {
//		
//		@FieldValue @PageHeader(Property.USERNAME)
//		@Path("/name/fullName") 
//		private String fullName;
//		
//		@FieldValue @PageHeader(Property.USERROLE)
//		@Path("/roleName") 
//		private String roleName;
//		
//	}
//	
//	@Model @Getter @Setter
//	public static class MenuHomeSettings {
//		@Model @Getter @Setter
//		public static class MenuHomeSettingsLinks {
//			@Link(url="")
//			private String placeHolderB;
//			
//			@Link(url="")
//			private String placeHolderC;
//		}
//		
//		@Menu()
//		private MenuHomeSettingsLinks menuHomeSettingsLinks;
//	}
//	
//	@Link(url="/ui/", imgSrc="anthem-rev.svg") @Hints(AlignOptions.Left) @PageHeader(Property.LOGO)
	
	
	@Link(url = "/#/h/petclinicdashboard/vpDashboard", imgSrc = "/images/anthem.png")
	@Hints(AlignOptions.Left)
	@PageHeader(Property.LOGO)
	@Label(value = "Anthem")
	private String linkHomeLogo;
	
	@PageHeader(Property.APPTITLE)
	@Paragraph
	@Label(value = "Pet Clinic")
	private String title;
//
//	@Link(value=Link.Type.MENU, imgSrc="alerts.svg") @Hints(AlignOptions.Right)
//	private MenuHomeSettings linkMenuHomeSettings;
//
//	@Path(linked=false) 
//	private ViewHomeUser viewHomeUser;
	
}
