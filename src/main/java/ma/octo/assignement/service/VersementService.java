package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;

@Service
//we use the power of transactional [all or nothing]
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class VersementService {
//J'ai choisis de ne pas utiliser l'injection de service puisque c'est pas necessaire dans notre projet [volume petit]
//    mais generalement lorsque on veut la REUTILISABILITY , we do that:


/*@Autowired what we want*/
public VersementService() {

}
    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VersementService.class);

    @Autowired
    private CompteRepository rep1;
    @Autowired
    private AuditService monservice;
    @Autowired
    private VersementRepository vr;
    public Map<String,String> executerVersement(VersementDto versementDto) throws CompteNonExistantException,
            TransactionException {
        Map<String, String> response = new HashMap<String, String>();
//        Some Checks
        if (!versementDto.isValid()) {
            LOGGER.error("all fields are required");
            throw new TransactionException("all fields are required");
        }
        else if (versementDto.getMontantVersement().intValue() == 0) {
            LOGGER.error("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() < 10) {
            LOGGER.error("Montant minimal de versement non atteint");
            throw new TransactionException("Montant minimal de versement non atteint");

        } else if (versementDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de versement dépassé");
            throw new TransactionException("Montant maximal de versement dépassé");
        }
        if (versementDto.getMotifVersement().length() <= 0) {
            LOGGER.error("Motif vide");
            throw new TransactionException("Motif vide");
        }

        Compte compteAVerser = rep1
                .findByRib(versementDto.getRib());

//      ici tout est bien passee
        compteAVerser.setSolde(compteAVerser.getSolde().add(versementDto.getMontantVersement()));
        rep1.save(compteAVerser);

//      Il faut mettre ici Versement
        Versement versement= new Versement();
        versement.setCompteBeneficiaire(compteAVerser);
        versement.setMontantVersement(versementDto.getMontantVersement());
        versement.setMotifVersement(versementDto.getMotifVersement());
        versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());
        versement.setDateExecution(versementDto.getDateExecution());
//        System.out.println(versement);
        vr.save(versement);

//        Add it to audit service
        monservice.auditVirement("Versement depuis " + versementDto.getRib() +" d'un montant de " + versement.getMontantVersement()
                .toString());

        response.put("status", String.valueOf(HttpStatus.CREATED));
        response.put("message", versementDto.toString());
        response.put("compte", compteAVerser.toString());
        return response;
    }
//   /*and we @Autowired it + recuperer la reponse dans un var par exmple*/

}







/*//        Some Checks
        if (!versementDto.isValid()) {
            LOGGER.error("all fields are required");
            throw new TransactionException("all fields are required");
        }
        else if (versementDto.getMontantVersement().intValue() == 0) {
            LOGGER.error("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (versementDto.getMontantVersement().intValue() < 10) {
            LOGGER.error("Montant minimal de versement non atteint");
            throw new TransactionException("Montant minimal de versement non atteint");

        } else if (versementDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de versement dépassé");
            throw new TransactionException("Montant maximal de versement dépassé");
        }
        if (versementDto.getMotifVersement().length() <= 0) {
            LOGGER.error("Motif vide");
            throw new TransactionException("Motif vide");
        }

        Compte compteAVerser = rep1
                .findByRib(versementDto.getRib());

//      ici tout est bien passee
        compteAVerser.setSolde(compteAVerser.getSolde().add(versementDto.getMontantVersement()));
        rep1.save(compteAVerser);

//      Il faut mettre ici Versement
        Versement versement= new Versement();
        versement.setCompteBeneficiaire(compteAVerser);
        versement.setMontantVersement(versementDto.getMontantVersement());
        versement.setMotifVersement(versementDto.getMotifVersement());
        versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());
        versement.setDateExecution(versementDto.getDateExecution());
//        System.out.println(versement);
        vr.save(versement);

//        Add it to audit service
        monservice.auditVirement("Versement depuis " + versementDto.getRib() +" d'un montant de " + versement.getMontantVersement()
                .toString());*/