package co.edu.escuelaing.microservices.dto;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreamDTO {
    private List<Post> posts;

    private User owner;

}
