package ma.octo.assignement.exceptions.TransactionExceptionTypes;

import ma.octo.assignement.exceptions.TransactionException;

public class MontantVideTransactionalException extends TransactionException {

    public MontantVideTransactionalException(){

    }
    public MontantVideTransactionalException(String message){
        super(message);
    }
}
