package com.atlas.client.extension.petquestionnaire.core;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Model.Param.Values;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Calendar;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.CheckBoxGroup;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.ComboBox;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.FileUpload;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Radio;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Signature;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TabInfo;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextArea;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.TextBox;
import com.antheminc.oss.nimbus.domain.defn.extension.ActivateConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ActivateConditionals;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.antheminc.oss.nimbus.domain.defn.extension.EnableConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional.Condition;
import com.antheminc.oss.nimbus.domain.defn.extension.VisibleConditional;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.AllTest;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.InboundReason;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.InboundType;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.NeitherTest;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.NoTest;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.SatisfactionType;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.VisitReason;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.YNType;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.YesTest;
import com.atlas.client.extension.petquestionnaire.core.CodeValueTypes.timeSelection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Andrew Jo
 *
 */
@Model
@Data
public class PetAssessment_Body {
	
	@TabInfo
	@Label("Pet Assessment Details")
	private PetAssessment_Body_Tab petAssessment_Accordion_tab;
	
	@Model @Data 
	public static class PetAssessment_Body_Tab {
		// ActivateConditional
		// ActivateConditionals
		// Calendar (date OR time formats)
		@Label(value = "Q1")
		private Q1 q1;
		
		@Model
		@Getter
		@Setter
		public static class Q1 {
			
			@Radio(postEventOnChange = true) 
//			@NotNull
			@Model.Param.Values(value = timeSelection.class)
			@ActivateConditionals({
				@ActivateConditional(when = "state == 'Date Only'", targetPath = "/../q1_b"),
				@ActivateConditional(when = "state == 'Time Only'", targetPath = "/../q1_c"),
				@ActivateConditional(when = "state == 'Both Date and Time'", targetPath = "/../q1_d")
			})
			@Label(value= "1. Choose date/time format for the date of appointment")
			private String q1_a;
			
			@Calendar(postEventOnChange = true)
			@Label(value = "Date ONLY of Appointment")
			private LocalDate q1_b;
			
			@Calendar(postEventOnChange = true, timeOnly= true)
			@Label(value = "Time ONLY of Appointment")
			private LocalDateTime q1_c;
			
			@Calendar(postEventOnChange = true, showTime = true)
			@Label(value = "Date AND Time of Appointment")
			private LocalDate q1_d;
		}
		
		// ActivateConditional (multiple)
		// ComboBox
		// Radio
		// TextBox
		@Label(value="Q2")
		private Q2 q2;
		
		@Model
		@Getter
		@Setter
		public static class Q2 {	
			
			@Radio(postEventOnChange = true)
//			@NotNull
			@Model.Param.Values(value = YNType.class)
			@ActivateConditional(when = "state == 'Yes'", targetPath = "/../q2_b")
			@Label(value="2. Was this your first visit at the Pet Clinic?")
			private String q2_a;

			@ComboBox(postEventOnChange = true)
			@Model.Param.Values(value = InboundType.class)
			@ActivateConditional(when = "state == 'Other'", targetPath = "/../q2_c")
			@Label(value="How did you hear about the Pet Clinic?")
			private String q2_b;

			@TextBox(postEventOnChange = true)
			@Label(value="Other (please specify)")
			private String q2_c;
		}
		
		// EnableConditional
		// TextBox
		@Label(value="Q3")
		private Q3 q3;
		
		@Model
		@Getter
		@Setter
		public static class Q3 {
			
	        @Radio(postEventOnChange = true)
			@Path(linked = false)
//			@NotNull
	        @Model.Param.Values(value = InboundReason.class)
			@EnableConditional(when = "state == 'Other'", targetPath = "/../q3_b")
	        @Label(value = "3. Why did you choose our Pet Clinic?")
	        private String q3_a;
			
			@TextBox(postEventOnChange = true)
			@Label(value="Other (please specify)")
			private String q3_b;
		}
		
		// ActivateConditional
		// Radio
		// TextBox
		@Label(value="Q4")
		private Q4 q4;
		
		@Model
		@Getter
		@Setter
		public static class Q4 {
			
			@Radio(postEventOnChange = true)
//			@NotNull
	        @Model.Param.Values(value = VisitReason.class)
			@ActivateConditional(when = "state == 'Other'", targetPath = "/../q4_b")
	        @Label(value = "4. What was the reason for your most recent visit to the Pet Clinic?")
	        private String q4_a;
			
