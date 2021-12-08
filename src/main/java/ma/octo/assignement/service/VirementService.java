package ma.octo.assignement.service;

import ma.octo.assignement.dto.VirementDto;
import org.springframework.stereotype.Service;
//class vrain
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class VirementService {
//J'ai choisis de ne pas utiliser l'injection de service puisque c'est pas necessaire dans notre projet [volume petit]
//    mais generalement lorsque on veut la REUTILISABILITY , we do that:


/*@Autowired what we want*/
public  VirementService(){

}
//    public Map<String,String> ServiceName(VirementDto virementDto) throws  IOException .. {
//
//    }
//   /*and we @Autowired it + recuperer la reponse dans un var par exmple*/

}
