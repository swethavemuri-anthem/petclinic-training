package com.anthem.nimbus.platform.client.extension.petclinic.model;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;

import lombok.Getter;
import lombok.Setter;

import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;

@MapsTo.Type(Owner.class)
@Getter @Setter
public class OwnerLineItem {
 
    @Path @GridColumn(hidden=true) private Long id;
 
    @Path @GridColumn private String firstName;
 
    @Path @GridColumn private String lastName;
 
    @Path("city") @GridColumn private String ownerCity;
 
    @Path @GridColumn private String telephone;
 
//    @Config(url="/p/ownerview:<!/id!>/_get")
//    @Link(imgSrc="edit.png") private String edit;
// 
//    @Configs({
//        @Config(url="/p/ownerview:<!/id!>/_get"),
//        @Config(url="/p/ownerview:<!/id!>/_nav?pageId=vpOwnerInfo")
//    })
//    @Link(imgSrc="task.svg") private String ownerInfo;
    
    
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @Model
    @Getter @Setter
    public static class VLMCaseItemLinks {
        @Config(url="/p/ownerview:<!/../id!>/_get")
        @Link(imgSrc="edit.png") private String edit;
     
        @Configs({
            @Config(url="/p/ownerview:<!/../id!>/_get"),
            @Config(url="/p/ownerview:<!/../id!>/_nav?pageId=vpOwnerInfo")
        })
        @Link(imgSrc="task.svg") private String ownerInfo;
    }
}