package ma.octo.assignement.web.common;

import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransactionExceptionTypes.MinimalAmountTransactionalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandelingController {

    @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Pas de solde pas de virement", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Compte introuvable", null, HttpStatus.UNAUTHORIZED);
    }


    //MinimalAmountTransactionalException.class in case of 4.1 Readme Exception Cases
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> handleMinimalAmountException(Exception ex, WebRequest request) {
//        System.out.println(ex.getMessage());
//        System.out.println(request);
        if(ex.getMessage() == "Montant minimal de virement non atteint") {
            return new ResponseEntity<>("Montant minimal de virement non atteint", null, HttpStatus.BAD_REQUEST);
        }
        else if(ex.getMessage()=="Montant vide"){
            return new ResponseEntity<>("Montant vide", null, HttpStatus.BAD_REQUEST);
        }
//        else if(ex.getMessage()=="all fields are required"){
//            return new ResponseEntity<>("all fields are required", null, HttpStatus.BAD_REQUEST);
//        }

//      KISS  DRY YAGNI [et meme les autre error s'inscrit dans le cadre de error de transaction]
//        Don’t Repeat Yourself
//        Keep It Simple, Stupid Not necessary here
//        You Ain’t Gonna Need It.
        else if(ex.getMessage()=="Montant maximal de virement dépassé"){
            return new ResponseEntity<>("Montant maximal de virement dépassé", null, HttpStatus.BAD_REQUEST);
        }
        else if(ex.getMessage()=="Motif vide"){
            return new ResponseEntity<>("Motif vide", null, HttpStatus.BAD_REQUEST);
        }  else if(ex.getMessage()=="Solde insuffisant pour l'utilisateur"){
            return new ResponseEntity<>("Solde insuffisant pour l'utilisateur", null, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("Error Au niveau du transaction", null, HttpStatus.BAD_REQUEST);
        }
    }




}
