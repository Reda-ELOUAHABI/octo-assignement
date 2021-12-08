package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;

public class VirementMapper {

    private static VirementDto virementDto;
    // Mapp a virement into a virment dto
    public static VirementDto map(Virement virement) {
        virementDto = new VirementDto();
        virementDto.setNrCompteEmetteur(virement.getCompteEmetteur().getNrCompte());
        virementDto.setDate(virement.getDateExecution());
        virementDto.setMotif(virement.getMotifVirement());
/*todo:  SpotBug: malicious code vulnerability of type Mutable static field bug on the sameType
*Public static method may expose internal representation by returning array
A public static method returns a reference to an array that is part of the
* static state of the class. Any code that calls this method can freely modify
*  the underlying array. One fix is to return a copy of the array.
*  */
//        return Arrays.copyOf(virementDto, virementDto.length) ;
//        but create the copy of methode
        return virementDto;

    }
}
