package com.anthem.nimbus.platform.client.extension.petclinic.view;

import java.util.List;

import com.anthem.nimbus.platform.client.extension.petclinic.model.Owner;
import com.anthem.nimbus.platform.client.extension.petclinic.model.PetLineItem;
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
 
        @Path @FieldValue(cols="2") private String firstName;
        @Path @FieldValue private String lastName;
 
        @FieldValue(type=FieldValue.Type.Divider)
        private String divider2;
 
        @Path @FieldValue private String address;
        @Path @FieldValue private String city;
        @Path @FieldValue private String telephone;
    }
 
    @Model
    @Getter @Setter
    public static class VSPets {
    	
        @Configs({
            @Config(url="/p/petview/_new?fn=_initEntity&target=/.m/ownerId&json=<!/.m/id!>")
        })
        @Button(imgSrc = "add.svg", cssClass ="btn btn-icon green")
        private String addPet;
 
        @Path(linked=false)
        @Config(url="/vpOwnerInfo/vtOwnerInfo/vsPets/pets.m/_process?fn=_set&url=/p/pet/_search?fn=query&where=pet.ownerId.eq(<!/.d/.m/id!>)")
        @Grid(onLoad=true, pageSize = "7")
        private List<PetLineItem> pets;
 
    }
 
}