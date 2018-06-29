package com.atlas.client.extension.petclinic.view;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Type;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button.Style;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CardDetail;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FieldValue;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Hints;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.extension.ActivateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Owner;
import com.atlas.client.extension.petclinic.core.PetLineItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Model
@Getter @Setter
public class VPOwnerInfo {
 
    @Tile(imgSrc="resources/icons/careplan.svg#Layer_1", size=Tile.Size.Large)
    private VTOwnerInfo vtOwnerInfo;
 
    @Model
    @Getter @Setter
    public static class VTOwnerInfo {
 
        @Section
        private VSOwnerInfo vsOwnerInfo;
 
        @Section(cssClass="contentBox bg-lightest")
        private VSPets vsPets;
        
        @Section
        private VSHistory vsHistory;
    }
 
    @Model @Getter @Setter
    public static class VSHistory {

    	@Form
    	private VFForm vfForm;
    }
    
    @Model @Getter @Setter
    public static class VFForm {
    	
    	@ActivateConditional(when = "state == null || state == 'inactive'", targetPath = {
    		"/../showHistory"
    	})
    	@ActivateConditional(when = "state == 'active'", targetPath = {
    		"/../hideHistory",
    		"/../callHistory"
    	})
    	private String gridVisibility;
    	
    	@Button(cssClass = "text-sm-right")
		@Config(url = "<!#this!>/../gridVisibility/_replace?rawPayload=\"active\"")
		@Label("Show Call History")
		@Hints(Hints.AlignOptions.Right)
		private String showHistory;

		@Button(cssClass = "text-sm-right")
		@Config(url = "<!#this!>/../gridVisibility/_replace?rawPayload=\"inactive\"")
		@Label("Hide Call History")
		@Hints(Hints.AlignOptions.Right)
		private String hideHistory;
		
		private CallHistory callHistory;
    }

    @Model @Getter @Setter
    @MapsTo.Type(Owner.class)
    public static class CallHistory {
    	
    	@Config(url = "<!#this!>.m/_process?fn=_set&url=/p/owner:<!/.m/id!>/calls/_get?b=$state")
		@Grid(onLoad = true, isTransient = true, pageSize = "5")
		@Label("Call History")
		@MapsTo.Path(linked = false)
		private List<CallLineItem> calls;
    }
    
    @Model
    @Getter @Setter
    public static class VSOwnerInfo {
 
        @CardDetail(cssClass="contentBox right-gutter bg-alternate mt-0")
        private VCDOwnerInfo vcdOwnerInfo;
 
    }
 
    @Model
    @Getter @Setter
    public static class VCDOwnerInfo {
 
        @CardDetail.Body
        private VCDBOwner vcdbOwner;
    }
 
    @Type(Owner.class)
    @Getter @Setter
    public static class VCDBOwner {
 
        @Path @FieldValue(cols="2") @Label("First Name") private String firstName;
        @Path @FieldValue @Label("Last Name") private String lastName;
 
        @FieldValue(type=FieldValue.Type.Divider)
      
        private String divider2;
 
        @Path @FieldValue @Label("Address") private String address;
        @Path @FieldValue @Label("City") private String city;
        @Path @FieldValue @Label("Telephone") private String telephone;
    }
 
    @Model
    @Getter @Setter
    public static class VSPets {
    	
        @Configs({
            @Config(url="/p/petview/_new?fn=_initEntity&target=/.m/ownerId&json=\"<!/.m/id!>\"&target=/.m/ownerName&json=\"<!/.m/firstName!> <!/.m/lastName!>\"")
        })
        @Button(style=Style.SECONDARY)
        @Label("Add Pet")
        private String addPet;
 
        @Path(linked=false)
        @Config(url="/vpOwnerInfo/vtOwnerInfo/vsPets/pets.m/_process?fn=_set&url=/p/pet/_search?fn=query&where=pet.ownerId.eq(<!/.m/id!>)")
        @Grid(onLoad=true, pageSize = "7")
        @Label("Pets")
        private List<PetLineItem> pets;
 
    }
 
}