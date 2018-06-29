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
package com.atlas.client.extension.petclinic.pageobject.core;

import org.springframework.mock.web.MockHttpServletRequest;

import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.domain.model.state.ModelEvent;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tony Lopez
 *
 */
@Getter @Setter
public abstract class UnitTestPageComponent extends UnitTestPage {

	protected final String relativePath;
	
	public UnitTestPageComponent(UnitTestPage parent, String relativePath) {
		super(parent);
		this.relativePath = relativePath;
	}

	public Param<?> getParam() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getParent().getRefId())
				.addNested(this.relativePath)
				.addAction(Action._get)
				.getMock();
		
		return this.extractResponse(request, null);
	}

	@Override
	protected <T> T doRequestAndGetLeafState(String path) {
		return super.doRequestAndGetLeafState(this.relativePath + path);
	}
	
	protected Object doEventNotify(ModelEvent<String> event) {
		
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getBaseURI())
				.addNested("/event/notify")
				.getMock();
		
		return this.controller.handleEventNotify(request, event);
	}
}
