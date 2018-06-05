package com.atlas.client.extension.petclinic.core;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;

@MapsTo.Type(Owner.class)
@Getter @Setter
public class OwnerLineItem {
 
    @Path @GridColumn(hidden=true) private Long id;
 
    @Path @GridColumn @Label("First Name") private String firstName;
 
    @Path @GridColumn @Label("Last Name") private String lastName;
 
    @Path("city") @GridColumn  @Label("Owner City") private String ownerCity;
 
    @Path @GridColumn @Label("Telephone") private String telephone;   
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @Model
    @Getter @Setter
    public static class VLMCaseItemLinks {
        @Config(url="/p/ownerview:<!/../id!>/_get")
        @Link(imgSrc="edit.png") @Label("Edit") private String edit;
     
        @Configs({
            @Config(url="/p/ownerview:<!/../id!>/_get"),
            @Config(url="/p/ownerview:<!/../id!>/_nav?pageId=vpOwnerInfo")
        })
        @Link(imgSrc="task.svg") @Label("Owner Info") private String ownerInfo;
    }
}