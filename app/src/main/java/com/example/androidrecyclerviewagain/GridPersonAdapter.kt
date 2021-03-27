package com.example.androidrecyclerviewagain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidrecyclerviewagain.databinding.ItemGridPersonBinding

class GridPersonAdapter(private val listPerson: ArrayList<Person>) :
    RecyclerView.Adapter<GridPersonAdapter.GridViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridPersonAdapter.GridViewHolder {
        val binding =
            ItemGridPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridPersonAdapter.GridViewHolder, position: Int) =
        holder.bind(listPerson[position])

    override fun getItemCount(): Int = listPerson.size

    inner class GridViewHolder(private val binding: ItemGridPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(person.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(imgItemPhoto)
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(person)  }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Person)
    }
}