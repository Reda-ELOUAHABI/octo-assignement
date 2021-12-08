package ma.octo.assignement.exceptions.TransactionExceptionTypes;

import ma.octo.assignement.exceptions.TransactionException;

public class MinimalAmount_TransactionalException extends TransactionException {

    public MinimalAmount_TransactionalException(){

    }
    public MinimalAmount_TransactionalException(String message){
        super(message);
    }
}
