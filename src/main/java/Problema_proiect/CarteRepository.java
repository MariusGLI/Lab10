package Problema_proiect;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(String autor);
}

