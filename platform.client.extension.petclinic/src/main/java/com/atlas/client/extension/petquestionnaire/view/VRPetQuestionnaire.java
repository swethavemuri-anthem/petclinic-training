package com.atlas.client.extension.petquestionnaire.view;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.atlas.client.extension.petquestionnaire.core.PetAssessment;
import com.atlas.client.extension.petquestionnaire.core.PetAssessment.PetForm;

import lombok.Getter;
import lombok.Setter;

@Domain(value = "petassessmentview", includeListeners = { ListenerType.websocket })
@Repo(value = Database.rep_none, cache = Cache.rep_device)
@MapsTo.Type(PetAssessment.class)
@ViewRoot(layout = "")
@Getter
@Setter
public class VRPetQuestionnaire {
	
	@Page(defaultPage = true)
	private VPMain vpMain;


	@Model
	@Getter
	@Setter
	public static class VPMain {

		@Tile( size = Tile.Size.Large)
		private VTMain vtMain;
	}

	@Model
	@Getter
	@Setter
	public static class VTMain {
		
		@Section
		private VSPetGeneralAssessment vsPetGeneralAssessment;
	
	}
	
	@MapsTo.Type(PetAssessment.class)
	@Getter
	@Setter
	public static class VSPetGeneralAssessment{
			
		@Form 	
		private PetForm petForm;

	}

	

}