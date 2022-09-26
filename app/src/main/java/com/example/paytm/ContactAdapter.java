package com.example.paytm;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    List<ContactModel> contactModelList=new ArrayList<>();
    AlertDialog alertDialog;




    public ContactAdapter(Context context, List<ContactModel> contactModelList) {
        this.context = context;
        this.contactModelList = contactModelList;

    }
    public void setFilteredList(List<ContactModel> filteredList){
        this.contactModelList=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=LayoutInflater.from(parent.getContext()).inflate(R.layout.contact,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ContactModel contactModel= contactModelList.get(position);
        holder.photo.setImageURI(contactModelList.get(position).imageUri);
        holder.name.setText(contactModelList.get(position).getName());
        holder.number.setText(contactModelList.get(position).getNumber());

        if(contactModelList.get(position).getImage()!=null)
        {
            holder.photo.setImageURI(Uri.parse(contactModelList.get(position).getImage()));
        }

        else {
            Log.i("null",""+contactModelList.get(position).getImage());
        }



    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,number;
        ShapeableImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name_txt);
            number=itemView.findViewById(R.id.mobile_txt);
            photo=itemView.findViewById(R.id.shape_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                    View root=LayoutInflater.from(context).inflate(R.layout.dialog,null);

                    Button calcel=root.findViewById(R.id.calcel_btn);
                    TextView dialog=root.findViewById(R.id.nametxt);
                    dialog.setText(name.getText().toString());
                ShapeableImageView displayimage=root.findViewById(R.id.dialog_moj);

                    displayimage.setImageURI(Uri.parse(""+contactModelList.get(getAdapterPosition()).getImage()));

                    builder.setView(root);
                    builder.setCancelable(false);
                    calcel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(ContactAdapter.this.context, "cancel", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog=builder.create();
                    alertDialog.show();
                }
            });
                }
            }
}