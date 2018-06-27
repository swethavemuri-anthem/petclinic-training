package com.atlas.client.extension.petclinic.core;

import java.time.LocalDate;

import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.OwnerLineItem.VLMCaseItemLinks;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Andrew Jo
 *
 */
@MapsTo.Type(Pet.class)
@Getter @Setter
public class PetLineItemOwnerLanding {
	
    @Path @GridColumn(hidden=true)
    private Long id;
 
    @Path @GridColumn
    @Label("Pet's name")
    private String name;
	
}
