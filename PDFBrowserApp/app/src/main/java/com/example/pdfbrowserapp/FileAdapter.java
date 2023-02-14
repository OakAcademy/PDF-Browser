package com.example.pdfbrowserapp;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    ArrayList<Uri> uriList;
    ArrayList<String> nameList;

    public FileAdapter(ArrayList<Uri> uriList,ArrayList<String> nameList){
        this.uriList = uriList;
        this.nameList = nameList;
    }


    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item,parent,false);

        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {

        String name = nameList.get(position);
        //Uri uri = uriList.get(position);
        holder.textViewFileName.setText(name);

        holder.fileItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.fileItem.getContext(),PDFActivity.class);
                intent.putExtra("uri",uriList.get(holder.getAdapterPosition()).toString());
                holder.fileItem.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    static class FileViewHolder extends RecyclerView.ViewHolder{
        TextView textViewFileName;
        LinearLayout fileItem;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFileName = itemView.findViewById(R.id.textViewFileName);
            fileItem = itemView.findViewById(R.id.fileItem);

        }
    }

}
