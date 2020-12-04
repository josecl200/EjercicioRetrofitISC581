package edu.pucmm.isc581.pruebaretrofit.fragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.pucmm.isc581.pruebaretrofit.ApiInterface;
import edu.pucmm.isc581.pruebaretrofit.R;
import edu.pucmm.isc581.pruebaretrofit.adapters.CommentAdapter;
import edu.pucmm.isc581.pruebaretrofit.adapters.PostAdapter;
import edu.pucmm.isc581.pruebaretrofit.pojos.Comentario;
import edu.pucmm.isc581.pruebaretrofit.pojos.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;


public class CommentsFragment extends Fragment {

    private static final String ARG_PARAM1 = "idPost";

    private Integer idPost;
    private CommentAdapter commentAdapter;

    public CommentsFragment() {}

    public static CommentsFragment newInstance(Integer idPost) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idPost);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPost = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        TextView tituloPost, textoPost, idUsuarioPost;
        tituloPost      = view.findViewById(R.id.tituloPost);
        textoPost       = view.findViewById(R.id.textoPost);
        idUsuarioPost   = view.findViewById(R.id.numeroUser);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Call<Post> retrievePosts = retrofit.create(ApiInterface.class).getSinglePost(idPost);
        retrievePosts.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                tituloPost.setText(response.body().getTitle());
                textoPost.setText(response.body().getBody());
                idUsuarioPost.setText(Integer.toString(response.body().getId()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                //
            }
        });
        Call<List<Comentario>> retrieveComments = retrofit.create(ApiInterface.class).getComments(idPost);
        retrieveComments.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                commentAdapter = new CommentAdapter(response.body(), getContext());
                RecyclerView commentList = view.findViewById(R.id.commentRecyclerView);
                commentList.setLayoutManager(new LinearLayoutManager(getContext()));
                commentList.setHasFixedSize(true);
                commentList.setItemAnimator(new DefaultItemAnimator());
                commentList.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {

            }
        });
        return view;
    }
}