package com.atlas.client.extension.petquestionnaire.core;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TabInfo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.GROUP_1;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Andrew Jo
 *
 */
@Model
@Data
public class PetAssessment_Name {
	
	@TabInfo
	@Label("PetAssessment Name Tab")
	private PetAssessment_Name_Tab petName_Accordion_tab;
	
	@Model @Getter @Setter
	public static class PetAssessment_Name_Tab{
		// TextBox
		// ValidateConditional
		@TextBox(postEventOnChange = true)
		@Label(value = "First Name")
		@ValidateConditional(when = " state != null && state != ''", targetGroup = GROUP_1.class)
		private String petForm_1a;
		
		// TextBox
			// ValidateConditional
		@TextBox(postEventOnChange = true)
		@Label(value = "Last Name")
		@ValidateConditional(when = "state != null && state != ''", targetGroup = GROUP_1.class)
		private String petForm_1b;
	}
	

}
