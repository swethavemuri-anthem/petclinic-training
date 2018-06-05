package com.atlas.client.extension.petclinic.view;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Calendar;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.InputDate;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Pet;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Swetha Vemuri
 *
 */

@Domain(value = "petview", includeListeners = {ListenerType.websocket})
@MapsTo.Type(Pet.class)
@Repo(value=Repo.Database.rep_none,cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper=true)
@ViewRoot(layout = "home")
public class VRPet {
	
	@Page(route="petview",defaultPage=true)
	private VPAddEditPet vpAddEditPet;
	
	@Page(route="petview")
	private VPPetInfo vpPetInfo;
	
	@MapsTo.Type(Pet.class)
	@Getter @Setter
	public static class VPAddEditPet {
		
		 @Tile( size = Tile.Size.Large)
		 private VTAddEditPet vtAddEditPet;
	}
	
	@Model
	@Getter @Setter
	public static class VTAddEditPet {
    	
        @Section
        private VSAddEditPet vsAddEditPet;
	}
	
	@Model
	@Getter @Setter
	public static class VSAddEditPet {
		@Form(cssClass="twoColumn")
		private VFAddEditPet vfAddEditPet;
	}
	
	@Type(Pet.class)
	@Getter @Setter
	public static class VFAddEditPet {
		
		@ButtonGroup(cssClass="text-sm-right pt-2 pb-2")
		private VBGAddPetButtonGrp vbgAddPetButtonGrp;
		
		@Path @TextBox @NotNull 
		@Label("Name")
		private String name;
		
		@Path 
		@Calendar
		@Label("Date of Birth")
		private LocalDate dob;
		
//		@ComboBox @MapsTo.Path 
//		@Model.Param.Values(url="Anthem/icr/p/staticCodeValue/_search?fn=lookup&where=staticCodeValue.paramCode.eq('/vetSpecialty')")
//		private String type;
		
	}
	
	@Model
	@Getter @Setter
	public static class VBGAddPetButtonGrp {
		
		@Configs({
			@Config(url="/vpAddEditPet/vtAddEditPet/vsAddEditPet/vfAddEditPet/_update"),
			@Config(url="/p/ownerview:<!/.m/ownerId!>/_get"),
			@Config(url="/p/ownerview:<!/.m/ownerId!>/_nav?pageId=vpOwnerInfo")
		})
		@Button(style = Button.Style.PRIMARY,type=Button.Type.submit)
		private String submit;
	
		@Config(url="/p/ownerview:<!/.m/ownerId!>/_nav?pageId=vpOwnerInfo")
		@Button(style = Button.Style.PLAIN,type = Button.Type.reset)
		private String cancel;
	}
	
}
