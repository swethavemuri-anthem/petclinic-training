package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;

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
	private String id;
	
	@Path
	private LocalDate appointment;
	
	@Path
	private String reasonForVisit;
	
//	@Path(value="/p/veterinarianview:<!/.m/vetId!>/vpAddEditVeterenarian/vtAddEditVeterinarian/vsAddEditVeterinarian/vfAddEditVeterinarian/fullName/_get", linked=false, detachedState=@DetachedState(loadState=LoadState.AUTO))
//	private String vetName;
	
	@Path
	private String status;
	
	@Configs({
		@Config(url="/p/visitview:<!/id!>/_get")
	})
	@Link(imgSrc="edit.png")
	private String edit;

}
