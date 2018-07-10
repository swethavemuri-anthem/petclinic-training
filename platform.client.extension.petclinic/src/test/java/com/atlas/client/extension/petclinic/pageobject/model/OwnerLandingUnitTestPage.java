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

import org.springframework.mock.web.MockHttpServletRequest;

import com.antheminc.oss.nimbus.context.BeanResolverStrategy;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;
import com.atlas.client.extension.petclinic.core.OwnerLineItem;
import com.atlas.client.extension.petclinic.pageobject.core.UnitTestPage;

/**
 * @author Tony Lopez
 *
 */
public class OwnerLandingUnitTestPage extends UnitTestPage {

	/**
	 * @param beanResolver
	 * @param clientId
	 * @param clientApp
	 * @param refId
	 */
	public OwnerLandingUnitTestPage(BeanResolverStrategy beanResolver, String clientId, String clientApp, Long refId) {
		super(beanResolver, clientId, clientApp, "owner", "ownerlandingview", "vpOwners", refId);
	}

	public List<OwnerLineItem> getOwners() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getRefId())
				.addNested("/vpOwners/vtOwners/vsOwners/owners")
				.addAction(Action._get)
				.getMock();
		
		Param<List<OwnerLineItem>> response = extractResponse(request, null);
		return response.getLeafState();
	}
	
	public Param<List<OwnerLineItem>> invokeGet_owners() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getRefId())
				.addNested("/vpOwners/vtOwners/vsOwners/owners")
				.addAction(Action._get)
				.getMock();
		
		return extractResponse(request, null);
	}
	
	public OwnerInfoUnitTestPage clickOwnerInfo(int index) {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getRefId())
				.addNested("/vpOwners/vtOwners/vsOwners/owners/" + index + "/vlmCaseItemLinks/ownerInfo")
				.addAction(Action._get)
				.getMock();
		
		this.controller.handlePost(request, null);

		return new OwnerInfoUnitTestPage(this.beanResolver, this.getClientId(), this.getClientApp(), this.getRefId());
	}
}
