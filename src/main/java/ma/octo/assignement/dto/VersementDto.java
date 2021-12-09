package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

/*
///DTO
* The Data Transfer Object Design Pattern is one of the enterprise
*  application architecture patterns that calls for the use of objects
*  that aggregate and encapsulate data for transfer. There, the main
*  idea of DTOs is to reduce the number of remote calls that are expensive.
* */

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
    return nomPrenomEmetteur;
  }

  public void setNom_prenom_emetteur(String nomPrenomEmetteur) {
    this.nomPrenomEmetteur = nomPrenomEmetteur;
  }

  private String nomPrenomEmetteur;

  public VersementDto(String rib, BigDecimal montantVersement, String motifVersement, Date dateExecution,
                      String nomPrenomEmetteur
  ) {
    this.rib = rib;
    this.montantVersement = montantVersement;
    this.motifVersement = motifVersement;
    this.dateExecution = dateExecution;
    this.nomPrenomEmetteur=nomPrenomEmetteur;
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
            || dateExecution==null || nomPrenomEmetteur==null
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
            ", nom & prenom=" + nomPrenomEmetteur +
            ", motif de Versement=" + motifVersement +
            '}';
  }

  public String getMotifVersement() {
    return motifVersement;
  }

  public void setMotifVersement(String motifVersement) {
    this.motifVersement = motifVersement;
  }
}
