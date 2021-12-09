package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//class vrain
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class VirementService {
//J'ai choisis de ne pas utiliser l'injection de service puisque c'est pas necessaire dans notre projet [volume petit]
//    mais generalement lorsque on veut la REUTILISABILITY , we do that:


/*@Autowired what we want*/
public  VirementService(){

}

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(VirementService.class);

    @Autowired
    private CompteRepository rep1;
    @Autowired
    private VirementRepository re2;
    @Autowired
    private AuditService monservice;
    @Autowired
    private UtilisateurRepository re3;
    public Map<String,String> executerVirement(VirementDto virementDto) throws
              SoldeDisponibleInsuffisantException, CompteNonExistantException,
            TransactionException {
              Map<String, String> response = new HashMap<String, String>();
        Compte c1 = rep1.
                findByNrCompte(virementDto.getNrCompteEmetteur());
        Compte c2 = rep1
                .findByNrCompte(virementDto.getNrCompteBeneficiaire());
//todo: PMD: for Maintability reasons : avoid duplicate literals:
        String compteNonExistant = "Compte Non existant";//repeated 4 times
        if (c1 == null) {
//    todo: PMD System Println:  System.(out|err).print is used, consider using a logger
//            System.out.println(compteNonExistant);
            LOGGER.error(compteNonExistant);
            throw new CompteNonExistantException(compteNonExistant);
        }

        if (c2 == null) {
            LOGGER.error(compteNonExistant);
//            System.out.println(compteNonExistant);
            throw new CompteNonExistantException(compteNonExistant);
        }
        if (!virementDto.isValid()) {
            LOGGER.error("all fields are required");
            throw new TransactionException("all fields are required");
        }

        else if (virementDto.getMontantVirement().intValue() == 0) {
            LOGGER.error("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (virementDto.getMontantVirement().intValue() < 10) {
            LOGGER.error("Montant minimal de virement non atteint");
            throw new TransactionException("Montant minimal de virement non atteint");

        } else if (virementDto.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de virement dépassé");
            throw new TransactionException("Montant maximal de virement dépassé");
        }
        if (virementDto.getMotif().length() <= 0) {
            LOGGER.error("Motif vide");
            throw new TransactionException("Motif vide");
        }
        if (c1.getSolde().intValue() - virementDto.getMontantVirement().intValue() < 0) {

            LOGGER.error("Solde insuffisant pour l'utilisateur");
            throw new TransactionException("Solde insuffisant pour l'utilisateur");
        }
        c1.setSolde(c1.getSolde().subtract(virementDto.getMontantVirement()));
        rep1.save(c1);

        c2.setSolde(c2.getSolde().add(virementDto.getMontantVirement()));
        rep1.save(c2);

        Virement virement = new Virement();
        virement.setDateExecution(virementDto.getDate());
        virement.setCompteBeneficiaire(c2);
        virement.setCompteEmetteur(c1);
        virement.setMontantVirement(virementDto.getMontantVirement());
        re2.save(virement);

        monservice.auditVirement("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
                .toString());

        response.put("message", "Virement avec Succes");
        return response;

    }
//   /*and we @Autowired it + recuperer la reponse dans un var par exmple*/

}















//    //la reponse doit etre declaree au debut , car il ya plusieurs cas
//    HashMap<String, String> response = new HashMap<String, String>();
//
//
//        /*Construction de virementsDto
//                String nrCompteEmetteur = (String) body.get("nrCompteEmetteur");
//                String nrCompteBeneficiaire = (String) body.get("nrCompteBeneficiaire");
//                BigDecimal motif = (BigDecimal) body.get("motif");
//                String montantVirement = (String) body.get("montantVirement");
//                Date date = (Date) body.get("date");
//                VirementDto virementDto;
//                try {
//                   virementDto = new VirementDto(montantVirement,nrCompteEmetteur,nrCompteBeneficiaire,motif,date);
//                    System.out.println("Request Valid ");
//                }
//                catch (Exception e){
//                    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
//                    response.put("message", "Verifie les Fields du requetes");
//                    response.put("error", String.valueOf(e));
//                    return response;
//
//                }
//*/
//
//
//
//    Compte c1 = rep1.findByNrCompte(virementDto.getNrCompteEmetteur());
//    //        si on la nomme c2 , ca sera plus lisible
//    Compte f12 = rep1
//            .findByNrCompte(virementDto.getNrCompteBeneficiaire());
//    //todo: PMD: for Maintability reasons : avoid duplicate literals:
//    String compteNonExistant = "Compte Non existant";//repeated 4 times
//        if (c1 == null) {
////    todo: PMD System Println:  System.(out|err).print is used, consider using a logger
////            System.out.println(compteNonExistant);
//                LOGGER.error(compteNonExistant);
//                throw new CompteNonExistantException(compteNonExistant);
//                }
//
//                if (f12 == null) {
//                LOGGER.error(compteNonExistant);
////            System.out.println(compteNonExistant);
//                throw new CompteNonExistantException(compteNonExistant);
//                }
///*todo: correctness type : comparing incompatible types for equality
//* Call to equals(null)
//This method calls equals(Object), passing a null value as the argument.
//*  According to the contract of the equals() method, this call should always return 'false'.
//* */
////             but spostbug isn't right here:   in case of request doesn't specified the montant
////        but it already give internal server error (java.lang.NullPointerException: null) if we don't
////        specify the montant [if we delete it , or "" empty]
////                todo: handle this error case
//
////                for this line , it can't even be null
////                System.out.println(virementDto);
//
////     the equals method (and every other method) requires string Variable to not be null.
////     Alors on utilise comparaison par reference au lieu de valeur(il utilise le hashcode des deux stings
//
////         Pas besoin de tester la nullety, deja on a notre test
////        if (virementDto.getMontantVirement()==null) {
////            System.out.println("Montant vide");
////            throw new TransactionException("Montant vide");
////        }
////                mais on doit tester les autres fields:
////j'ai cree une fonction is Valid dans le contrat virementDto
//
//                if (!virementDto.isValid()) {
//                LOGGER.error("all fields are required");
////            System.out.println("all fields are required");
//                throw new TransactionException("all fields are required");
//                }
//
//                else if (virementDto.getMontantVirement().intValue() == 0) {
//                LOGGER.error("Montant vide");
//                throw new TransactionException("Montant vide");
//                } else if (virementDto.getMontantVirement().intValue() < 10) {
////                    response.put("Montant minimal de virement non atteint");
////                where we throw errors:
////                This will allow you to create higher quality code where errors are
////                checked at compile time instead of runtime, and create custom exceptions
////                that make debugging and recovery easier.
//                    /* Best practice of handling exceptions
//                    * try {
//                      throw new Exception();
//                      } catch(Exception e) {
//                      // code to handle the exception
//                       }
//                    * */
////                    Old
//        //                        Il va nous couper l'execution , wheras we are inside a restAPI app !
//        LOGGER.error("Montant minimal de virement non atteint");
//        throw new TransactionException("Montant minimal de virement non atteint");
////         throw new MinimalAmountTransactionalException("Montant minimal de virement non atteint");
////
////New Before Using AdviceController
//                   /*
//                    try {
//                        System.out.println("Montant minimal de virement non atteint");
//                        throw new TransactionException("Montant minimal de virement non atteint");
//                    }
//                    catch ( TransactionException e){
//                    response.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
//                    response.put("message", "Montant minimal de virement non atteint");
//                    response.put("error", String.valueOf(e));
//                    return response;
//*/
//
//
//        } else if (virementDto.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
//        LOGGER.error("Montant maximal de virement dépassé");
//        throw new TransactionException("Montant maximal de virement dépassé");
//        }
//
////                <=0 , not <0 [-1,-2]
//        if (virementDto.getMotif().length() <= 0) {
//        LOGGER.error("Motif vide");
////            Puisque il sont de la meme class , est ce que on va utiliser un try catch/ ou on cree des exception plus
////            personnalisee ?? c'est une decision a prendre
//        throw new TransactionException("Motif vide");
//        }
////where we use logging
//        /*Loggers has multiple levels for logging.
//        If we are writing a real short program, just for learning purposes System.
//        out. println is fine but when we are developing a *quality software
//         project*, we should use professional logger and SOPs should be avoided.*/
////
////why we use logging
//            /*Logging is an API that provides the ability to trace out the errors of the
//applications. When an application generates the logging call, the Logger records
//the event in the LogRecord.
// After that, it sends to the corresponding handlers or appenders*/
//        if (c1.getSolde().intValue() - virementDto.getMontantVirement().intValue() < 0) {
//
//        LOGGER.error("Solde insuffisant pour l'utilisateur");
//        throw new TransactionException("Solde insuffisant pour l'utilisateur");
//        }
//
////        c'est un
////        if (c1.getSolde().intValue() - virementDto.getMontantVirement().intValue() < 0) {
////            LOGGER.error("Solde insuffisant pour l'utilisateur");
////            throw new TransactionException("Solde insuffisant pour l'utilisateur");
////        }
//
////                ici tout est bien passee
//        c1.setSolde(c1.getSolde().subtract(virementDto.getMontantVirement()));
//        rep1.save(c1);
//
//        f12.setSolde(f12.getSolde().add(virementDto.getMontantVirement()));
////      f12.setSolde(new BigDecimal(f12.getSolde().intValue() + virementDto.getMontantVirement().intValue()));
//        rep1.save(f12);
//
//        Virement virement = new Virement();
////        et si le virement n'est pas complet ! ,
////        on instancie sans avoir verifie ce qu'on va cree
//        virement.setDateExecution(virementDto.getDate());
//        virement.setCompteBeneficiaire(f12);
//        virement.setCompteEmetteur(c1);
//        virement.setMontantVirement(virementDto.getMontantVirement());
//        re2.save(virement);
//
//        monservice.auditVirement("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
//        .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
//        .toString());
//
////   Better to be at the top:     HashMap<String, String> response = new HashMap<String, String>();
