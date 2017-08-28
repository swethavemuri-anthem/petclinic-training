package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Pet;
import com.anthem.nimbus.platform.spec.serializer.CustomLocalDateSerializer;
import com.anthem.oss.nimbus.core.domain.definition.Domain;
import com.anthem.oss.nimbus.core.domain.definition.Domain.ListenerType;
import com.anthem.oss.nimbus.core.domain.definition.Execution.Config;
import com.anthem.oss.nimbus.core.domain.definition.Executions.Configs;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo.Path;
import com.anthem.oss.nimbus.core.domain.definition.MapsTo.Type;
import com.anthem.oss.nimbus.core.domain.definition.Model;
import com.anthem.oss.nimbus.core.domain.definition.Repo;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Button;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.ButtonGroup;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.ComboBox;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Form;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.InputDate;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Page;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Section;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.TextBox;
import com.anthem.oss.nimbus.core.domain.definition.ViewConfig.Tile;
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
public class VRPet {
	
	@Page(route="petview",defaultPage=true)
	private VPAddEditPet vpAddEditPet;
	
	@Page(route="petview")
	private VPPetInfo vpPetInfo;
	
	@MapsTo.Type(Pet.class)
	@Getter @Setter
	public static class VPAddEditPet {
		
		 @Tile(title="Add/Edit PET", size = Tile.Size.Large)
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
		private String name;
		
		@Path @InputDate
		@JsonSerialize(using=CustomLocalDateSerializer.class)
		private LocalDate dob;
		
		@ComboBox @MapsTo.Path 
		@Model.Param.Values(url="Anthem/icr/p/staticCodeValue/_search?fn=lookup&where=staticCodeValue.paramCode.eq('/vetSpecialty')")
		private String type;
		
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
