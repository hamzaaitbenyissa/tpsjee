package ma.enset.studentsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nom is required")
    private String nom;
    @NotBlank(message = "prenom is required")
    private String prenom;
    @NotEmpty
    @Email
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datenaissance;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private boolean enregle;
}
