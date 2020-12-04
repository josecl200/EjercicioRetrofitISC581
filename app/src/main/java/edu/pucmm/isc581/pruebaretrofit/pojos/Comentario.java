package edu.pucmm.isc581.pruebaretrofit.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Comentario {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
