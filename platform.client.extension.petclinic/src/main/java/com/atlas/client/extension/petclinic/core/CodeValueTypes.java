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
			values.add(new ParamValue("Snake", "Snake"));
            values.add(new ParamValue("Lizard", "Lizard"));
            values.add(new ParamValue("Turtle", "Turtle"));
			values.add(new ParamValue("Turkey", "Turkey"));
			values.add(new ParamValue("Duck", "Duck"));
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
            values.add(new ParamValue("Service", "Service Group"));
			return values;
		}
    }
	
	public static class CatCategory implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("Bobtail", "American Bobtail"));
            values.add(new ParamValue("Curl", "American Curl"));
            values.add(new ParamValue("White", "American SnowWhite"));
			return values;
		}
    }
	
	public static class AllCategory implements Source {
		@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("Bobtail", "American Bobtail"));
            values.add(new ParamValue("Curl", "American Curl"));
            values.add(new ParamValue("White", "American SnowWhite"));
            values.add(new ParamValue("Sporting", "Sporting Group"));
            values.add(new ParamValue("Hound", "Hound Group"));
            values.add(new ParamValue("Service", "Service Group"));
            values.add(new ParamValue("Hound2", "Hound Group"));
            values.add(new ParamValue("Hound3", "Hound Group"));
            values.add(new ParamValue("Hound4", "Hound Group"));
            values.add(new ParamValue("Hound5", "Hound Group"));
            values.add(new ParamValue("Hound6", "Hound Group"));
            values.add(new ParamValue("Hound7", "Hound Group"));
            values.add(new ParamValue("Hound8", "Hound Group"));
            values.add(new ParamValue("Hound9", "Hound Group"));
            values.add(new ParamValue("Hound10", "Hound Group"));
            values.add(new ParamValue("Hound11", "Hound Group"));
            values.add(new ParamValue("Hound12", "Hound Group"));
            values.add(new ParamValue("Hound13", "Hound Group"));
            values.add(new ParamValue("Hound14", "Hound Group"));
            values.add(new ParamValue("Hound15", "Hound Group"));
            values.add(new ParamValue("Hound16", "Hound Group"));
            values.add(new ParamValue("Hound17", "Hound Group"));
            values.add(new ParamValue("Hound18", "Hound Group"));
            values.add(new ParamValue("Hound19", "Hound Group"));
            values.add(new ParamValue("Hound20", "Hound Group"));
            values.add(new ParamValue("Hound21", "Hound Group"));
            values.add(new ParamValue("Hound22", "Hound Group"));
            values.add(new ParamValue("Hound23", "Hound Group"));
            values.add(new ParamValue("Hound24", "Hound Group"));
            values.add(new ParamValue("Hound25", "Hound Group"));
            values.add(new ParamValue("Hound26", "Hound Group"));
            values.add(new ParamValue("Hound27", "Hound Group"));
            values.add(new ParamValue("Hound28", "Hound Group"));
            values.add(new ParamValue("Hound29", "Hound Group"));
            values.add(new ParamValue("Hound30", "Hound Group"));
            values.add(new ParamValue("Hound31", "Hound Group"));
            values.add(new ParamValue("Hound32", "Hound Group"));
            values.add(new ParamValue("Hound33", "Hound Group"));
            values.add(new ParamValue("Hound34", "Hound Group"));
            values.add(new ParamValue("Hound35", "Hound Group"));
            values.add(new ParamValue("Hound36", "Hound Group"));
            values.add(new ParamValue("Hound37", "Hound Group"));
			return values;
		}
	}
	
	public static class OwnerNotificationPreferences implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("do_not_send_notifications", "Do Not Send Notifications"));
			values.add(new ParamValue("email", "Email"));
			values.add(new ParamValue("physical_mail", "Mail"));
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
	
	public static class VetSpeciality implements Source {
    	@Override
		public List<ParamValue> getValues(String paramPath) {
			List<ParamValue> values = new ArrayList<>();
			values.add(new ParamValue("avian", "Avian"));
			values.add(new ParamValue("equine", "Equine"));
			values.add(new ParamValue("feline", "Feline"));
			values.add(new ParamValue("canine", "Canine"));
			values.add(new ParamValue("poultry", "Poultry"));
			values.add(new ParamValue("reptile-amphibian", "Reptile & Amphibian"));
			return values;
		}
    }
}
