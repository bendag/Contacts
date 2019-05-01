package com.demo.contacts.RecyclerUtils;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.demo.contacts.BR;
import com.demo.contacts.Contact;
import com.demo.contacts.R;

class ContactViewHolder extends RecyclerView.ViewHolder {
    // À implémenter
    Button button;
    View itemView;
    ViewDataBinding binding;
    //ContactSwipeCallback contactSwipeCallback;

    ContactViewHolder(ViewDataBinding itemView){
        super(itemView.getRoot());
        this.itemView = itemView.getRoot();
        binding = itemView;
        button = (Button) this.itemView.findViewById(R.id.list_button);
    }

    public void bind(Contact contact){
        this.binding.setVariable(BR.contact, contact);
        this.binding.executePendingBindings();
    }
}

