package com.atlas.client.extension.petclinic.core.pet;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;

import com.antheminc.oss.nimbus.domain.model.state.internal.DefaultParamState;
import com.antheminc.oss.nimbus.support.JustLogit;
import com.atlas.client.extension.petclinic.core.veterinarian.Veterinarian;

public class PetDecisionUtils {
	
	private static JustLogit logit = new JustLogit(PetDecisionUtils.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isVetAvailable(Veterinarian vet, DefaultParamState param) {
		logit.debug(() -> "Checking if ["+vet.getFullName()+"] is the least busy vet");
		List<Veterinarian> allVets = (List<Veterinarian>)param.getLeafState();
		Veterinarian availableVet = allVets.stream()
					.filter(v -> StringUtils.equalsIgnoreCase(vet.getSpeciality(), v.getSpeciality()))
					.min(Comparator.comparing(Veterinarian::assignedPetCount))
				      .orElseThrow(NoSuchElementException::new);
				      
		if(StringUtils.equalsIgnoreCase(vet.getFullName(), availableVet.getFullName())){
			logit.debug(() -> "YES!! ["+vet.getFullName()+"] is the least busy vet");
			return true;
		}
		logit.debug(() -> "NO!! ["+vet.getFullName()+"] is a busy vet");
		return false;
	}
}
