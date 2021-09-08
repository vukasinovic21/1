package Vesela.Druzina.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface KorisnikRepo extends JpaRepository<KorisnikEntity, Integer> {

    KorisnikEntity findByEmail(String email);


}