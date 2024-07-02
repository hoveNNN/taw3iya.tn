package Formcom.example.Taaw3iya.dao.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Posts")
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @OneToMany
    private List<Like> likes=new ArrayList<Like>();
    @OneToMany(mappedBy = "post")
    private List<Comment> comments=new ArrayList<Comment>();
    //comeent
    @ManyToOne
    private User User_id;


}
