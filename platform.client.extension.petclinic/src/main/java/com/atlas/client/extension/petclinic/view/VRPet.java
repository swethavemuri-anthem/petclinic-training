package com.atlas.client.extension.petclinic.view;

import java.time.LocalDate;

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
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Calendar;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Form;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Page;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.PickList;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ViewRoot;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional.Condition;
import com.atlas.client.extension.petclinic.core.CodeValueTypes.CatCategory;
import com.atlas.client.extension.petclinic.core.CodeValueTypes.DogCategory;
import com.atlas.client.extension.petclinic.core.CodeValueTypes.petType;
import com.atlas.client.extension.petclinic.core.Pet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Swetha Vemuri
 *
 */
@Domain(value = "petview", includeListeners = { ListenerType.websocket })
@MapsTo.Type(Pet.class)
@Repo(value=Repo.Database.rep_none, cache=Repo.Cache.rep_device)
@Getter @Setter @ToString(callSuper = true)
@ViewRoot(layout = "home")
public class VRPet {
	
	@Label("Pets")
	@Page(route="petview")
	private VPAllPets vpAllPets;
	
	@Label("Add/Edit Pet")
	@Page(route="petview")
	private VPAddEditPet vpAddEditPet;
	
	@Label("Pet Info")
	@Page(route="petview")
	private VPPetInfo vpPetInfo;
	
	@MapsTo.Type(Pet.class)
	@Getter @Setter
	public static class VPAddEditPet {
		
		 @Tile(size = Tile.Size.Large)
		 private VTAddEditPet vtAddEditPet;
	}
	
	@Model @Getter @Setter
	public static class VTAddEditPet {
    	
        @Section
        private VSAddEditPet vsAddEditPet;
	}
	
	@Model @Getter @Setter
	public static class VSAddEditPet {
		
		@Form(cssClass="twoColumn")
		private VFAddEditPet vfAddEditPet;
	}
	
	@Type(Pet.class)
	@Getter @Setter
	public static class VFAddEditPet {
		
		@ButtonGroup(cssClass="text-sm-right pt-2 pb-2")
		private VBGAddPetButtonGrp vbgAddPetButtonGrp;
		
		@Label("Pet's Name")
		@TextBox
		@NotNull
		@Path
		private String name;
		
		@Label("Date of Birth")
		@Calendar
		@Path 
		private LocalDate dob;
		
		@ComboBox(postEventOnChange=true)
		@Values(value = petType.class)
		@ValuesConditional(target="../category", condition= {
				@Condition(when="state== 'Dog'", then = @Values(value=DogCategory.class)),
				@Condition(when="state == 'Cat'", then = @Values(value=CatCategory.class))
		})
		@Path
		@Label("Type")
		private String type;
		
		@PickList(postEventOnChange=true,sourceHeader="Available Category", targetHeader="Selected Category",showTargetControls=true)
		@Path
		@Label("Category")
		private String[] category;
		
	}
	
	@Model @Getter @Setter
	public static class VBGAddPetButtonGrp {
		
		@Label("Submit")
		@Button(style = Button.Style.PRIMARY,type=Button.Type.submit, browserBack = true)
		@Config(url="/vpAddEditPet/vtAddEditPet/vsAddEditPet/vfAddEditPet/_update")
		private String submit;
	
		@Label("Cancel")
		@Button(style = Button.Style.PLAIN, type = Button.Type.reset, browserBack = true)
		private String cancel;
	}
	
}
