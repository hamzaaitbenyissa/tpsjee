package ma.enset.jpausers.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolename;
    @Column(name = "DESCRIPTON")
    private String desc;
    @ManyToMany
//    @JoinTable(name = "USERS_ROLES")
    @ToString.Exclude
    private List<User> users=new ArrayList<>();
}
