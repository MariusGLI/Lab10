package Problema_proiect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

    public String adaugaCarte(Carte carte, Model model) {
        if (carte.getIsbn() != null && carte.getTitlu() != null && carte.getAutor() != null) {
            carteRepository.save(carte);
            model.addAttribute("message", "Adaugare realizata cu succes!");
        } else {
            model.addAttribute("message", "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!");
        }
        return "redirect:/";
    }

    public String modificaCarte(String isbn, Carte carte, Model model) {
        Carte carteExistenta = carteRepository.findById(isbn).orElse(null);
        if (carteExistenta != null) {
            carteExistenta.setTitlu(carte.getTitlu());
            carteExistenta.setAutor(carte.getAutor());
            carteRepository.save(carteExistenta);
            model.addAttribute("message", "Cartea cu ISBN-ul " + isbn + " a fost modificata!");
        } else {
            model.addAttribute("message", "Nu se gaseste nici o carte cu isbnul introdus.");
        }
        return "redirect:/";
    }

    public String stergeCarte(String isbn, Model model) {
        if (carteRepository.existsById(isbn)) {
            carteRepository.deleteById(isbn);
            model.addAttribute("message", "Cartea cu ISBN-ul " + isbn + " a fost stearsa!");
        } else {
            model.addAttribute("message", "Nu exista nicio carte cu acest ISBN!");
        }
        return "redirect:/";
    }

    public String filtreazaCartiDupaAutor(String autor, Model model) {
        List<Carte> carti = autor.isEmpty() ? carteRepository.findAll() : carteRepository.findByAutor(autor);
        model.addAttribute("carti", carti);
        model.addAttribute("message", autor.isEmpty() ? "Toate cartile sunt afisate!" : "Cărţile următoare aparţin autorului " + autor);
        return "index";
    }
}

