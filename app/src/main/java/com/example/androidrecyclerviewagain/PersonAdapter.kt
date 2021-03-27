package com.example.androidrecyclerviewagain

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewagain.databinding.ItemPersonBinding

class PersonAdapter(private val listPerson: ArrayList<Person>): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    private var onItemClickCallback: OnItemClickCallBack? = null

    interface OnItemClickCallBack {
        fun onItemClicked(data: Person)
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(person.photo)
                    .into(binding.civItemPhoto)

                tvItemPerson.text = person.name
                tvItemDetail.text = person.desc

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(person)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(listPerson[position])
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

}