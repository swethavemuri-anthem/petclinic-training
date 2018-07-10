package com.atlas.client.extension.petquestionnaire.core;

import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.GROUP_1;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.ValidationScope;

import lombok.Data;
/**
 * @author Andrew Jo
 *
 */
@Model
@Data
public class PetAssessment_Name {
	
	// TextBox
	// ValidateConditional
	@TextBox(postEventOnChange = true)
	@Label(value = "First Name")
	@ValidateConditional(when = " state != null && state != ''", targetGroup = GROUP_1.class, scope = ValidationScope.SIBLING_NESTED)
	private String petForm_1a;
	
	// TextBox
		// ValidateConditional
	@TextBox(postEventOnChange = true)
	@Label(value = "Last Name")
	@ValidateConditional(when = "state != null && state != ''", targetGroup = GROUP_1.class, scope = ValidationScope.SIBLING_NESTED)
	private String petForm_1b;

}
