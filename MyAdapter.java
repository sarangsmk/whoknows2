package gq.smktech.whoknows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<post> posts;
    SharedPreferences sharedPreferences;
    private OnItemClickListner mListner;

    public interface OnItemClickListner{
        void OnItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner)
    {
        mListner=listner;
    }

    public MyAdapter(Context c,ArrayList<post> p)
    {
        context = c;
        posts = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.question.setText(posts.get(i).getQuestion());
        myViewHolder.description.setText(posts.get(i).getDescription());
        myViewHolder.postedOn.setText(posts.get(i).getPostedOn());
        myViewHolder.postedBy.setText(posts.get(i).getPostedBy());
        Glide.with(context).load(posts.get(i).getDp()).into(myViewHolder.proPic);

        //Glide.with(context).load(posts.get(i).getMediaLink()).into(myViewHolder.proPic);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder

    {
        TextView question,description,postedOn,postedBy;
        CircleImageView proPic;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            question = (TextView)itemView.findViewById(R.id.txtQuestion);
            description =itemView.findViewById(R.id.txtDescription);
            postedOn = itemView.findViewById(R.id.txtDate);
            postedBy = itemView.findViewById(R.id.txtUser);
            proPic = itemView.findViewById(R.id.proPic);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            mListner.OnItemClick(position);
                        }
                    }
                }
            });


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, CommentActivity.class));
////
////                    Toast.makeText(context, question.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
           // proPic = (CircleImageView)itemView.findViewById(R.id.proPic);
        }
    }
}
