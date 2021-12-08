package ma.octo.assignement.domain;

import ma.octo.assignement.domain.util.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_VIREMENT")
public class AuditVersement {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

  /*
  checkStyle: Hidden Field , I may change configuration to this ,
  to ignore setters and getters
  <module name="HiddenField" >
    <property name="ignoreSetter" value="true" />
    <property name="ignoreConstructorParameter" value="true" />
</module>*/



  public Long getId() {
    return id;
  }

  public void setId(Long Id) {
    this.id = Id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String Message) {
    this.message = Message;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}
