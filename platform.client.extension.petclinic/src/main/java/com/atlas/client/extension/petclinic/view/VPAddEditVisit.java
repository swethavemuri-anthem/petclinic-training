package com.atlas.client.extension.petclinic.view;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ButtonGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Calendar;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.InputDate;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Visit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@MapsTo.Type(Visit.class)
@Getter @Setter
public class VPAddEditVisit {
	
	@Tile(imgSrc="resources/icons/autocase.svg#Layer_1", size=Tile.Size.Large) 
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
		@Label("Pet Id")
		private String petId;

//		@ComboBox @Path
//		@NotNull
//		@Model.Param.Values(url="Anthem/icr/p/veterinarian/_search?fn=lookup&projection.mapsTo=code:id,label:lastName")
//		private String vetId;
		
//		@Path
//		private LocalDateTime appointment;
		
		@TextBox @Path
		@Label("Reason For Visit")
		private String reasonForVisit;
		
		@TextBox @Path
		@Label("Visit Outcome")
		private String visitOutcome;
		
		@TextBox @Path
		@Label("Visit Note")
		private String visitNote;
		
		@Calendar @Path
		@Label("Appointment")
		private LocalDate appointment;
		
		@TextBox @Path
		@Label("Status")
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
