package com.unreal.demotask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    ArrayList user_id, name_id, number_id;

    public CustomAdapter(Context context, ArrayList id, ArrayList name, ArrayList num){
        this.user_id = id;
        this.context = context;
        this.name_id = name;
        this.number_id = num;

    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_list_rows, parent, false);
        return new MyViewHolder(v).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.Name.setText(String.valueOf(name_id.get(position)));
        holder.Number.setText(String.valueOf(number_id.get(position)));
        holder.ID.setText(String.valueOf(user_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Number, ID;
//        ImageButton Delete;
        public CustomAdapter adapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.row_name);
            Number = itemView.findViewById(R.id.row_number);
            ID = itemView.findViewById(R.id.user_id);
            itemView.findViewById(R.id.dlt_row).setOnClickListener(view ->{
                DBHelper DBmain = new DBHelper(context);
                MainActivity mAct = new MainActivity();
                SQLiteDatabase db = DBmain.getReadableDatabase();
                long recdelete = db.delete("usertable", "User_ID=" + user_id.get(getAdapterPosition()), null);
                if (recdelete != -1) {
                    Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Not deleted", Toast.LENGTH_SHORT).show();
                }

                adapter.name_id.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });
        }
        public MyViewHolder linkAdapter(CustomAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }


}
