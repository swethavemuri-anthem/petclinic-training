package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;

import com.antheminc.oss.nimbus.support.json.CustomLocalDateDeserializer;
import com.antheminc.oss.nimbus.support.json.CustomLocalDateSerializer;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.entity.AbstractEntity.IdString;

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
