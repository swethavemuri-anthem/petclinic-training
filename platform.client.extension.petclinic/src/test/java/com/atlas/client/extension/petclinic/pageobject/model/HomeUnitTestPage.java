/**
 *  Copyright 2016-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.atlas.client.extension.petclinic.pageobject.model;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.mock.web.MockHttpServletRequest;

import com.antheminc.oss.nimbus.context.BeanResolverStrategy;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.test.domain.support.pageobject.UnitTestPage;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;
import com.antheminc.oss.nimbus.test.domain.support.utils.ParamUtils;
import com.atlas.client.extension.petclinic.view.owner.VisitLineItem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Tony Lopez
 *
 */
public class HomeUnitTestPage extends UnitTestPage {

	@Getter
	private LeftNav leftNav = new LeftNav(this);
	
	public HomeUnitTestPage(BeanResolverStrategy beanResolver, String clientId, String clientApp, Long refId) {
		super(beanResolver, clientId, clientApp, null, "petclinicdashboard", "vpDashboard", refId);
		
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addAction(Action._new)
				.getMock();
		this.controller.handlePost(request, null);
	}

	public OwnerLandingUnitTestPage clickGoToOwners() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addNested("/vpDashboard/vtMyVisits/vsMyVisits/goToOwners")
				.addAction(Action._get)
				.getMock();
		
		Object response = this.controller.handlePost(request, null);
		return new OwnerLandingUnitTestPage(this.beanResolver, getClientId(), getClientApp(), response);
	}
	
	@RequiredArgsConstructor
	public static class LeftNav {
	
		private final HomeUnitTestPage page;
		
		public OwnerLandingUnitTestPage clickOwners() {
			return new OwnerLandingUnitTestPage(page.beanResolver, page.getClientId(), page.getClientApp(), null);
		}
		
		public OwnerLandingUnitTestPage clickHome() {
			throw new NotImplementedException("Tell the developers to stop slacking off!");
		}
		
		public OwnerLandingUnitTestPage clickVeterinarians() {
			throw new NotImplementedException("Tell the developers to stop slacking off!");
		}
		
		public OwnerLandingUnitTestPage clickPets() {
			throw new NotImplementedException("Tell the developers to stop slacking off!");
		}
		
		public OwnerLandingUnitTestPage clickNotes() {
			throw new NotImplementedException("Tell the developers to stop slacking off!");
		}
	}

	public List<VisitLineItem> getVisits() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getRefId())
				.addNested("/vpDashboard/vtMyVisits/vsMyVisits/myVisits")
				.addAction(Action._get)
				.getMock();
		
		Param<List<VisitLineItem>> response = ParamUtils.extractResponseByParamPath(this.controller.handlePost(request, null), "/myVisits");
		return response.getLeafState();
	}

	public PetsUnitTestPage clickGoToPets() {
		// client side redirect
		return new PetsUnitTestPage(this, this.getRefId());
	}
}
