package Formcom.example.Taaw3iya.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;
    @Column(nullable = false,unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.ALL)
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role-id",referencedColumnName = "id"))
    private List<Roles> roles=new ArrayList<Roles>();

}
