package tr.edu.yildiz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context mContext;
    private String[] mquestions;
    private String[] mans1s;
    private String[] mans2s;
    private String[] mans3s;
    private String[] mans4s;
    private String[] mans5s;
    private String[] manswers;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question, ans1, ans2, ans3, ans4, ans5, ans;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.question=itemView.findViewById(R.id.question);
            this.ans1=itemView.findViewById(R.id.ans1);
            this.ans2=itemView.findViewById(R.id.ans2);
            this.ans3=itemView.findViewById(R.id.ans3);
            this.ans4=itemView.findViewById(R.id.ans4);
            this.ans5=itemView.findViewById(R.id.ans5);
            this.ans=itemView.findViewById(R.id.ans);
        }
    }

    public CustomAdapter(Context Context, String[] questions, String[] ans1s, String[] ans2s, String[] ans3s,
                         String[] ans4s,String[] ans5s, String[] answers) {

        this.mContext = Context;
        this.mans1s=ans1s;
        this.mans2s=ans2s;
        this.mans3s=ans3s;
        this.mans4s=ans4s;
        this.mans5s=ans5s;
        this.mquestions=questions;
        this.manswers=answers;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {
        holder.question.setText(mquestions[i]);
        holder.ans1.setText(mans1s[i]);
        holder.ans2.setText(mans2s[i]);
        holder.ans3.setText(mans3s[i]);
        holder.ans4.setText(mans4s[i]);
        holder.ans5.setText(mans5s[i]);
        holder.ans.setText(manswers[i]);
    }

    @Override
    public int getItemCount() {
        return mquestions.length;
    }



}