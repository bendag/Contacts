package com.demo.contacts;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

public class Contact extends BaseObservable implements Serializable {

    // À implémenter

    //information a sauvegarder
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean favorite;
    private int id;

    //Bindable: relié à l'activité contact_edit_activity bidirectionnellement
    @Bindable
    public String getFirstName(){
        return firstName;
    }

    @Bindable
    public void setFirstName(String firstName){
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName(){
        return lastName;
    }

    @Bindable
    public void setLastName(String lastName){
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getEmail(){
        return email;
    }

    @Bindable
    public void setEmail(String email){
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPhone(){
        return phone;
    }

    @Bindable
    public void setPhone(String phone){
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public boolean getFavorite(){
        return favorite;
    }

    @Bindable
    public void setFavorite(boolean favorite){
        this.favorite = favorite;
        notifyPropertyChanged(BR.favorite);
    }

    @Bindable
    public int getId(){
        return id;
    }

    @Bindable
    public void setId(int id){
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

}
