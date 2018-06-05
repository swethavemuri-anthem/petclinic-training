package com.atlas.client.extension.petclinic.core;

import java.time.LocalDate;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
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
	
	@Path @GridColumn
	@Label("Appointment")
	private LocalDate appointment;
	
	@Path @GridColumn
	@Label("Reason For Visit")
	private String reasonForVisit;
	
//	@Path(value="/p/veterinarianview:<!/.m/vetId!>/vpAddEditVeterenarian/vtAddEditVeterinarian/vsAddEditVeterinarian/vfAddEditVeterinarian/fullName/_get", linked=false, detachedState=@DetachedState(loadState=LoadState.AUTO))
//	private String vetName;
	
	@Path @GridColumn
	@Label("Status")
	private String status;
	
	@Configs({
		@Config(url="/p/visitview:<!/id!>/_get")
	})
	@Link(imgSrc="edit.png")
	private String edit;

}
