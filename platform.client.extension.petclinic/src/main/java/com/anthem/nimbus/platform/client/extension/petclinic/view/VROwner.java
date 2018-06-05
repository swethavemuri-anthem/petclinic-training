package com.anthem.nimbus.platform.client.extension.petclinic.view;


import javax.validation.constraints.NotNull;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Owner;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Type;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ButtonGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Header;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Domain(value = "ownerview", includeListeners = {ListenerType.websocket})
@MapsTo.Type(Owner.class)
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
public class VROwner {
 
    @Page(defaultPage=true)
    private VPAddEditOwner vpAddEditOwner;
 
    @Page
    private VPOwnerInfo vpOwnerInfo;
 
    @MapsTo.Type(Owner.class)
    @Getter @Setter
    public static class VPAddEditOwner {
 
         @Tile(size = Tile.Size.Large)
         private VTAddEditOwner vtAddEditOwner;
    }
 
    @Model
    @Getter @Setter
    public static class VTAddEditOwner {
 
        @Header(size=Header.Size.H3)
        private String addOwnerHeader;
 
        @Header(size=Header.Size.H3)
        private String editOwnerHeader;
 
        @Section
        private VSAddEditOwner vsAddEditOwner;
    }
 
    @Model
    @Getter @Setter
    public static class VSAddEditOwner {
        @Form(cssClass="twoColumn")
        private VFAddEditOwner vfAddEditOwner;
    }
 
    @Type(Owner.class)
    @Getter @Setter
    public static class VFAddEditOwner {
 
        @ButtonGroup(cssClass="text-sm-right pt-2 pb-2")
        private VBGAddOwnerButtonGrp vbgAddOwnerButtonGrp;
 
        @Path @TextBox @NotNull private String firstName;
 
        @Path @TextBox @NotNull private String lastName;
 
        @Path @TextBox private String address;
 
        @Path @TextBox @NotNull private String city;
 
        @Path @TextBox @NotNull private String telephone;
 
    }
 
    @Model
    @Getter @Setter
    public static class VBGAddOwnerButtonGrp {
 
        @Configs({
            @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_update"),
            @Config(url="/p/ownerlandingview/_nav?pageId=vpOwners")
        })
        @Button(style = Button.Style.PRIMARY,type=Button.Type.submit)
        private String submit;
 
        @Configs({
            @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_update"),
            @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_nav?pageId=vpOwnerInfo")
        })
        @Button(style = Button.Style.PRIMARY,type=Button.Type.submit)
        private String update;
 
        @Config(url="/p/ownerlandingview/_nav?pageId=vpOwners")
        @Button(style = Button.Style.PLAIN,type = Button.Type.reset)
        private String cancel;
    }
 
}
