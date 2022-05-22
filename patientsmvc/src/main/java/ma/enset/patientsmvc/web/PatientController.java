package ma.enset.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String Patient(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> patientsPages = patientRepository.findPatientsByNomContains(keyword, PageRequest.of(page, size));

        model.addAttribute("patients", patientsPages.getContent());
        model.addAttribute("pages", new int[patientsPages.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/")
    public String home() {
        return "/home";
    }

    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> patients() {
        return patientRepository.findAll();
    }


    @GetMapping(path = "/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }


    @PostMapping(path = "/save")
    public String save(Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @Valid Patient patient,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;

    }


    @GetMapping(path = "/editPatients")
    public String editPatients(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient", patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatients";
    }

}
