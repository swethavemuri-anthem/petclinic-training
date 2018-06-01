package com.anthem.nimbus.platform.client.extension.petclinic.view;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Visit;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;

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
public class VRVisitLanding {

	@Page(route="visitview", defaultPage=true)
	private VPAddEditVisit vPAddEditVisit;

}
