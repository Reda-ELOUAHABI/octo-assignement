package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersementRepository extends JpaRepository<Versement, Long> {
}
