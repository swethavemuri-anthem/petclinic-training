package com.anthem.nimbus.platform.client.extension.petclinic.model;

import java.time.LocalDate;

import com.anthem.nimbus.platform.client.extension.petclinic.model.OwnerLineItem.VLMCaseItemLinks;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

@MapsTo.Type(Pet.class)
@Getter @Setter
public class PetLineItem {
	
    @Path @GridColumn(hidden=true)
    private Long id;
 
    @Path @GridColumn
    private String name;
 
//    @Path @GridColumn
//    private LocalDate dob;
 
    @Path("type") @GridColumn
    private String petType;
 
//    @Configs({
//        @Config(url="/p/petview:<!/id!>/_get")
//    })
//    @Link()
//    private String editPet;
// 
//    @Configs({
//        @Config(url="/p/petview:<!/id!>/_get"),
//        @Config(url="/p/petview:<!/id!>/_nav?pageId=vpPetInfo")
//    })
//    @Link() private String viewVisits;
    
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @Model
    @Getter @Setter
    public static class VLMCaseItemLinks {
    	
        @Configs({
            @Config(url="/p/petview:<!/../id!>/_get")
        })
        @Link()
        @Label("Edit Pet")
        private String editPet;
     
        @Configs({
            @Config(url="/p/petview:<!/../id!>/_get"),
            @Config(url="/p/petview:<!../id!>/_nav?pageId=vpPetInfo")
        })
        @Link() 
        @Label("View Visits")
        private String viewVisits;
    	
    }
	
}
