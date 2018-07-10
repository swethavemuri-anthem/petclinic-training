package com.atlas.client.extension.petclinic.core;

import java.time.LocalDate;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Sandeep Mantha
 *
 */

@MapsTo.Type(Visit.class)
@Getter @Setter
public class VisitLineItem {
	
	@Path @GridColumn(hidden=true)
	private Long id;
	
	// Add Pet Name and Owner columns
	
	@Path @GridColumn(filter=true)
	@Label("Appointment Date")
	private LocalDate appointment;
	
	@Path
	@GridColumn(filter=true)
	@Label("Owner's name")
	private String ownerName;
	
	@Path
	@GridColumn(filter=true)
	@Label("Pet's name")
	private String petName;
	
	@Path @GridColumn(filter=true)
	@Label("Reason For Visit")
	private String reasonForVisit;
	
//	@Path(value="/p/veterinarianview:<!/.m/vetId!>/vpAddEditVeterenarian/vtAddEditVeterinarian/vsAddEditVeterinarian/vfAddEditVeterinarian/fullName/_get", linked=false, detachedState=@DetachedState(loadState=LoadState.AUTO))
//	private String vetName;
	
	@Path @GridColumn(filter=true)
	@Label("Status")
	private String status;
	
    @LinkMenu
    private VLMVisitItemLinks vlmVisitItemLinks;
   
    @Model
    @Getter @Setter
    public static class VLMVisitItemLinks {
        @Config(url="/p/petassessmentview/_new")
        @Link(imgSrc="edit.png") @Label("Pet Questionnaire") private String petQuestionnaire;
        
    }

}
