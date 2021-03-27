package com.example.androidrecyclerviewagain

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidrecyclerviewagain.databinding.ItemCardviewPersonBinding

class CardViewPersonAdapter(private val listPerson: ArrayList<Person>) :
    RecyclerView.Adapter<CardViewPersonAdapter.CardViewViewHolder>() {
    class CardViewViewHolder(private val binding: ItemCardviewPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(person.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(imgItemPhoto)

                tvItemName.text = person.name
                tvItemDescription.text = person.desc

                btnSetFavorite.setOnClickListener { Toast.makeText(itemView.context, "Favorite ${person.name}", Toast.LENGTH_SHORT).show() }
                btnSetShare.setOnClickListener { Toast.makeText(itemView.context, "Share ${person.name}", Toast.LENGTH_SHORT).show() }
                itemView.setOnClickListener { Toast.makeText(itemView.context, "Kamu memilih ${person.name}", Toast.LENGTH_SHORT).show() }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding = ItemCardviewPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listPerson[position])
    }

    override fun getItemCount(): Int = listPerson.size
}