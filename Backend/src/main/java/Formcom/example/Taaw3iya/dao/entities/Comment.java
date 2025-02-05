package Formcom.example.Taaw3iya.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Comments")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

    @Column(name="post_id")
    private Long poste;
}
