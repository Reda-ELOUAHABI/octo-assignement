package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VersementDto {
  private String rib;
  private BigDecimal montantVersement;
  private String motifVersement;
  private Date dateExecution;

//  setters and getters name must be the same as jsonFields
  public Date getDateExecution() {
    return dateExecution;
  }

  public void setDateExecution(Date dateExecution) {
    this.dateExecution = dateExecution;
  }

  public String getNom_prenom_emetteur() {
    return nom_prenom_emetteur;
  }

  public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
    this.nom_prenom_emetteur = nom_prenom_emetteur;
  }

  private String nom_prenom_emetteur;

  public VersementDto(String rib, BigDecimal montantVersement, String motifVersement, Date dateExecution,
                      String nom_prenom_emetteur
  ) {
    this.rib = rib;
    this.montantVersement = montantVersement;
    this.motifVersement = motifVersement;
    this.dateExecution = dateExecution;
    this.nom_prenom_emetteur=nom_prenom_emetteur;
  }

  public VersementDto() {
  }

  public String getRib() {
    return rib;
  }

  public void setRib(String rib) {
    this.rib = rib;
  }

  public BigDecimal getMontantVersement() {
    return montantVersement;
  }

  public void setMontantVersement(BigDecimal montantVersement) {
    this.montantVersement = montantVersement;
  }



  public boolean isValid(
  ){
    if (rib==null || montantVersement==null
            || dateExecution==null || nom_prenom_emetteur==null
        || montantVersement==null
     ){
      return false;
    }
    else {
      return true;
    }
  }

  @Override
  public String toString() {
    return "VersementDto{" +
            "RIB='" + rib + '\'' +
            ", montantVersement=" + montantVersement +
            ", dateExecution=" + dateExecution +
            ", nom & prenom=" + nom_prenom_emetteur +
            ", motif Versement=" + motifVersement +
            '}';
  }

  public String getMotifVersement() {
    return motifVersement;
  }

  public void setMotifVersement(String motifVersement) {
    this.motifVersement = motifVersement;
  }
}
