package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VirementDto {
//  comme une trace minimal des virement
  /*
  * Un objet de transfert de données (data transfer object ou DTO en anglais)
  *  est un patron de conception utilisé dans les architectures logicielles
  * objets.
  * Son but est de simplifier les transferts de données entre les sous-systèmes
* d'une application logicielle. Les objets de transfert de données sont souvent
*  utilisés en conjonction des objets d'accès aux données.

Le DTO se distingue du DAO (objet d'accès aux données) car il ne permet que de
*  modifier ou d'accéder à ses données (avec des mutateurs et accesseurs).
  * */


  private String nrCompteEmetteur;
  private String nrCompteBeneficiaire;
  private String motif;
  private BigDecimal montantVirement;
  private Date date;

  public VirementDto(String nrCompteEmetteur, String nrCompteBeneficiaire, String motif, BigDecimal montantVirement, Date date) {
    this.nrCompteEmetteur = nrCompteEmetteur;
    this.nrCompteBeneficiaire = nrCompteBeneficiaire;
    this.motif = motif;
    this.montantVirement = montantVirement;
    this.date = date;
  }

  public VirementDto() {
  }

  public String getNrCompteEmetteur() {
    return nrCompteEmetteur;
  }

  public void setNrCompteEmetteur(String nrCompteEmetteur) {
    this.nrCompteEmetteur = nrCompteEmetteur;
  }

  public String getNrCompteBeneficiaire() {
    return nrCompteBeneficiaire;
  }

  public void setNrCompteBeneficiaire(String nrCompteBeneficiaire) {
    this.nrCompteBeneficiaire = nrCompteBeneficiaire;
  }

  public BigDecimal getMontantVirement() {
    return montantVirement;
  }

  public void setMontantVirement(BigDecimal montantVirement) {
    this.montantVirement = montantVirement;
  }

  public String getMotif() {
    return motif;
  }

  public void setMotif(String motif) {
    this.motif = motif;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isValid(
          /*String nrCompteEmetteur,
           String nrCompteBeneficiaire,
           String motif, BigDecimal montantVirement,
           Date date*/
  ){
    if (nrCompteBeneficiaire==null || nrCompteEmetteur==null || montantVirement==null || motif ==null || date==null){
      return false;
    }
    else {
      return true;
    }
  }

  @Override
  public String toString() {
    return "VirementDto{" +
            "nrCompteEmetteur='" + nrCompteEmetteur + '\'' +
            ", nrCompteBeneficiaire='" + nrCompteBeneficiaire + '\'' +
            ", motif='" + motif + '\'' +
            ", montantVirement=" + montantVirement +
            ", date=" + date +
            '}';
  }
}
