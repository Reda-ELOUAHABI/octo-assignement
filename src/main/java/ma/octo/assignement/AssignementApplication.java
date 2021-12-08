package ma.octo.assignement;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
//import java.sql.SQLException;
import java.util.Date;

@SpringBootApplication
public class AssignementApplication  {
//public class AssignementApplication implements CommandLineRunner { //for run methode
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private VirementRepository virementRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}

//CheckStyle: design For Extention:
/* Class 'AssignementApplication' looks like designed for extension
(can be subclassed), but the method 'run' does not have javadoc that
 explains how to do that safely. If class is not designed for extension
  consider making the class 'AssignementApplication' final or making the
   method 'run' static/final/abstract/empty, or adding allowed annotation
    for the method.*/

//	SOL : If class is not designed for extension consider making the class 'AssignementApplication' final or making the method 'console' static/final/abstract/empty

	private final  Long COMPTE_1_init = 200000L;
	@Bean
	public CommandLineRunner console() {
		return args -> {

			Utilisateur utilisateur1 = new Utilisateur();
			utilisateur1.setUsername("user1");
			utilisateur1.setLastname("last1");
			utilisateur1.setFirstname("first1");
			utilisateur1.setGender("Male");


			utilisateurRepository.save(utilisateur1);

			System.out.println(utilisateur1 + " Saved");

			Utilisateur utilisateur2 = new Utilisateur();
			utilisateur2.setUsername("user2");
			utilisateur2.setLastname("last2");
			utilisateur2.setFirstname("first2");
			utilisateur2.setGender("Female");

			utilisateurRepository.save(utilisateur2);
			System.out.println(utilisateur2 + " Saved");

			Compte compte1 = new Compte();
			compte1.setNrCompte("010000A000001000");
			compte1.setRib("RIB1");
//	todo: checkStyle : reliability : magic number , better to be readable and meaningful
			compte1.setSolde(BigDecimal.valueOf(COMPTE_1_init));
			compte1.setUtilisateur(utilisateur1);


			compteRepository.save(compte1);
			System.out.println(compte1+ " de l: "+utilisateur1 + " Saved");

			Compte compte2 = new Compte();
			compte2.setNrCompte("010000B025001000");
			compte2.setRib("RIB2");
			compte2.setSolde(BigDecimal.valueOf(140000L));
			compte2.setUtilisateur(utilisateur2);

			compteRepository.save(compte2);
			System.out.println(compte2+ " de l: "+utilisateur2 + " Saved");


			Virement v = new Virement();
			v.setMontantVirement(BigDecimal.TEN);
			v.setCompteBeneficiaire(compte2);
			v.setCompteEmetteur(compte1);
			v.setDateExecution(new Date());
			v.setMotifVirement("Assignment 2021");


			virementRepository.save(v);
			System.out.println("Virement "+BigDecimal.TEN+"succes de "+compte1+ " de l: "+utilisateur1 + " a " + compte2+ " de l: "+utilisateur2 );


		};}

/*
	@Override
	public void run(String... strings) throws Exception {
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");


		utilisateurRepository.save(utilisateur1);

		System.out.println(utilisateur1 + " Saved");

		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");

		utilisateurRepository.save(utilisateur2);
		System.out.println(utilisateur2 + " Saved");

		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compte1.setUtilisateur(utilisateur1);


		compteRepository.save(compte1);
		System.out.println(compte1+ " de l: "+utilisateur1 + " Saved");

		Compte compte2 = new Compte();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		compteRepository.save(compte2);
		System.out.println(compte2+ " de l: "+utilisateur2 + " Saved");


		Virement v = new Virement();
		v.setMontantVirement(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifVirement("Assignment 2021");


		virementRepository.save(v);
		System.out.println("Virement "+BigDecimal.TEN+"succes de "+compte1+ " de l: "+utilisateur1 + " a " + compte2+ " de l: "+utilisateur2 );

	}
*/


}
