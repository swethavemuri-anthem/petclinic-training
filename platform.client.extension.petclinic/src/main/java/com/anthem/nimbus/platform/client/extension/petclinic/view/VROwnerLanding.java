package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Owner;
import com.anthem.nimbus.platform.client.extension.petclinic.model.OwnerLineItem;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ButtonGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;

import lombok.Getter;
import lombok.Setter;
 
 
@Domain(value = "ownerlandingview", includeListeners = {ListenerType.websocket})
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter
@ViewRoot(layout = "home")
public class VROwnerLanding {
 
    @Page(defaultPage=true)
    private VPOwners vpOwners;
 
    @Model
    @Getter @Setter
    public static class VPOwners  {
 
        @Tile(imgSrc = "resources/icons/task.svg#Layer_1", size = Tile.Size.Medium)
        private VTOwners vtOwners;
    }
 
    @Model
    @Getter @Setter
    public static class VTOwners  {
 
        @Section
        private VSSearchOwnerCriteria vsSearchOwnerCriteria;
 
        @Section
        private VSOwners vsOwners;
    }
 
    @Model
    @Getter @Setter
    public static class VSSearchOwnerCriteria  {
 
        @Form(cssClass="twoColumn")
        @Path(linked=false)
        private VFSearchOwnerCriteria vfSearchOwnerCriteria;
    }
 
    @Model
    @MapsTo.Type(Owner.class)
    @Getter @Setter
    public static class VFSearchOwnerCriteria  {
 
        @Path @TextBox @NotNull @Label("First Name") private String firstName;
        @Path @TextBox @Label("Last Name") private String lastName;
        @ButtonGroup
        private VBGSearchOwner vbgSearchOwner;
    }
 
    @Model
    @Getter @Setter
    public static class VBGSearchOwner {
        @Configs({
            @Config(url="/vpOwners/vtOwners/vsSearchOwnerCriteria/vfSearchOwnerCriteria/_update"),
            @Config(url="/vpOwners/vtOwners/vsOwners/owners.m/_process?fn=_set&url=/p/owner/_search?fn=example")
        })
        @Button(style=Button.Style.PRIMARY,type=Button.Type.submit)
        private String search;
 
        @Configs({
             @Config(url="/p/ownerview/_new")
        })
        @Button(style=Button.Style.SECONDARY)
        @Label("Add Owner")
        private String addOwner;
    }
 
    @Model
    @Getter @Setter
    public static class VSOwners  {
 
        @MapsTo.Path(linked=false)
        @Config(url="/p/ownerlandingview/vpOwners/vtOwners/vsOwners/owners.m/_process?fn=_set&url=/p/owner/_search?fn=example")
        @Grid(onLoad=true, pageSize = "7")
        @Label("Owners")
        private List<OwnerLineItem> owners;
    }
}
