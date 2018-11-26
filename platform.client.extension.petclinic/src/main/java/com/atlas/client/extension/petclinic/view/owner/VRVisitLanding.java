package com.atlas.client.extension.petclinic.view.owner;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.atlas.client.extension.petclinic.core.owner.Visit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Domain(value = "visitview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@MapsTo.Type(Visit.class)
@Getter @Setter
@ViewRoot(layout = "home")
public class VRVisitLanding {

	@Page(route="visitview", defaultPage=true)
	private VPAddEditVisit vPAddEditVisit;

}
