package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.util.List;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Veterinarian;
import com.anthem.nimbus.platform.client.extension.petclinic.model.VisitLineItem;
import com.anthem.oss.nimbus.core.domain.definition.Domain;
import com.anthem.oss.nimbus.core.domain.definition.Domain.ListenerType;
import com.anthem.oss.nimbus.core.domain.definition.Execution.Config;
import com.anthem.oss.nimbus.core.domain.definition.Executions.Configs;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.Repo;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Button;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Grid;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Page;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Section;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Tile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author Sandeep Mantha
 * @author Rakesh Patel
 *
 */

@Domain(value = "veterinarianview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
@MapsTo.Type(Veterinarian.class)
public class VRVeterinarian {
	
	@Page(route="veterinarianview", layout="home")
	private VPVeterenarians vpVeterenarians;
	
	@Page(route="veterinarianview", defaultPage=true, layout="home")
	private VPAddEditVeterinarian vpAddEditVeterenarian;

	@Model
	@Getter @Setter
	public static class VPVeterenarians  {

		@Tile(title = "VETERINARIANS", imgSrc = "resources/icons/task.svg#Layer_1", size = Tile.Size.Medium)
        private VTVeterinarians vtVeterinarians;
		
    }
	
	@Model
	@Getter @Setter
	public static class VTVeterinarians  {

		@Section
		private VSVeterinarians vsVeterinarians;
    }

	@Model
	@Getter @Setter
	public static class VSVeterinarians  {
		
		@Configs({
	         @Config(url="/vpVeterenarians/vtVeterinarians/vsVeterinarians/_nav?pageId=vpAddEditVeterenarians")
	    })
	    @Button(imgSrc = "add.svg", cssClass ="btn btn-icon green")
	    private String addConcern;
		
		@MapsTo.Path(linked=false)
		@Config(url="/vpVeterenarians/vtVeterinarians/vsVeterinarians/veterinarians.m/_process?fn=_set&url=/p/veterinarian/_search?fn=example")       
		@Grid(onLoad=true, isTransient = true, pageSize = "7")
		private List<VisitLineItem> veterinarians;
		
    }
}
