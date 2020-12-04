package edu.pucmm.isc581.pruebaretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import edu.pucmm.isc581.pruebaretrofit.R;
import edu.pucmm.isc581.pruebaretrofit.pojos.Comentario;
import edu.pucmm.isc581.pruebaretrofit.pojos.Post;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comentario> comments;
    private Context mContext;

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_list,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindData(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, email, comentario;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreComentario);
            email = itemView.findViewById(R.id.emailComentario);
            comentario = itemView.findViewById(R.id.cuerpoComentario);
        }

        public void bindData(Comentario comment) {
           nombre.setText(comment.getName());
           email.setText(comment.getEmail());
           comentario.setText(comment.getBody());
        }
    }
}
