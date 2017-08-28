package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;

import com.anthem.nimbus.platform.spec.serializer.CustomLocalDateDeserializer;
import com.anthem.nimbus.platform.spec.serializer.CustomLocalDateSerializer;
import com.anthem.oss.nimbus.core.domain.definition.Domain;
import com.anthem.oss.nimbus.core.domain.definition.Domain.ListenerType;
import com.anthem.oss.nimbus.core.domain.definition.Repo;
import com.anthem.oss.nimbus.core.domain.definition.Repo.Cache;
import com.anthem.oss.nimbus.core.domain.definition.Repo.Database;
import com.anthem.oss.nimbus.core.entity.AbstractEntity.IdString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rakesh Patel
 *
 */
@Domain(value="pet", includeListeners={ListenerType.persistence, ListenerType.update}) 
@Repo(value=Database.rep_mongodb, cache=Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
public class Pet extends IdString {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@JsonDeserialize(using = CustomLocalDateDeserializer.class) 
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private LocalDate dob;
	
	private String type;

	private String ownerId;

}
