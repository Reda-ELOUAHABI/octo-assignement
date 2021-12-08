package ma.octo.assignement.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "COMPTE")
public class Compte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 16, unique = true)
  private String nrCompte;

  private String rib;

  @Column(precision = 16, scale = 2)
  private BigDecimal solde;

  @ManyToOne()
  @JoinColumn(name = "utilisateur_id")
  private Utilisateur utilisateur;

  public String getNrCompte() {
    return nrCompte;
  }

  public void setNrCompte(String nbrCompte) {
    this.nrCompte = nbrCompte;
  }

  public String getRib() {
    return rib;
  }

  public void setRib(String ribX) {
    this.rib = ribX;
  }

  public BigDecimal getSolde() {
    return solde;
  }

  public void setSolde(BigDecimal soldeX) {
    this.solde = soldeX;
  }

  public Utilisateur getUtilisateur() {
    /*todo: *8-bug on the sameType SpotBug: malicious code vulnerability of type
     *Methode returning array may expose internal representation
     *  */
    return utilisateur;
  }

  /*todo: *8-bug on the sameType SpotBug: malicious code vulnerability of type
  Storing reference to mutable object
  May expose internal representation by incorporating reference to mutable object
This code stores a reference to an externally mutable object into the internal
representation of the object.  If instances are accessed by untrusted code,
 and unchecked changes to the mutable object would compromise security or other
 important properties, you will need to do something different. Storing a copy of
  the object is better approach in many situations.
  *
  * */
//  Sol Simple [On n'expose pas notre object 'precious']
//retun Arrays.copyOf(DESCRIPTIONS, DESCRIPTIONS.length); ansteadOf return Description;
  public void setUtilisateur(Utilisateur utilisateurX) {
    this.utilisateur = utilisateurX;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Compte{" +
            "id=" + id +
            ", nrCompte='" + nrCompte + '\'' +
            ", rib='" + rib + '\'' +
            ", solde=" + solde +
            ", utilisateur=" + utilisateur +
            '}';
  }
}
