package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.util.List;

import com.anthem.nimbus.platform.client.extension.petclinic.model.VeterinarianLineItem;
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

@Domain(value = "veterinarianlandingview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
//@MapsTo.Type(Veterinarian.class)
public class VRVeterinarianLanding {
	
	@Page(defaultPage=true, layout="home")
	private VPVeterenarians vpVeterenarians;
	
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
	         @Config(url="/p/veterinarianview/_new")
	    })
	    @Button(imgSrc = "add.svg", cssClass ="btn btn-icon green")
	    private String addVeterinarian;
		
		@MapsTo.Path(linked=false)
		@Config(url="/p/veterinarianlandingview/vpVeterenarians/vtVeterinarians/vsVeterinarians/veterinarians.m/_process?fn=_set&url=/p/veterinarian/_search?fn=example")       
		@Grid(onLoad=true, isTransient = true, pageSize = "7")
		private List<VeterinarianLineItem> veterinarians;
		
    }
}
