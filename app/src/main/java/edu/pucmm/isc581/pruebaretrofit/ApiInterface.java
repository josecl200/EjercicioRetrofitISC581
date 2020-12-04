package edu.pucmm.isc581.pruebaretrofit;

import edu.pucmm.isc581.pruebaretrofit.pojos.Comentario;
import edu.pucmm.isc581.pruebaretrofit.pojos.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ApiInterface {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{postId}")
    Call<Post> getSinglePost(@Path("postId") Integer postId);

    @GET("posts/{postId}/comments")
    Call<List<Comentario>> getComments(@Path("postId") Integer postId);
}
