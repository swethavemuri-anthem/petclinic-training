package com.atlas.client.extension.petquestionnaire.core;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Cache;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Accordion;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.AccordionTab;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Button;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ButtonGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Calendar;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.entity.AbstractEntity.IdLong;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.YesTest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sandeep Mantha
 *
 */
@Domain(value = "petcareassessment", includeListeners = { ListenerType.persistence })
@Repo(alias = "petcareassessment", value = Database.rep_mongodb, cache = Cache.rep_device)
@Getter
@Setter
public class PetCareAssessment extends IdLong {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private PetCareForm petCareForm;
	
	@Model
	@Getter @Setter
	public static class PetCareForm {
		
		@Accordion(showExpandAll=true, showMessages=true)
		private PetCareAssessmentQuestionnaire petCareAssessmentQuestionnaire;
				
		@ButtonGroup
		private  FormButtonGroup buttonGroup;
	}
	
	@Model
	@Getter @Setter
	public static class PetCareAssessmentQuestionnaire {
		
		@AccordionTab
		@Label("Section 1")
		private Quetionnaire_Section1 quetionnaire_Section1;
		
		@AccordionTab
		@Label("Section 2")
		private Quetionnaire_Section2 quetionnaire_Section2;
		
		@AccordionTab
		@Label("Section 3")
		private Quetionnaire_Section3 quetionnaire_Section3;
				
	}
	
	@Model
	@Getter @Setter
	public static class Quetionnaire_Section1 {
				
		@TextBox
		@NotNull
		@Label(value = "Question 1")
		private String question1;

		@Calendar()
		@NotNull
		@Label(value = "Question 2")
		private LocalDate question2;

		@ComboBox()
		@Model.Param.Values(value = YesTest.class)
		@Label(value = "Question 3")
		private String question3;
		
	}
	
	@Model
	@Getter @Setter
	public static class Quetionnaire_Section2 {
		
		@TextBox
		@NotNull
		@Label(value = "Question 4")
		private String question4;

		@Calendar()
		@NotNull
		@Label(value = "Question 5")
		private LocalDate question5;

		@ComboBox()
		@Model.Param.Values(value = YesTest.class)
		@Label(value = "Question 6")
		private String question6;
	}
	
	@Model
	@Getter @Setter
	public static class Quetionnaire_Section3 {

		@TextBox
		@NotNull
		@Label(value = "Question 7")
		private String question7;

		@Calendar()
		@NotNull
		@Label(value = "Question 8")
		private LocalDate question8;

		@ComboBox()
		@Model.Param.Values(value = YesTest.class)
		@Label(value = "Question 9")
		private String question9;
	
	}
	
	@Model
	@Getter @Setter
	public static class FormButtonGroup {
		
		@Button(style = Button.Style.PRIMARY, cssClass = "btn btn-primary mb-1", type = Button.Type.submit, formReset = true)
		@Label(value = "Submit")
		private String submit;
	
		@Button(style = Button.Style.VALIDATION, cssClass = "btn btn-primary mb-1", type = Button.Type.button)
		@Label(value = "Validate Form")
		private String validate;
		
		@Button(style = Button.Style.SECONDARY, cssClass = "btn btn-primary mb-1", type = Button.Type.submit, formReset = true)
		@Label("Clear")
		private String Clear;
			
	
	}
	
}