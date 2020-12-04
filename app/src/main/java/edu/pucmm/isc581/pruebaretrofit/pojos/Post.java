package edu.pucmm.isc581.pruebaretrofit.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
