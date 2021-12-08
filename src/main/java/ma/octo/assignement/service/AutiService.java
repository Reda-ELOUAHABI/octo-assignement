package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditVirement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditVirementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
/*
L'annotation @Transactional permet de délimiter une transaction (entre le début et la fin de la méthode) et de définir le comportement transactionnel d'une méthode.
No-rollback-for : la ou les exceptions (séparées ...
Rollback-for : la ou les exceptions (séparées par ...
Propagation : mode de propagation de la trans...
Isolation : niveau d'isolation de la transaction


where we use Transactional
votre appel est"change password". Qui consiste en deux opérations

changer le mot de passe.
Vérifier le changement.
envoyer au client que le mot de passe a changé.
ainsi, si la vérification échoue, alors le mot de passe doit changer
 aussi l'échec? */
public class AutiService {

    Logger LOGGER = LoggerFactory.getLogger(AutiService.class);

    @Autowired
    private AuditVirementRepository auditVirementRepository;

    public void auditVirement(String message) {
        LOGGER.info("Audit de l'événement {}", EventType.VIREMENT);
        AuditVirement audit = new AuditVirement();
        audit.setEventType(EventType.VIREMENT);
        audit.setMessage(message);
        auditVirementRepository.save(audit);
    }


    public void auditVersement(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.VERSEMENT);
        AuditVirement audit = new AuditVirement();
        audit.setEventType(EventType.VERSEMENT);
        audit.setMessage(message);
        auditVirementRepository.save(audit);
    }
}
