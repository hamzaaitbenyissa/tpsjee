package ma.enset.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private Date datenaissance;
    private boolean malade;

    @OneToMany(mappedBy = "patient",fetch =  FetchType.LAZY)
    private Collection<RendezVous> rendezVous;

    public Patient(String nom, String email, Date datenaissance, boolean malade) {
        this.nom = nom;
        this.email = email;
        this.datenaissance = datenaissance;
        this.malade = malade;
    }
}
