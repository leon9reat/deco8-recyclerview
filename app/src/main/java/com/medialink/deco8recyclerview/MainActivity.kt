package com.medialink.deco8recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.medialink.deco8recyclerview.models.Hero
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickCallback {
    private val list = ArrayList<Hero>()
    private var title = "Mode List"
    private var mode: Int = 0

    companion object {
        const val STATE_TITLE = "STATE_TITLE"
        const val STATE_LIST = "STATE_LIST"
        const val STATE_MODE = "STATE_MODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_heros.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            list.addAll(getListHero())
            showRecyclerList()
            mode = R.id.action_list
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        title = savedInstanceState.getString(STATE_TITLE).toString()
        val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
        val stateMode = savedInstanceState.getInt(STATE_MODE, 0)

        setActionBarTitle(title)
        stateList?.let { list.addAll(it) }
        setMode(stateMode)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecylerGrid()
            }
            R.id.action_card -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        mode = itemId
        setActionBarTitle(title)
    }

    private fun showRecyclerList() {
        rv_heros.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        listHeroAdapter.setOnItemClickCallback(this)
        rv_heros.adapter = listHeroAdapter
    }

    private fun showRecylerGrid() {
        rv_heros.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        gridHeroAdapter.setOnItemClickCallback(this)
        rv_heros.adapter = gridHeroAdapter
    }

    private fun showRecyclerCardView() {
        rv_heros.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        cardViewHeroAdapter.setOnItemClickCallback(this)
        rv_heros.adapter = cardViewHeroAdapter
    }

    private fun getListHero(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (position in dataName.indices) {
            val hero = Hero(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu Memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(hero: Hero) {
        showSelectedHero(hero)
    }
}
