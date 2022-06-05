package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.models.Drink
import com.example.presentation.databinding.ItemCocktailBinding

class CockTailAdapter : RecyclerView.Adapter<CockTailAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCocktailBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int): Unit =
        with(holder.binding) {
            val image = differ.currentList[position]
            val context = holder.itemView.context
            cockTailName.text = image.strDrink
            Glide.with(context)
                .load(image.strDrinkThumb)
                .transform(CenterInside(), RoundedCorners(round))
                .into(cockTailImage)
        }
    companion object {
        const val round = 24
    }

    override fun getItemCount(): Int = differ.currentList.size
}
