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

import org.springframework.mock.web.MockHttpServletRequest;

import com.antheminc.oss.nimbus.context.BeanResolverStrategy;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.test.domain.support.pageobject.UnitTestPage;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Tony Lopez
 *
 */
public class DashboardUnitTestPage extends UnitTestPage {

	@Getter
	private LeftNav leftNav = new LeftNav(this);
	
	/**
	 * @param beanResolver
	 * @param clientId
	 * @param clientApp
	 * @param coreDomainAlias
	 * @param viewDomainAlias
	 * @param pageId
	 * @param refId
	 */
	public DashboardUnitTestPage(BeanResolverStrategy beanResolver, String clientId, String clientApp,
			Long refId) {
		super(beanResolver, clientId, clientApp, null, "cmdashboard", "vpDashboard", refId);
	}
	
	@RequiredArgsConstructor
	public static class LeftNav {
	
		private final DashboardUnitTestPage page;
		
		public OwnerLandingUnitTestPage clickOwners() {
			MockHttpServletRequest request = MockHttpRequestBuilder.withUri(page.getViewRootDomainURI())
					.addRefId(page.getRefId())
					.addNested("???")
					.addAction(Action._get)
					.getMock();
			
			page.controller.handlePost(request, null);

			return new OwnerLandingUnitTestPage(page.beanResolver, page.getClientId(), page.getClientApp(), page.getRefId());
		}
	}
}
