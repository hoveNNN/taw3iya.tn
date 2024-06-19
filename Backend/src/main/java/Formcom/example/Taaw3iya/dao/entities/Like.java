package Formcom.example.Taaw3iya.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@Table(name="Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
