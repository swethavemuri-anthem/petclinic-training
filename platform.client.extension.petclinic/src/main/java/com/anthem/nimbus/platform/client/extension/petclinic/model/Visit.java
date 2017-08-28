package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
 * @author Sandeep Mantha
 *
 */

@Domain(value="visit", includeListeners={ListenerType.persistence, ListenerType.update}) 
@Repo(value=Database.rep_mongodb, cache=Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
public class Visit extends IdString{

	private static final long serialVersionUID = 1L;
	
	private String vetId; //vetId
	
	private String petId; //petId
	
	@JsonDeserialize(using = CustomLocalDateDeserializer.class) 
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private LocalDate appointment;
	
	private String reasonForVisit;
	
	private String visitOutcome;
	
	private String visitNote;
	
	private String status; // change to Status enum. Find out what are the possible values.
	
	private String veterinarianId;
}
