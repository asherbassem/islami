package com.route.contactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ContactsAdapter (
    val contactList: List<ContactsItem>)
    : Adapter<ContactsAdapter.ContactsViewHolder>(){
        var onItemClick: OnContactItemClickListner?=null

    class ContactsViewHolder(val contactView : View) : ViewHolder(contactView){
        val user: TextView =contactView.findViewById(R.id.name)
        val phoneNumber:TextView =contactView.findViewById(R.id.number)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val context = parent.context
        val inflater= LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_contact,parent,false)

        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size ?:0
            }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = contactList.get(position)
        holder.user.text=item.name
        holder.phoneNumber.text=item.phone
        holder.itemView.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                onItemClick?.onContactsItemClick(contactItem = item,position=position)
            }


        })
    }

}