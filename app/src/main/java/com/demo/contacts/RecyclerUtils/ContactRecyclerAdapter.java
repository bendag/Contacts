package com.demo.contacts.RecyclerUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.contacts.Contact;
import com.demo.contacts.EditContactActivity;
import com.demo.contacts.R;
import java.util.List;


import static android.databinding.DataBindingUtil.inflate;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    // À implémenter
    private boolean isFavorite = true;
    public static List<Contact> list_item;
    Context context;

    public ContactRecyclerAdapter(List<Contact> list_item, Context context){
        super();
        this.list_item = list_item;
        this.context = context;
    }

    /**
     * onCreateViewHolder sous forme simple_list_item_2
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = inflate(layoutInflater, R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(binding);
    }


    @NonNull
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int i) {
        if(isFavorite == list_item.get(i).getFavorite() || isFavorite == false) {

            //on retrouve le contact
            Contact item = list_item.get(i);

            //bind soccupe des operations du binding dans la classe ContactViewholder
            holder.bind(item);

            //On initialise nos OnClickListeners
            holder.button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    shortClick(holder.itemView, holder);
                }
            });
            holder.button.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {

                    longClick(holder.itemView, holder);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    //appel de fonction lors dun clique cours sur un viewHolder
    public void shortClick(View v, ContactViewHolder holder) {
        Contact c = list_item.get(holder.getAdapterPosition());
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", c.getPhone(), null));
        context.startActivity(intent);
    }

    //appel de fonction lors dun clique long sur un viewHolder
    public void longClick(View v, ContactViewHolder holder) {
        Contact c = list_item.get(holder.getAdapterPosition());
        Intent intent = new Intent(context, EditContactActivity.class);
        intent.putExtra("EDIT_TYPE", context.getResources().getString(R.string.edit_contact));
        intent.putExtra("CONTACT", c);
        ((Activity) context).startActivityForResult(intent, 1);
    }

    public void setFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    public boolean getFavorite(){
        return isFavorite;
    }
}
