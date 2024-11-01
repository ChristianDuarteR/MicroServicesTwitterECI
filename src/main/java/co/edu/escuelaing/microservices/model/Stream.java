package co.edu.escuelaing.microservices.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stream {
    private List<Post> posts;

    private static Stream stream;

    public static Stream getInstance() {
        if (stream ==null ) {
            stream = new Stream();
        }
        return stream;
    }

}
