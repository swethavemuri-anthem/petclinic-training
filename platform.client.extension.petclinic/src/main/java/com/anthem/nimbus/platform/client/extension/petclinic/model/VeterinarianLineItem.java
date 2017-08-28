package com.anthem.nimbus.platform.client.extension.petclinic.model;

import com.anthem.oss.nimbus.core.domain.definition.Execution.Config;
import com.anthem.oss.nimbus.core.domain.definition.Executions.Configs;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo.Path;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.GridColumn;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Link;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Rakesh Patel
 *
 */

@MapsTo.Type(Veterinarian.class)
@Getter @Setter
public class VeterinarianLineItem {
	
	@Path @GridColumn(hidden=true)
	private String id;
	
	@Path
	private String firstName;
	
	@Path
	private String lastName;
	
	@Path
	private String specialty;
	
	@Configs({
		@Config(url="/p/veterinarianview:<!/id!>/_get")
	})
	@Link(imgSrc="edit.png")
	private String edit;

}
