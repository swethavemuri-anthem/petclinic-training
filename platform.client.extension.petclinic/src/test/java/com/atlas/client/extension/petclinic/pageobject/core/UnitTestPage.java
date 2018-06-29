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
import org.springframework.test.web.client.MockRestServiceServer;

import com.antheminc.oss.nimbus.channel.web.WebActionController;
import com.antheminc.oss.nimbus.context.BeanResolverStrategy;
import com.antheminc.oss.nimbus.domain.cmd.Action;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.MultiOutput;
import com.antheminc.oss.nimbus.domain.cmd.exec.CommandExecution.Output;
import com.antheminc.oss.nimbus.domain.defn.Constants;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.support.Holder;
import com.antheminc.oss.nimbus.support.JustLogit;
import com.antheminc.oss.nimbus.test.domain.support.utils.MockHttpRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tony Lopez
 *
 */
@Getter @Setter
public abstract class UnitTestPage extends Page {
	
	public static final JustLogit logger = new JustLogit();
	
	protected final BeanResolverStrategy beanResolver;
	private final String clientId;
	private final String clientApp;
	private final String coreDomainAlias;
	private final String viewDomainAlias;
	private final String pageId;
	private final Long refId;
	
	protected final WebActionController controller;
	protected final ObjectMapper objectMapper;
	
	private UnitTestPage parent;
	
	protected MockRestServiceServer mockServer;
	
	public UnitTestPage(BeanResolverStrategy beanResolver, String clientId, String clientApp, String coreDomainAlias, String viewDomainAlias, String pageId, Long refId) {
		this.beanResolver = beanResolver;
		this.clientId = clientId;
		this.clientApp = clientApp;
		this.viewDomainAlias = viewDomainAlias;
		this.coreDomainAlias = coreDomainAlias;
		this.pageId = pageId;
		this.refId = refId;
		
		this.controller = this.beanResolver.find(WebActionController.class);
		this.objectMapper = this.beanResolver.find(ObjectMapper.class);
	}
	
	public UnitTestPage(UnitTestPage parent) {
		this(parent.beanResolver, parent.clientId, parent.clientApp, parent.coreDomainAlias, parent.viewDomainAlias, parent.pageId, parent.refId);
		this.parent = parent;
	}
	
	public String getBaseURI() {
		return new StringBuilder()
				.append(this.clientId).append(Constants.SEPARATOR_URI.code)
				.append(this.clientApp).append(Constants.SEPARATOR_URI.code)
				.append(Constants.MARKER_URI_PLATFORM.code)
				.toString();
	}
	
	public String getViewRootDomainURI() {
		return new StringBuilder()
				.append(this.getBaseURI()).append(Constants.SEPARATOR_URI.code)
				.append(this.viewDomainAlias)
				.toString();
	}
	
	public String getCoreRootDomainURI() {
		return new StringBuilder()
				.append(this.getBaseURI()).append(Constants.SEPARATOR_URI.code)
				.append(this.coreDomainAlias)
				.toString();
	}
	
	public String getViewRootWithRefId() {
		return new StringBuilder()
				.append(Constants.SEPARATOR_URI.code)
				.append(this.viewDomainAlias)
				.append(Constants.SEPARATOR_URI_VALUE.code)
				.append(this.refId)
				.toString();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Param<T> getParam() {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.refId)
				.addAction(Action._get)
				.getMock();
		
		return (Param<T>) this.extractResponse(request, null);
	}
	
	public <T> Param<T> getParam(String pathFromViewRoot) {
		return this.getParam().findParamByPath(pathFromViewRoot);
	}
	
	public boolean isVisible() {
		return this.getParam().isVisible();
	}

	protected <T> Param<T> extractResponse(MockHttpServletRequest request) {
		return this.extractResponse(request, null, null);
	}
	
	protected <T> Param<T> extractResponse(MockHttpServletRequest request, String json) {
		return this.extractResponse(request, json, null);
	}
	
	protected <T> Param<T> extractResponse(MockHttpServletRequest request, String json, String endsWith) {
		return this.extractResponseByParamPath(this.controller.handlePost(request, json), endsWith);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Param<T> extractResponseByParamPath(Object response, String paramPathEndsWith) {
		Holder<MultiOutput> resp = (Holder<MultiOutput>) response;
		MultiOutput multiOutput = resp.getState();
		
		if (null == paramPathEndsWith) {
			return (Param<T>) multiOutput.getSingleResult();
		}
		
		for(Output<?> output : multiOutput.getOutputs()) {
			if (output.getValue() instanceof Param) {
				Param<?> param = (Param<?>) output.getValue();
				if (param.getPath().endsWith(paramPathEndsWith)) {
					return (Param<T>) param;
				}
			}
		}
		logger.debug(() -> "Unable to locate param in response ending with '" + paramPathEndsWith + "'. Response was: \n" + response);
		throw new RuntimeException("Unable to locate param in response ending with '" + paramPathEndsWith + "'. See debug logs for more information.");
	}
	
	protected Object doRequest(String uri, Long refId, String nestedPath, Action action) {
		return this.doRequest(uri, refId, nestedPath, action, null);
	}
	
	protected Object doRequest(String uri, Long refId, String nestedPath, Action action, Object payload) {
		MockHttpRequestBuilder request = MockHttpRequestBuilder.withUri(uri);
		
		if (null != refId) {
			request.addRefId(refId);
		}

		if (null != nestedPath) {
			request.addNested(nestedPath);
		}

		if (null != action) {
			request.addAction(action);
		}
		
		String sPayload = convertToStringPayload(payload);
		
		return this.controller.handlePost(request.getMock(), sPayload);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T doRequestAndGetLeafState(String path) {
		MockHttpServletRequest request = MockHttpRequestBuilder.withUri(this.getViewRootDomainURI())
				.addRefId(this.getRefId())
				.addNested(path)
				.addAction(Action._get)
				.getMock();
		
		Param<?> response = this.extractResponse(request);
		return (T) response.getLeafState();
	}
	
	protected String convertToStringPayload(Object payload) {
		if (null == payload) {
			return null;
		}
		
		final String sPayload;
		if (payload.getClass().equals(String.class)) {
			sPayload = (String) payload;
		} else {
			try {
				sPayload = this.objectMapper.writeValueAsString(payload);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("Failed to convert payload to string.", e);
			}
		}

		return sPayload;
	}
	
	protected Param<?> doRequestAndExtractParam(String uri, Long refId, String nestedPath, Action action, Object payload, String paramPathEndsWith) {
		Object response = this.doRequest(uri, refId, nestedPath, action, payload);
		return this.extractResponseByParamPath(response, paramPathEndsWith);
	}
}
