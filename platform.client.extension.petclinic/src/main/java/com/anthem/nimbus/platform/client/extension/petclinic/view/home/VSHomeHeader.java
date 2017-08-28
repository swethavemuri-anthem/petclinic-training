/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo.Path;
import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.FieldValue;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Hints;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Hints.AlignOptions;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Label;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Link;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Menu;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.PageHeader;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.PageHeader.Property;
import com.anthem.oss.nimbus.core.entity.client.user.ClientUser;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Model @Getter @Setter
public class VSHomeHeader {
	
	@MapsTo.Type(ClientUser.class) @Getter @Setter
	public static class ViewHomeUser {
		
		@FieldValue @PageHeader(Property.USERNAME)
		@Path("/name/fullName") 
		private String fullName;
		
		@FieldValue @PageHeader(Property.USERROLE)
		@Path("/roleName") 
		private String roleName;
		
	}
	
	@Model @Getter @Setter
	public static class MenuHomeSettings {
		@Model @Getter @Setter
		public static class MenuHomeSettingsLinks {
			@Link(url="")
			private String placeHolderB;
			
			@Link(url="")
			private String placeHolderC;
		}
		
		@Menu()
		private MenuHomeSettingsLinks menuHomeSettingsLinks;
	}
	
	@Link(url="/ui/", imgSrc="anthem-rev.svg") @Hints(AlignOptions.Left) @PageHeader(Property.LOGO)
	private String linkHomeLogo;
	
	@Label @PageHeader(Property.APPTITLE)
	private String linkCaseManagerTitle;

	@Link(value=Link.Type.MENU, imgSrc="alerts.svg") @Hints(AlignOptions.Right)
	private MenuHomeSettings linkMenuHomeSettings;

	@Path(linked=false) 
	private ViewHomeUser viewHomeUser;
	
}
