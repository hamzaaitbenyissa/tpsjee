package com.benyissa.backend.web;


import com.benyissa.backend.entities.Student;
import com.benyissa.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/students")
    public List<Student> Student(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Student> studentsPages = studentRepository.findStudentByNomContains(keyword, PageRequest.of(page, size));
        return studentsPages.getContent();
    }


    /*find a student by id */
    @GetMapping(path = "/students/{id}")
    public Student GetStudent(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // add a new student
    @PostMapping(path = "/students")
    public Student AddStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    /*update a student */
    @PutMapping(path = "/students/{id}")
    public Student UpdateStudent(@RequestBody Student updatedStudent, @PathVariable Long id) {

        return studentRepository.findById(id)
                .map(student -> {
                    student.setNom(updatedStudent.getNom());
                    student.setPrenom(updatedStudent.getPrenom());
                    student.setGenre(updatedStudent.getGenre());
                    student.setEmail(updatedStudent.getEmail());
                    student.setEnregle(updatedStudent.isEnregle());
                    student.setDatenaissance(updatedStudent.getDatenaissance());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    updatedStudent.setId(id);
                    return studentRepository.save(updatedStudent);
                });
    }


    /*delete a student by id */
    @DeleteMapping("/students/{id}")
    public void DeleteStudent(@PathVariable Long id) {
        if(studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        }
    }


}
