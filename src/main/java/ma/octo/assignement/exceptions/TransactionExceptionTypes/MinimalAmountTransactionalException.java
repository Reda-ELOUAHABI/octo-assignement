package ma.octo.assignement.exceptions.TransactionExceptionTypes;

import ma.octo.assignement.exceptions.TransactionException;
//PMD: Usability:naming class convention The class name 'MinimalAmount_TransactionalException' doesn't match '[A-Z][a-zA-Z0-9]*'
public class MinimalAmountTransactionalException extends TransactionException {

    public MinimalAmountTransactionalException(){

    }
    public MinimalAmountTransactionalException(String message){
        super(message);
    }
}
