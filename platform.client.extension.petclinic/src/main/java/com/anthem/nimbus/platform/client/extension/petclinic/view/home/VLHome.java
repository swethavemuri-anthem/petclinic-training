/**
 * 
 */
package com.anthem.nimbus.platform.client.extension.petclinic.view.home;

import com.anthem.oss.nimbus.core.domain.definition.Domain;
import com.anthem.oss.nimbus.core.domain.definition.Domain.ListenerType;
import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Page;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Section;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Section.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Domain(value="home", includeListeners={ListenerType.websocket}) 
@Getter @Setter
public class VLHome {

	@Model @Getter @Setter
	public static class VPHome {
		
		@Section(Type.HEADER) 
		private VSHomeHeader vsHomeHeader;
		
		@Section(Type.LEFTBAR) 
		private VSHomeLeftBar vsHomeLeftBar;

	}
	
	@Page private VPHome vpHome;
}
