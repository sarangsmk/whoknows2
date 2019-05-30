package gq.smktech.whoknows;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder> {

    Context context;
    ArrayList<comment> comments;

    public commentAdapter(Context c, ArrayList<comment> cmnt)
    {
        context = c;
        comments = cmnt;
    }

    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new commentAdapter.commentViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_single_comment,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder commentViewHolder, int i) {

        commentViewHolder.txtComment.setText(comments.get(i).getComment());
        commentViewHolder.txtDate.setText(comments.get(i).getPostedOn());
        commentViewHolder.txtPostedBy.setText(comments.get(i).getPostedBy());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class commentViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtComment,txtDate,txtPostedBy;

        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtComment= itemView.findViewById(R.id.txtComment);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPostedBy = itemView.findViewById(R.id.txtPostedBy);

        }
    }
}
