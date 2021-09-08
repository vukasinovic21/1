package Vesela.Druzina.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Vesela.Druzina.demo.web.KorisnikData;

@Repository
interface KorisnikRepo extends JpaRepository<KorisnikEntity, Integer> {

    KorisnikEntity findByEmail(String email);

    void save(KorisnikData user);

}