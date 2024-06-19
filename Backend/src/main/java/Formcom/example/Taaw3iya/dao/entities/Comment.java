package Formcom.example.Taaw3iya.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@Table(name="Comments")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
