package com.example.lenovo.dhuwi_1202154348_studycase5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


    public class todoadapter extends RecyclerView.Adapter<todoadapter.holder> {
        private Context con;
        private List<itemtodo> itemnya;
        int id;


        //Constructornya
        public todoadapter(Context con, List<itemtodo> itemnya, int id) {
            this.con = con;
            this.itemnya = itemnya;
            this.id = id;
        }

        //Method untuk menentukan viewholder untuk recyclerview
        @Override
        public holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(con).inflate(R.layout.itemrecycler, parent, false);
            holder hold = new holder(v);
            return hold;
        }

        //Method untuk mengikat nilai dengan objek pada viewholder
        @Override
        public void onBindViewHolder(holder holder, int position) {
            itemtodo satuan = itemnya.get(position);
            holder.name.setText(satuan.getName());
            holder.description.setText(satuan.getDescription());
            holder.priority.setText(satuan.getPriority());
            holder.cd.setCardBackgroundColor(con.getResources().getColor(this.id));
        }

        //Method untuk mendapatkan itemtodo dari adapter
        public itemtodo getItem(int position){
            return itemnya.get(position);
        }

        //Method untuk mendapatkan jumlah itemtodo
        @Override
        public int getItemCount() {
            return itemnya.size();
        }

        //Method untuk menghapus itemtodo
        public void removeitem(int i){
            itemnya.remove(i);
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, itemnya.size());
        }

        //Subclass sebagai viewholder
        class holder extends RecyclerView.ViewHolder{
            public TextView name, description, priority;
            public CardView cd;
            public holder(View itemView) {
                super(itemView);

                //Menginisialisasikan objek
                name = itemView.findViewById(R.id.todoname);
                description = itemView.findViewById(R.id.tododescription);
                priority = itemView.findViewById(R.id.todopriority);
                cd = itemView.findViewById(R.id.rootcardnya);
            }
        }
    }
