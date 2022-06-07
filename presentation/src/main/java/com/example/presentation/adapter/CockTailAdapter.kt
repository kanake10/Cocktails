package com.example.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.models.Drink
import com.example.presentation.R
import com.example.presentation.databinding.ItemCocktailBinding

class CockTailAdapter : ListAdapter<Drink, CockTailAdapter.ImageViewHolder>(DrinkDiffUtil) {

    class ImageViewHolder private constructor(private val binding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            with(binding) {
                if (adapterPosition % 2 == 1) {
                    drinkCardView.setCardBackgroundColor(
                        drinkCardView.context.resources.getColor(R.color.green_card
                        ))
                } else {
                    drinkCardView.setCardBackgroundColor(
                        drinkCardView.context.resources.getColor(R.color.purple_card
                        ))
                }
                val context = binding.root.context
                cockTailName.text = drink.strDrink
                Glide.with(context)
                    .load(drink.strDrinkThumb)
                    .transform(CenterInside(), RoundedCorners(round))
                    .into(cockTailImage)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemCocktailBinding.inflate(layoutInflater, parent, false)
                return ImageViewHolder(itemBinding)
            }
        }
    }

    object DrinkDiffUtil : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean = oldItem.idDrink == newItem.idDrink
        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder.create(parent)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val drink = getItem(position)
        holder.bind(drink = drink)
    }

    companion object {
        const val round = 24
    }

    override fun getItemCount(): Int = currentList.size
}
