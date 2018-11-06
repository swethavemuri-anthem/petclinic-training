package com.atlas.client.extension.petclinic.core;

import java.util.List;
import java.util.Optional;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rakesh Patel
 *
 */
@Domain(value="veterinarian", includeListeners={ListenerType.persistence, ListenerType.update}) 
@Repo(value=Database.rep_mongodb, cache=Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
public class Veterinarian extends AbstractEntity.IdLong {
	
	private static final long serialVersionUID = 1L;

	private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	private String speciality;
	
	private List<Long> assignedPets;
	
	public String getFullName() {
		return firstName + ' ' + lastName;		
	}
	
	public int assignedPetCount() {
		return Optional.ofNullable(this.assignedPets).get().size();
	}
}
