package com.atlas.client.extension.petclinic.core;

import java.util.ArrayList;
import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Model.Param.Values.Source;
import com.antheminc.oss.nimbus.domain.model.config.ParamValue;
/**
 * @author Andrew Jo
 *
 */
public class CodeValueTypes {
	
	public static class petType implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("Cat", "Cat"));
            values.add(new ParamValue("Dog", "Dog"));
            values.add(new ParamValue("Parrot", "Parrot"));
			values.add(new ParamValue("Horse", "Horse"));
			values.add(new ParamValue("Turtle", "Turle"));
			values.add(new ParamValue("Yeti", "Yeti"));
			values.add(new ParamValue("Other", "Other"));
			return values;
		}
    }
	
	public static class DogCategory implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("Sporting", "Sporting Group"));
            values.add(new ParamValue("Hound", "Hound Group"));
			return values;
		}
    }
	
	public static class CatCategory implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("Bobtail", "American Bobtail"));
            values.add(new ParamValue("Curl", "American Curl"));
			return values;
		}
    }
	
	public static class OwnerNotificationPreferences implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("do_not_send_notifications", "Do Not Send Notifications"));
			values.add(new ParamValue("email", "Email"));
			values.add(new ParamValue("phyiscal_mail", "Mail"));
			values.add(new ParamValue("text", "Text"));
			return values;
		}
    }

	public static class NoteTypes implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("general", "General Note"));
			values.add(new ParamValue("owner", "Note about Owner"));
			values.add(new ParamValue("visit", "Note about Visit"));
			return values;
		}
    }
}
