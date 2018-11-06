package com.atlas.client.extension.petclinic.view.veterinarian;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
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

@Domain(value = "veterinarianlandingview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
@MapsTo.Type(Veterinarian.class)
@ViewRoot(layout = "home")
public class VRVeterinarianLanding {
	
	@Label("Add Veterinarian")
	@Page(defaultPage=true)
	private VPAddEditVeterinarian vpAddEditVeterenarian;
	
}
