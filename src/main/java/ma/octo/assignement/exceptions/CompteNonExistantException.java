package ma.octo.assignement.exceptions;

public class CompteNonExistantException extends Exception {


  /*
   * SerialVersionUID is a unique identifier for each class,
   *  JVM uses it to compare the versions of the class ensuring
   *  that the same class was used during Serialization is loaded
   *  during Deserialization. Specifying one gives more control,
   *  though JVM does generate one if you don't specify.
   * */

  //  serialisation : enregister un objet java dans une forme persistant ,
//  afin qu'on peut le recuperer par la suite par note jVM ou une jvm
//  distant (exemple de RMI)
//   TELL QU IL EST , Cet objet est mis sous une forme sous laquelle
//   il pourra être reconstitué à l'identique.
  private static final long serialVersionUID = 1L;

  public CompteNonExistantException() {
  }

  public CompteNonExistantException(String message) {
    super(message);
  }
}
