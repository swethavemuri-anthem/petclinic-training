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
package com.atlas.client.extension.petclinic.scenariotests.owner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.atlas.client.extension.petclinic.core.Owner;
import com.atlas.client.extension.petclinic.pageobject.model.OwnerLandingUnitTestPage;
import com.atlas.client.extension.petclinic.scenariotests.AbstractPetclinicSpringTest;
import com.atlas.client.extension.petclinic.view.owner.OwnerLineItem;
import com.atlas.client.extension.petclinic.view.owner.VPAddEditOwner.VFAddEditOwner;

/**
 * @author Tony Lopez
 *
 */
public class OwnerLandingPageTests extends AbstractPetclinicSpringTest {

	private OwnerLandingUnitTestPage ownerLandingPage;
	
	@Before
	public void init() {
		super.init();
		this.ownerLandingPage = this.homepage.clickGoToOwners();
	}
	
	@Test
	public void testAddMultipleOwnersToGrid() {
		VFAddEditOwner owner1 = this.getDummyOwner("Big", "Bird");
		VFAddEditOwner owner2 = this.getDummyOwner("Cookie", "Monster");
		
		// Add an owner and validate it was added in the grid.
		this.ownerLandingPage.clickAddOwner().fillForm(owner1).clickSubmit();
		List<OwnerLineItem> owners = this.ownerLandingPage.getOwners();
		Assert.assertEquals(1, owners.size());
		Assert.assertEquals(owner1.getFirstName(), owners.get(0).getFirstName());
		Assert.assertEquals(owner1.getLastName(), owners.get(0).getLastName());
		
		// Add a second owner and validate it was added in the grid.
		this.ownerLandingPage.clickAddOwner().fillForm(owner2).clickSubmit();
		owners = this.ownerLandingPage.getOwners();
		Assert.assertEquals(2, owners.size());
		Assert.assertEquals(owner1.getFirstName(), owners.get(0).getFirstName());
		Assert.assertEquals(owner1.getLastName(), owners.get(0).getLastName());
		Assert.assertEquals(owner2.getFirstName(), owners.get(1).getFirstName());
		Assert.assertEquals(owner2.getLastName(), owners.get(1).getLastName());
	}
	
	@Test
	public void testOwnersGridDataDisplay() {
		
		// Create and add an owner to the db.
		Owner expected = new Owner();
		expected.setId(1L);
		expected.setFirstName("Jane");
		expected.setLastName("Doe");
		expected.setAddress("742 Evergreen Terrace");
		expected.setCity("Springfield");
		expected.setTelephone("867-5309");
		this.mongo.insert(expected, CollectionNames.OWNER);
		
		// Validate owners are being displayed in the grid.
		List<OwnerLineItem> actual = this.ownerLandingPage.getOwners();
		assertThat(actual).isNotNull();
		assertThat(actual.size()).isEqualTo(1);
		assertThat(actual.get(0).getId()).isEqualTo(expected.getId());
		assertThat(actual.get(0).getFirstName()).isEqualTo(expected.getFirstName());
		assertThat(actual.get(0).getLastName()).isEqualTo(expected.getLastName());
		assertThat(actual.get(0).getOwnerCity()).isEqualTo(expected.getCity());
		assertThat(actual.get(0).getTelephone()).isEqualTo(expected.getTelephone());
	}
	
	private VFAddEditOwner getDummyOwner(String firstName, String lastName) {
		VFAddEditOwner owner = new VFAddEditOwner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		return owner;
	}
}
