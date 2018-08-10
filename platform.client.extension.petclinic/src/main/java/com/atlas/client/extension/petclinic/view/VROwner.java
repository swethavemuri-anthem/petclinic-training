package com.atlas.client.extension.petclinic.view;


import javax.validation.constraints.NotNull;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Type;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Model.Param.Values;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ButtonGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CheckBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Header;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.GROUP_1;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.GROUP_3;
import com.antheminc.oss.nimbus.domain.defn.extension.ValidateConditional.ValidationScope;
import com.atlas.client.extension.petclinic.core.CodeValueTypes.OwnerNotificationPreferences;
import com.atlas.client.extension.petclinic.core.Owner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Domain(value = "ownerview", includeListeners = {ListenerType.websocket})
@MapsTo.Type(Owner.class)
@Repo(value = Repo.Database.rep_none, cache = Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
@ViewRoot(layout = "home")
public class VROwner {
 
	@Label("Add Owner")
    @Page(defaultPage=true)
    private VPAddEditOwner vpAddEditOwner;
 
	@Label("Owner Info")
    @Page
    private VPOwnerInfo vpOwnerInfo;
 
    @MapsTo.Type(Owner.class)
    @Getter @Setter
    public static class VPAddEditOwner {
 
         @Tile
         private VTAddEditOwner vtAddEditOwner;
    }
 
    @Model @Getter @Setter
    public static class VTAddEditOwner {
 
        @Header(size=Header.Size.H3)
        private String addOwnerHeader;
 
        @Header(size=Header.Size.H3)
        private String editOwnerHeader;
 
        @Section
        private VSAddEditOwner vsAddEditOwner;
    }
 
    @Model @Getter @Setter
    public static class VSAddEditOwner {
    	
        @Form(cssClass="twoColumn")
        private VFAddEditOwner vfAddEditOwner;
    }
 
    @Type(Owner.class)
    @Getter @Setter
    public static class VFAddEditOwner {
 
        @ButtonGroup(cssClass="text-sm-right pt-2 pb-2")
        private VBGAddOwnerButtonGrp vbgAddOwnerButtonGrp;
        
        @Label("First Name")
    	@TextBox
    	@NotNull
    	@Path
    	private String firstName;
    	
    	@Label("Last Name")
        @TextBox 
        @NotNull
        @Path
        private String lastName;
        
    	@Label("Nickname")
        @NotNull(groups = { GROUP_1.class })
        @TextBox
        private String nickname;
        
        @Label("Does user prefer nickname?")
        @CheckBox(postEventOnChange = true)
        @ValidateConditional(when = "state == true", scope = ValidationScope.SIBLING, targetGroup = GROUP_1.class)
        private boolean shouldUseNickname;
        
        @Label("Notification Preference")
        @NotNull
        @ComboBox(postEventOnChange = true)
        @Values(OwnerNotificationPreferences.class)
        @ValidateConditional(when = "state == 'phyiscal_mail'", scope = ValidationScope.CHILDREN, targetGroup = GROUP_1.class, targetPath = { 
        		"../address",
        		"../city",
        		"../state",
        		"../zip",
        		"../telephone",
        		"../email"
        })
        @ValidateConditional(when = "state == 'email'", scope = ValidationScope.CHILDREN, targetGroup = GROUP_3.class, targetPath = {
        		"../address",
        		"../city",
        		"../state",
        		"../zip",
        		"../telephone",
        		"../email"
        })
        private String notificationPreference;
        
        @Path 
    	@TextBox 
    	@Label("Address")
    	@NotNull(groups = { GROUP_1.class })
    	private String address;
    	 
    	@Path 
        @TextBox 
        @NotNull(groups = { GROUP_1.class })
        @Label("City")
        private String city;
    	
    	@Path 
        @TextBox 
        @NotNull(groups = { GROUP_1.class })
        @Label("State")
        private String state;
    	
    	@Path 
        @TextBox 
        @NotNull(groups = { GROUP_1.class })
        @Label("Zip Code")
        private String zip;
        
        @Path 
        @TextBox 
        @NotNull
        @Label("Telephone") 
        private String telephone;
        
        @Path 
        @TextBox 
        @NotNull(groups = { GROUP_3.class })
        @Label("Email Address") 
        private String email;
    }
 
    @Model @Getter @Setter
    public static class VBGAddOwnerButtonGrp {
 
    	@Label("Submit")
        @Button(style = Button.Style.PRIMARY, type=Button.Type.submit)
        @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_update")
        @Config(url="/p/ownerlandingview/_nav?pageId=vpOwners")
        private String submit;
 
    	@Label("Update")
        @Button(style = Button.Style.PRIMARY, type=Button.Type.submit)
        @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_update")
        @Config(url="/vpAddEditOwner/vtAddEditOwner/vsAddEditOwner/vfAddEditOwner/_nav?pageId=vpOwnerInfo")
        private String update;
 
    	@Label("Cancel")
        @Button(style = Button.Style.PLAIN, type = Button.Type.reset) 
        @Config(url="/p/ownerlandingview/_nav?pageId=vpOwners")
        private String cancel;
    }
 
}
