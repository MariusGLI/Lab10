package Problema_proiect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarteController {

    @Autowired
    private CarteService carteService;

    @GetMapping("/")
    public String afiseazaCarti(Model model) {
        model.addAttribute("carti", carteService.findAll());
        model.addAttribute("message", "Lista cartilor preluate prin repository");
        return "index";
    }

    @PostMapping("/adauga")
    public String adaugaCarte(@ModelAttribute Carte carte, Model model) {
        return carteService.adaugaCarte(carte, model);
    }

    @PostMapping("/modifica/{isbn}")
    public String modificaCarte(@PathVariable String isbn, @ModelAttribute Carte carte, Model model) {
        return carteService.modificaCarte(isbn, carte, model);
    }

    @PostMapping("/sterge/{isbn}")
    public String stergeCarte(@PathVariable String isbn, Model model) {
        return carteService.stergeCarte(isbn, model);
    }

    @PostMapping("/filtrare")
    public String filtreazaCarti(@RequestParam String autor, Model model) {
        return carteService.filtreazaCartiDupaAutor(autor, model);
    }
}

