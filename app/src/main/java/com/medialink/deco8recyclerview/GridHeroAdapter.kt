package com.medialink.deco8recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medialink.deco8recyclerview.models.Hero
import kotlinx.android.synthetic.main.item_grid_hero.view.*

class GridHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .override(350, 550)
                    .into(img_item_photo)
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_hero, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listHero[position])
    }
}