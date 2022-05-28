package ma.enset.studentsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.studentsmvc.entities.Student;
import ma.enset.studentsmvc.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentRepository studentRepository;

    @GetMapping(path = "/index")
    public String Student(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Student> studentsPages = studentRepository.findStudentByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("students", studentsPages.getContent());
        model.addAttribute("pages", new int[studentsPages.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "students";
    }




    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        studentRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/index";
    }

//    a form to add students
    @GetMapping(path = "/formStudents")
    public String formStudents(Model model) {
        model.addAttribute("student", new Student());
        return "formStudents";
    }


    @PostMapping(path = "/save")
    public String save(Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @Valid Student student,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "formStudents";
        studentRepository.save(student);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }



//    a form to update students
    @GetMapping(path = "/editStudents")
    public String editStudents(Model model, Long id, String keyword, int page) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new RuntimeException("student introuvable");
        model.addAttribute("student", student);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editStudents";
    }


}
