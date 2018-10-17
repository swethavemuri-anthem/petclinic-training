package com.atlas.client.extension.petclinic.view.veterinarian;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Veterinarian;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author Sandeep Mantha
 * @author Rakesh Patel
 *
 */
@Domain(value = "veterinarianview", includeListeners = {ListenerType.websocket})
@Repo(value = Repo.Database.rep_none,cache = Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper = true)
@MapsTo.Type(Veterinarian.class)
@ViewRoot(layout = "home")
public class VRVeterinarian {
	
	@Label("Veterinarians")
	@Page(defaultPage=true)
	private VPVeterenarians vpVeterenarians;

	@Model @Getter @Setter
	public static class VPVeterenarians  {

		@Tile(imgSrc = "resources/icons/task.svg#Layer_1", size = Tile.Size.Medium)
        private VTVeterinarians vtVeterinarians;
		
    }
	
	@Model @Getter @Setter
	public static class VTVeterinarians  {

		@Section
		private VSVeterinarians vsVeterinarians;
    }

	@Model @Getter @Setter
	public static class VSVeterinarians  {
		
		@Label("Add Veterinarian")
	    @Button (style = Button.Style.SECONDARY)
	    @Config(url="/p/veterinarianlandingview/_new")
	    private String addVeterinarian;
		
		@Label("Veterinarians")       
		@Grid(onLoad = true,pageSize = "7")
		@Path(linked = false)
		@Config(url = "/vpVeterenarians/vtVeterinarians/vsVeterinarians/veterinarians.m/_process?fn=_set&url=/p/veterinarian/_search?fn=example")
		private List<VeterinarianLineItem> veterinarians;
		
    }
}
