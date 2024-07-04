package Formcom.example.Taaw3iya.web.dto;

import Formcom.example.Taaw3iya.dao.entities.Tag;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


import lombok.*;

import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPostDto {
    @NotEmpty
    @Size(max = 4096)
    private String subject;
    @NotEmpty
    @Size(max = 4096)
    private String description;

    private Long userId;

    private MultipartFile images;

    private List<Tag> tags;
}