package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.antheminc.oss.nimbus.support.json.CustomLocalDateDeserializer;
import com.antheminc.oss.nimbus.support.json.CustomLocalDateSerializer;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.entity.AbstractEntity;
import com.antheminc.oss.nimbus.entity.AbstractEntity.IdString;
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
public class Visit extends AbstractEntity.IdLong{

	private static final long serialVersionUID = 1L;
	
//	private String vetId; //vetId
	
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
