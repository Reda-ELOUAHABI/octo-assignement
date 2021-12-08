package ma.octo.assignement.domain.util;

public enum EventType {

  VIREMENT("virement"),
  VERSEMENT("Versement d'argent");

  private String type;

  EventType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  /*todo: spotBug , bad practice
  * Public enum method unconditionally sets its field
This public method declared in public enum unconditionally
*  sets enum field, thus this field can be changed by malicious
*  code or by accident from another package. Though mutable enum
*  fields may be used for lazy initialization, it's a bad practice
*  to expose them to the outer world. Consider removing this method
* or declaring it package-private.
* */

//  public void setType(String type) {
//    this.type = type;
//  }
}
