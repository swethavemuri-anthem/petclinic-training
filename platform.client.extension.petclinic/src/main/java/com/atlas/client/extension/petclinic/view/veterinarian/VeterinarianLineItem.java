package com.atlas.client.extension.petclinic.view.veterinarian;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Veterinarian;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Rakesh Patel
 *
 */

@MapsTo.Type(Veterinarian.class)
@Getter @Setter
public class VeterinarianLineItem {
	
	@GridColumn(hidden = true)
	@Path
	private Long id;
	
	@Label("First Name")
	@GridColumn
	@Path
	private String firstName;
	
	@Label("Last Name")
	@GridColumn
	@Path
	private String lastName;
	
	@Label("Speciality")
	@GridColumn
	@Path
	private String speciality;
	
	@Config(url="/p/veterinarianview:<!/id!>/_get")
	@Link(imgSrc="edit.png")
	private String edit;

}
