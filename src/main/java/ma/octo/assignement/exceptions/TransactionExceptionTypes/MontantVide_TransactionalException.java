package ma.octo.assignement.exceptions.TransactionExceptionTypes;

import ma.octo.assignement.exceptions.TransactionException;

public class MontantVide_TransactionalException extends TransactionException {

    public MontantVide_TransactionalException(){

    }
    public MontantVide_TransactionalException(String message){
        super(message);
    }
}
