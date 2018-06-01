package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.time.LocalDate;
import java.util.List;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Pet;
import com.anthem.nimbus.platform.client.extension.petclinic.model.VisitLineItem;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Type;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CardDetail;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FieldValue;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Section;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Tile;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Swetha Vemuri
 *
 */
@Model
@Getter @Setter
public class VPPetInfo {
	
	@Tile(title="PET INFO", imgSrc="resources/icons/careplan.svg#Layer_1", size=Tile.Size.Large)
	private VTPetInfo vtPetInfo;
	
	@Model
	@Getter @Setter
	public static class VTPetInfo {
		
		@Section
		private VSPetInfo vsPetInfo;
		
		@Section(cssClass="contentBox bg-lightest")
		private VSVisits vsVisits;
		
	}
	
	@Model
	@Getter @Setter
	public static class VSPetInfo {
		
		@CardDetail(cssClass="contentBox right-gutter bg-alternate mt-0")
		private VCDPetInfo vcdPetInfo;
		
	}
	
	@Model
	@Getter @Setter
	public static class VCDPetInfo {
		
		@CardDetail.Body
		private VCDBPet vcdbPet;
	}
	
	@Type(Pet.class)
	@Getter @Setter
	public static class VCDBPet {
		
		@Path @FieldValue(cols="2") 
		private String name;	
		
		@Path @FieldValue 
		private LocalDate dob;
		
		@Path @FieldValue 
		private String type;
		
	}
	
	@Model
	@Getter @Setter
	public static class VSVisits {
		
		@Configs({
			@Config(url="/p/visitview/_new?fn=_initEntity&target=/.m/petId&json=\"<!/.m/id!>\"")
	    })
	    @Button(imgSrc = "add.svg", cssClass ="btn btn-icon green")
	    private String addVisit;
		
		@Path(linked=false)
		@Config(url="/vpPetInfo/vtPetInfo/vsVisits/visits.m/_process?fn=_set&url=/p/visit/_search?fn=query&where=visit.petId.eq('<!/.m/id!>')")
		@Grid(onLoad=true, isTransient = true, pageSize = "7")
		private List<VisitLineItem> visits;
	}
	
}