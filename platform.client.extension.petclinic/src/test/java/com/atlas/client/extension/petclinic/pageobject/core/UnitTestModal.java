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
import com.antheminc.oss.nimbus.domain.defn.Constants;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;

/**
 * @author Tony Lopez
 *
 */
public abstract class UnitTestModal<PARENT> extends UnitTestPageComponent {

	public static final String CLOSE_MODAL_ALIAS = "closeModal";
	
	public UnitTestModal(UnitTestPage parent, String relativePath) {
		super(parent, relativePath);
	}

	public PARENT click_close() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getParent().getViewRootDomainURI())
				.addRefId(this.getParent().getRefId())
				.addNested(this.getRelativePath())
				.addNested(Constants.SEPARATOR_URI.code).addNested(CLOSE_MODAL_ALIAS)
				.addAction(Action._get)
				.getMock();
		
		this.controller.handlePost(request, null);
		return (PARENT) this.getParent();
	}
}
