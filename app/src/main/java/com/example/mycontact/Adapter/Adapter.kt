package com.example.mycontact.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.Data.Entity.User
import com.example.mycontact.databinding.ContactItemBinding

class Adapter(var list: List<User>,var contactClick:OnClickContact):RecyclerView.Adapter<Adapter.MyHolder>() {
    class MyHolder(binding:ContactItemBinding):RecyclerView.ViewHolder(binding.root){
        var name = binding.userName
        var number = binding.userNumber
        var main = binding.contactItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ContactItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        holder.main.setOnClickListener {
            contactClick.setOnClickContact(list[position].id)
        }
    }
    interface OnClickContact{
        fun setOnClickContact(id:Int)
    }
}