package edu.pucmm.isc581.pruebaretrofit.fragments;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.pucmm.isc581.pruebaretrofit.ApiInterface;
import edu.pucmm.isc581.pruebaretrofit.R;
import edu.pucmm.isc581.pruebaretrofit.adapters.PostAdapter;
import edu.pucmm.isc581.pruebaretrofit.pojos.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;


public class PostFragment extends Fragment {

    private List<Post> posts;
    private PostAdapter postAdapter;


    public PostFragment() {
        // Required empty public constructor
    }

    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_post, container, false);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Call<List<Post>> retrievePosts = retrofit.create(ApiInterface.class).getPosts();
        retrievePosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.e("nice", "nice");
                postAdapter = new PostAdapter(response.body(), getContext()) ;
                RecyclerView postList = view.findViewById(R.id.PostRecyclerView);
                postList.setLayoutManager(new LinearLayoutManager(getContext()));
                postList.setHasFixedSize(true);
                postList.setItemAnimator(new DefaultItemAnimator());
                postList.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });

        getActivity().setTitle("Posts retrofit");
        return view;
    }
}