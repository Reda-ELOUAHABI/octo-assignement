package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.VersementService;
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
@RequestMapping("/versements")

class VersementController {

    @Autowired
    private VersementService vs;
    @Autowired
    private VersementRepository vr;

    @GetMapping("lister_versement")
    List<Versement> loadAllVersements() {
        List<Versement> all = vr.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @PostMapping("/executerVersement")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> createVersement(
            @RequestBody VersementDto versementDto)
            throws  CompteNonExistantException,
            TransactionException {
        Map<String, String> response = new HashMap<String, String>();
        response = vs.executerVersement(versementDto);
        return response;
    }

}
