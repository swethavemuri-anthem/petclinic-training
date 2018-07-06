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
package com.atlas.client.extension.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.atlas.client.extension.petclinic.core.PetLineItem;
import com.atlas.client.extension.petclinic.pageobject.model.OwnerInfoUnitTestPage;
import com.atlas.client.extension.petclinic.test.AbstractPetclinicSpringTest;
import com.atlas.client.extension.petclinic.view.CallLineItem;

/**
 * @author Tony Lopez
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OwnerInfoPageTests extends AbstractPetclinicSpringTest {
	
	private OwnerInfoUnitTestPage ownerInfoPage;
	
	@Before
	public void t0_init() {
		super.t0_init();
		this.ownerInfoPage = new OwnerInfoUnitTestPage(this.beanResolver, CLIENT_ID, CLIENT_ORG, 1L);
	}
	
	@Test
	public void t01_callsGrid_dataValidation() {
		
		// Validate pets data
		List<PetLineItem> pets = this.ownerInfoPage.getPets();
		assertThat(pets).isNotNull();
		assertThat(pets.size()).isEqualTo(1);
		assertThat(pets.get(0).getName()).isEqualTo("Fido");
		assertThat(pets.get(0).getPetType()).isEqualTo("Dog - Chihuahua");
		assertThat(pets.get(0).getOwnerName()).isEqualTo("Jane Doe");
		assertThat(pets.get(0).getDob()).isEqualTo(LocalDate.of(2001, 01, 21));
		
		// Validate the call data
		List<CallLineItem> calls = this.ownerInfoPage.getCalls();
		assertThat(calls).isNotNull();
		assertThat(calls.size()).isEqualTo(2);
		assertThat(calls.get(0).isReceived()).isTrue();
		assertThat(calls.get(0).getReason()).isEqualTo("Schedule pet haircut");
		assertThat(calls.get(1).isReceived()).isFalse();
		assertThat(calls.get(1).getReason()).isEqualTo("N/A");
	}
	
	@Test
	public void t02_callsGrid_toggleActive() {
		
		// invoke a get call on grid to initialize the data.
		this.ownerInfoPage.getCalls();
		
		// Validate calls grid and toggle buttons are appropriately active/inactive.
		assertThat(this.ownerInfoPage.getParam_calls().isActive()).isFalse();
		assertThat(this.ownerInfoPage.getParam_hideCallHistory().isActive()).isFalse();
		assertThat(this.ownerInfoPage.getParam_showCallHistory().isActive()).isTrue();
		
		// click show history. calls grid and hide button should be active. show button should be inactive.
		this.ownerInfoPage.clickShowCallHistory();
		assertThat(this.ownerInfoPage.getParam_calls().isActive()).isTrue();
		assertThat(this.ownerInfoPage.getParam_hideCallHistory().isActive()).isTrue();
		assertThat(this.ownerInfoPage.getParam_showCallHistory().isActive()).isFalse();
		
		// click hide history. show button should be active. calls grid and hide button should be inactive.
		this.ownerInfoPage.clickHideCallHistory();
		assertThat(this.ownerInfoPage.getParam_calls().isActive()).isFalse();
		assertThat(this.ownerInfoPage.getParam_hideCallHistory().isActive()).isFalse();
		assertThat(this.ownerInfoPage.getParam_showCallHistory().isActive()).isTrue();
	}
}
