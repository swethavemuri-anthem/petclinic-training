 package com.atlas.client.extension.petclinic.view.owner;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Type;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button.Style;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CardDetail;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FieldValue;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FieldValueGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Hints;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Paragraph;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.extension.ActivateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Owner;
import com.atlas.client.extension.petclinic.view.pet.PetLineItem;

import lombok.Getter;
import lombok.Setter;

@Model @Getter @Setter
public class VPOwnerInfo {
 
    @Tile(imgSrc="resources/icons/careplan.svg#Layer_1", size=Tile.Size.Large)
    private VTOwnerInfo vtOwnerInfo;
  
    @Model @Getter @Setter
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
    	
	    	@Label("Hello <!/.d/.m/firstName!> <!/.d/.m/lastName!> !! Welcome to <!#env.petclinic.clinicname!>. Below is your call history.")
	    	@Paragraph(cssClass="font-weight-bold")
	    	private String headerCallSection;
    	
    		private CallSection callSection;
    }

    @Model @Getter @Setter
    public static class CallSection {
    	
	    	@ActivateConditional(when = "state == null || state == 'inactive'", targetPath = {
	    		"/../showHistory"
	    	})
	    	@ActivateConditional(when = "state == 'active'", targetPath = {
	    		"/../hideHistory",
	    		"/../gridWrapper"
	    	})
	    	private String gridVisibility;
    	
	    	@Label("Show Call History")
	    	@Button(cssClass = "text-sm-right")
			@Hints(Hints.AlignOptions.Right)
	    	@Config(url = "<!#this!>/../gridVisibility/_replace?rawPayload=\"active\"")
		private String showHistory;

	    	@Label("Hide Call History")
		@Button(cssClass = "text-sm-right")
		@Hints(Hints.AlignOptions.Right)
		@Config(url = "<!#this!>/../gridVisibility/_replace?rawPayload=\"inactive\"")
		private String hideHistory;
		
		private CallHistoryGridWrapper gridWrapper;
    }
    
    @Type(Owner.class)
    @Model @Getter @Setter
    public static class CallHistoryGridWrapper {
    	
    		@Label("Call History")
		@Grid(onLoad = true, isTransient = true, pageSize = "5")
		@Path
		private List<CallLineItem> calls;
    }
    
    @Model @Getter @Setter
    public static class VSOwnerInfo {
 
        @CardDetail(cssClass="contentBox right-gutter bg-alternate mt-0")
        private VCDOwnerInfo vcdOwnerInfo;
 
    }
 
    @Model @Getter @Setter
    public static class VCDOwnerInfo {
 
        @CardDetail.Body
        private VCDBOwner vcdbOwner;
    }
 
    @Type(Owner.class)
    @Getter @Setter
    public static class VCDBOwner {
 
    		@Label("First Name")
        @FieldValue(cols="2")
    		@Path
        private String firstName;
        
        @Label("Last Name")
        @FieldValue
        @Path
        private String lastName;
 
        @FieldValue(type=FieldValue.Type.Divider)
        private String divider2;
        
        @Label("Address Group")
        @FieldValueGroup()
        private AddressGroup addressGroup;
        
        
        @Label("Telephone")
        @FieldValue
        @Path
        private String telephone;
    }
    
    @Type(Owner.class)
    @Getter @Setter
    public static class AddressGroup {
    	
    		@Label("Address")
        @FieldValue(cols="1", showName=false)
        @Path
        private String address;

        @Label("City")
        @FieldValue(cols="1", showName=false)
        @Path
        private String city;
        
        @Label("State")
        @FieldValue(cols="1", showName=false)
        @Path
        private String state;
        
        @Label("Zip")
        @FieldValue(cols="1", showName=false)
        @Path
        private String zip;
    	
    }
    
    
 
    @Model @Getter @Setter
    public static class VSPets {
    	
    		@Label("Add Pet")
    		@Button(style=Style.SECONDARY)
        @Config(url="/p/petview/_new?fn=_initEntity&target=/.m/ownerId&json=\"<!/.m/id!>\"&target=/.m/ownerName&json=\"<!/.m/firstName!> <!/.m/lastName!>\"")
        @Config(url="/p/petview/_nav?pageId=vpAddEditPet")
        private String addPet;
 
    		@Label("Pets")
    		@Grid(onLoad = true, pageSize = "7")
        @Path(linked = false)
        @Config(url="<!#this!>/.m/_process?fn=_set&url=/p/pet/_search?fn=query&where=pet.ownerId.eq(<!/../.m/id!>)")
        private List<PetLineItem> pets;
 
    }
 
}