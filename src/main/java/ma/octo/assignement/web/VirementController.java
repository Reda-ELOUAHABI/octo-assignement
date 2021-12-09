package ma.octo.assignement.web;


import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.VirementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RestController(value = "/virements")
@RequestMapping("/virements")

class VirementController {

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    private CompteRepository rep1;
    @Autowired
    private VirementRepository re2;
    @Autowired
    private VirementService virementService;
    @Autowired
    private UtilisateurRepository re3;

    @GetMapping("lister_virements")
    List<Virement> loadAll() {
        List<Virement> all = re2.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("lister_comptes")
    List<Compte> loadAllCompte() {
        List<Compte> all = rep1.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("/lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> all = re3.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;}
    }

    @PostMapping("/executerVirements")
//    Why 201 des le debut , peut etre que ca va echouer
    @ResponseStatus(HttpStatus.CREATED)
//    void , is not preferable to have
//  todo: PMD: J'ai utiliser une interface Map au lieu de Hashmap : to loose coopling
    public Map<String,String> createTransaction(@RequestBody
  //Map<String,Object> body
    VirementDto virementDto  //deja c'est risquee , c'est la requete n'est pas correcte
     //  sintaxiquement => server Down
// => sol : construit l'object ras par tas
    )
//    public void createTransaction(@RequestBody VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException,
            TransactionException {
        //la reponse doit etre declaree au debut , car il ya plusieurs cas
        Map<String, String> response = new HashMap<String, String>();
        response=virementService.executerVirement(virementDto);
        return response;
    }

// ca sert a rien
//    private void save(Virement Virement) {
//        re2.save(Virement);
//    }



}
