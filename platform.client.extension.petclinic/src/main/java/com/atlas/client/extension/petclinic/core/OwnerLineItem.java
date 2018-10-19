package com.atlas.client.extension.petclinic.core;

import java.math.BigDecimal;
import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Executions.Configs;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridRowBody;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Style;
import com.antheminc.oss.nimbus.domain.defn.extension.StyleConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;

import lombok.Getter;
import lombok.Setter;

@MapsTo.Type(Owner.class)
@Getter @Setter
public class OwnerLineItem {
 
    @Path 
    @GridColumn(hidden=true) 
    private Long id;
 
    @Path
    @GridColumn(hidden=true) 
    @Label("First Name") 
    private String firstName;
    
    @GridRowBody
    private ExpandedRowContent expandedRowContent;
    
    @Model @Getter @Setter
    public static class ExpandedRowContent {
    	
    	@Path(linked=false)
		@Config(url="<!#this!>.m/_process?fn=_set&url=/p/pet/_search?fn=example")
        @Grid(onLoad= true)
        @Label("Pets")
        private List<PetLineItemOwnerLanding> pets;
    }
 
    @Path 
    @GridColumn(applyValueStyles = true) 
    @Label("Last Name") 
    private String lastName;
    
    @Label("Nickname")
    @GridColumn
    @Path
    private String nickname;

    @GridColumn(hidden = true)
    @Path
    @StyleConditional(targetPath = "/../nickname", condition = {
    @StyleConditional.Condition(when = "state == true", then = @Style(cssClass = "highlight-nickname"))
    })
    private Boolean shouldUseNickname;
    
//  @GridColumn(applyValueStyles = true) 
    @Path 
    @GridColumn
    @StyleConditional(targetPath = "/../lastName", condition = {
    @StyleConditional.Condition(when = "state == 'Inactive'", then = @Style(cssClass = "inactiveUser"))
    })
    @Label("Status")
    private String status;
    
//  @GridColumn(applyValueStyles = true) 
    @Path 
    @GridColumn(hidden=true) 
    @Label("Notification Preference") 
    private String notificationPreference;
 
//	@GridColumn  
    @Path("city") 
    @GridColumn(hidden=true)  
    @Label("Owner City") 
    private String ownerCity;
 
//	@GridColumn  
    @Path 
    @GridColumn(hidden=true)  
    @Label("Telephone") 
    private String telephone;
    
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @Model
    @Getter @Setter
    public static class VLMCaseItemLinks {
        @Config(url="/p/ownerview:<!/../id!>/_get")
        @Link(imgSrc="edit.png") @Label("Edit") private String edit;
     
        @Configs({
            @Config(url="/p/ownerview:<!/../id!>/_get"),
            @Config(url="/p/ownerview:<!/../id!>/_nav?pageId=vpOwnerInfo")
        })
        @Link(imgSrc="task.svg") @Label("Owner Info") private String ownerInfo;
    }
}