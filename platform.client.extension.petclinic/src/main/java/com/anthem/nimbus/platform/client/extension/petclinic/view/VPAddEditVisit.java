package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Visit;
import com.anthem.oss.nimbus.core.domain.definition.Execution.Config;
import com.anthem.oss.nimbus.core.domain.definition.Executions.Configs;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo.Path;
import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.Repo.Cache;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Button;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.ButtonGroup;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.ComboBox;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Form;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.InputDate;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Section;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.TextBox;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Tile;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@MapsTo.Type(Visit.class)
@Getter @Setter
public class VPAddEditVisit {
	
	@Tile(title="ADD/EDIT VISIT", imgSrc="resources/icons/autocase.svg#Layer_1", size=Tile.Size.Large) 
	private VTAddEditVisit vtAddEditVisit;

	@Model
	@Getter @Setter
	public static class VTAddEditVisit {
		
		@Section
		private VSAddEditVisit vsAddEditVisit;
	}

	@MapsTo.Type(Visit.class)
	@Getter @Setter
	public static class VSAddEditVisit {
		
		@Form(cssClass="twoColumn")
		private VFAddEditVisit vfAddEditVisit;
	}
	

	@MapsTo.Type(Visit.class)
	@Getter @Setter
	public static class VFAddEditVisit {
		
		@ButtonGroup
		private VBGAddEditVisit buttonGroup;
		
		//@Path(value="/p/petview:<!/.m/petId!>/vpPetInfo/vtPetInfo/vsPetInfo/vcdPetInfo/vcdbPet/name/_get", cache=Cache.rep_none)
		@TextBox(readOnly=true) @Path
		private String petId;

		@ComboBox @Path
		@NotNull
		@Model.Param.Values(url="Anthem/icr/p/veterinarian/_search?fn=lookup&projection.mapsTo=code:id,label:lastName")
		private String vetId;
		
//		@Path
//		private LocalDateTime appointment;
		
		@TextBox @Path
		private String reasonForVisit;
		
		@TextBox @Path
		private String visitOutcome;
		
		@TextBox @Path
		private String visitNote;
		
		@InputDate @Path
		private LocalDate appointment;
		
		@TextBox @Path
		private String status;
	}
	
	@Model
	@Getter @Setter
	public static class VBGAddEditVisit {
		
		@Button(browserBack=true)
		private String back;
		
		@Configs({
			@Config(url="/vPAddEditVisit/vtAddEditVisit/vsAddEditVisit/vfAddEditVisit/_update")
//			@Config(url="/p/petclinicdashboard/_nav?pageId=vpDashboard")	
		})
		@Button(style=Button.Style.PRIMARY, type = Button.Type.submit)
		private String Submit;
	}
}
