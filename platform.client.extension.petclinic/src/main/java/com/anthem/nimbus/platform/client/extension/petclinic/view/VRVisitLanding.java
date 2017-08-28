package com.anthem.nimbus.platform.client.extension.petclinic.view;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Visit;
import com.anthem.oss.nimbus.core.domain.definition.Domain;
import com.anthem.oss.nimbus.core.domain.definition.Domain.ListenerType;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.Repo;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Page;

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