			@TextBox(postEventOnChange = true)
			@Label(value="Other (please specify)")
			private String q4_b;
		}
		
		// Radio
		@Label(value="Q5")
		private Q5 q5;
		
		@Model
		@Getter
		@Setter
		public static class Q5 {	
			
			@Radio(postEventOnChange = true)
//			@NotNull
			@Model.Param.Values(value = YNType.class)
			@Label(value="5. Were you greeted promptly and politely?")
			private String q5_a;
		}

		// String
		@CheckBoxGroup
		@Label("Please rate each part of your visit: ")
		private String surveyLabel;
		
		// Radio
		@Label(value="Q6")
		private Q6 q6;
		
		@Model
		@Getter
		@Setter
		public static class Q6{
			
			@Radio(postEventOnChange = true)
//			@NotNull
			@Model.Param.Values(value = SatisfactionType.class)
			@Label(value="6. Making an appointment: ")
			private String q6_a;
		}
		
		// Radio
		@Label(value="Q7")
		private Q7 q7;
		
		@Model
		@Getter
		@Setter
		public static class Q7{
			
			@Radio(postEventOnChange = true)
//			@NotNull
			@Model.Param.Values(value = SatisfactionType.class)
			@Label(value="7. Checking in: ")
			private String q7_a;
		}
		
		// Radio
		@Label(value="Q8")
		private Q8 q8;
		
		@Model
		@Getter
		@Setter
		public static class Q8{
			
			@Radio(postEventOnChange = true, help="This field requires a value for pet type")
//			@NotNull
			@Model.Param.Values(value = SatisfactionType.class)
			@Label(value="8. Examination of my pet: ")
			private String q8_a;
		}
		
		// Radio
		// VisibleConditional
		// FileUpload
		@Label(value="Q9")
		private Q9 q9;
		
		@Model
		@Getter
		@Setter
		public static class Q9 {	
			
			@Radio(postEventOnChange = true)
//			@NotNull
			@Model.Param.Values(value = YNType.class)
			@VisibleConditional(when = "state == 'Yes'", targetPath = "/../q9_b")
			@Label(value="9. Do you have any relevant file/s to upload? (only in .png or .pdf formats)")
			private String q9_a;

			@FileUpload(type=".png,.pdf")
			@Label(value="Please upload the relevant file/s")
			private String q9_b;
		}
		
		// Radio
		//TextBox
		// ValueConditional
		@Label(value="Q10")
		private Q10 q10;
		
		@Model
		@Getter @Setter
		public static class Q10 {
			
			@TextBox(postEventOnChange = true)
			@ValuesConditional(targetPath = "/../q10_b", condition = {
				@Condition(when = "state == 'Yes'", then = @Values(YesTest.class)),
				@Condition(when = "state == 'No'", then = @Values(NoTest.class)),
				@Condition(when = "state == 'No'", then = @Values(NoTest.class)),
				@Condition(when = "state != 'Yes' || state != 'No'", then = @Values(NeitherTest.class)),
			})
			@Label(value="10. Values Conditional Test (type Yes or No)")
			private String q10_a;
			
			@Radio
			@Values(AllTest.class)
			@Label(value="Value Conditional Test Output: ")
			private String q10_b;
		}
		
		// CheckBoxGroup (with minimum size limitation)
		@CheckBoxGroup(postEventOnChange = true)
		@Size(min = 3, message = "Required number of selections not met (3)")
		@Model.Param.Values(value = VisitReason.class)
		@Label(value="11. CheckBoxGroup test (minimum of three):")
		private String[] q11;
		
		// TextArea (with max Characters limitation)
		@TextArea(postEventOnChange = true)
		@Label("12. Please feel free to make any other comments, good or bad, "
		+ "that will allow us to serve you better. "
		+ "Thanks for your time! We appreciate your comments. (Max 5 Characters)")
		@Pattern(regexp = "^[\\S\\s]{0,5}$", message = "Max 5 Characters")
		private String q12;

		// Signature
		// Not Null (with custom message)
		@Signature(postEventOnChange=true)
		@IgnoreCopy
		@NotNull (message = "The submission requires an acknowledgement signature")
		@Label(value = "13. Please provide signature:")
		private String q13;
	}
	
}
