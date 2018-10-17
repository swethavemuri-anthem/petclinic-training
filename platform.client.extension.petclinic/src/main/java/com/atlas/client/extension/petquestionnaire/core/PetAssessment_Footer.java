package com.atlas.client.extension.petquestionnaire.core;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TabInfo;
import com.antheminc.oss.nimbus.domain.defn.extension.AccessConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.AccessConditional.Permission;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Data;
/**
 * @author Andrew Jo
 *
 */
@Model
@Data
public class PetAssessment_Footer {

	@TabInfo
	private PetAssessment_Footer_Tab footer_tab;
	
	@Model @Data
	public static class PetAssessment_Footer_Tab {
		@AccessConditional(whenAuthorities="?[#this == 'TEST'].empty", p=Permission.HIDDEN)
		@Button(style = Button.Style.PRIMARY, type = Button.Type.submit)
		@Label(value = "Submit")
		private String submit;
		
		@Button(browserBack = true)
		@Label(value = "Back")
		private String back;
	}
	
}
