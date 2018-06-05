package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.util.List;

import com.anthem.nimbus.platform.client.extension.petclinic.model.VisitLineItem;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Sandeep Mantha
 *
 */

@Domain(value = "petclinicdashboard", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter
@ViewRoot(layout = "home")
public class VRDashboard {

	@Page(defaultPage=true)
	private VPDashboard vpDashboard;

	@Model
	@Getter @Setter
	public static class VPDashboard  {

		@Tile(imgSrc = "resources/icons/task.svg#Layer_1", size = Tile.Size.Medium)
        private VTMyVisits vtMyVisits;
		
    }
	
	@Model
	@Getter @Setter
	public static class VTMyVisits  {

		@Section
		private VSMyVisits vsMyVisits;
		
    }
	
	@Model
	@Getter @Setter
	public static class VSMyVisits  {
		
		@MapsTo.Path(linked=false)
		@Config(url="/vpDashboard/vtMyVisits/vsMyVisits/myVisits.m/_process?fn=_set&url=/p/visit/_search?fn=example")       
		@Grid(onLoad=true, isTransient = true, pageSize = "7")
		@Label("My Visits")
		private List<VisitLineItem> myVisits;
		
    }
	
}
