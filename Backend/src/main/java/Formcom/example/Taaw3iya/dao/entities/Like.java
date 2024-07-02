package Formcom.example.Taaw3iya.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Formcom.example.Taaw3iya.dao.enums.IntercationType; // Ensure this is the correct package


import java.security.PrivateKey;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IntercationType value;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;
    @Column(name="post_id")
    private Long poste;



}


