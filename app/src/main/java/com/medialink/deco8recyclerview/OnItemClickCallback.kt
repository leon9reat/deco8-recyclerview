package com.medialink.deco8recyclerview

import com.medialink.deco8recyclerview.models.Hero

interface OnItemClickCallback {
    fun onItemClicked(hero: Hero)
}