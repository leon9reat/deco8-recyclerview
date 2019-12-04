package com.medialink.deco8recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medialink.deco8recyclerview.models.Hero
import kotlinx.android.synthetic.main.item_cardview_hero.view.*

class CardViewHeroAdapter(private val listHero: List<Hero>) :
    RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            with(itemView) {
                Glide.with(this.context)
                    .load(hero.photo)
                    .override(350, 550)
                    .into(img_item_photo)
                tv_item_name.text = hero.name
                tv_item_description.text = hero.description

                btn_set_favorite.setOnClickListener {
                    Toast.makeText(
                        this.context,
                        "Favorite ${hero.name}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                btn_set_share.setOnClickListener {
                    Toast.makeText(
                        this.context,
                        "Share ${hero.name}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                this.setOnClickListener {
                    onItemClickCallback?.onItemClicked(hero)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listHero[position])
    }
}