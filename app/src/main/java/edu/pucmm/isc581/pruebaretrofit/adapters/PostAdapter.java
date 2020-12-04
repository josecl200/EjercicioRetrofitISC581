package edu.pucmm.isc581.pruebaretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import edu.pucmm.isc581.pruebaretrofit.MainActivity;
import edu.pucmm.isc581.pruebaretrofit.R;
import edu.pucmm.isc581.pruebaretrofit.fragments.CommentsFragment;
import edu.pucmm.isc581.pruebaretrofit.fragments.PostFragment;
import edu.pucmm.isc581.pruebaretrofit.pojos.Post;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;
    private Context mContext;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_list,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bindData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        CardView carta;
        TextView titulo, usuario, preview;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            carta = itemView.findViewById(R.id.carta);
            titulo = itemView.findViewById(R.id.titlePost);
            usuario = itemView.findViewById(R.id.numeroUsuario);
            preview = itemView.findViewById(R.id.previewPost);
        }

        public void bindData(Post post) {
            titulo.setText(post.getTitle());
            usuario.setText(Integer.toString(post.getUserId()));
            preview.setText(post.getBody().substring(0, 100) + "...");
            carta.setOnClickListener(v -> {
                CommentsFragment commentsFragment = CommentsFragment.newInstance(post.getId());
                Fragment fragment = ((MainActivity) mContext).getSupportFragmentManager().getPrimaryNavigationFragment();
                FragmentTransaction ft = ((MainActivity) mContext).getSupportFragmentManager().beginTransaction();
                ft.detach(fragment);
                ft.replace(R.id.fragment, commentsFragment).commit();
            });
        }
    }
}
