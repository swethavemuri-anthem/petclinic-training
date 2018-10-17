package com.atlas.client.extension.petquestionnaire.core;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Accordion;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.entity.AbstractEntity.IdLong;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andrew Jo
 *
 */
@Domain(value = "petassessment", includeListeners = { ListenerType.persistence })
@Repo(alias = "petassessment", value = Database.rep_mongodb, cache = Cache.rep_device)
@Getter
@Setter
public class PetAssessment extends IdLong {

	@Getter
	@Setter
	private PetForm petForm;
	
	@Model
	@Getter
	@Setter
	public static class PetForm {
		
		// Accordions
		@Accordion
		@Label("Name")
		private PetAssessment_Name patientName;
				
		@Accordion
		@Label("Body")
		private PetAssessment_Body petFormBody;
		
		@Accordion
		@Label("Footer Buttons")
		private PetAssessment_Footer footerButtons;
	}
	
}