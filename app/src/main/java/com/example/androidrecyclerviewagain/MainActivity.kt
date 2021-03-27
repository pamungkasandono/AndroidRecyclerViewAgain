package com.example.androidrecyclerviewagain

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrecyclerviewagain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var title = "Mode List"
    private val list = ArrayList<Person>()
    private var mode: Int = 0

    companion object {
        private const val STATE_TITLE = "state_title"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPerson.setHasFixedSize(true)

        // state
        if (savedInstanceState == null) {
            setActionBarTitle(title)
            list.addAll(getListPerson())
            showRecyclerList()
            mode = R.id.action_list
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Person>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)
            setActionBarTitle(title)
            if (stateList != null) {
                list.addAll(stateList)
            }
            setMode(stateMode)
        }
        //./ end state

        list.addAll(getListPerson())
        showRecyclerList()

        setActionBarTitle(title)

    }

    private fun showSelectedPerson(person: Person) {
        Toast.makeText(this, "MainActivity Kamu memilih ${person.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getListPerson(): ArrayList<Person> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Person>()
        for (i in dataName.indices) {
            val dataPerson = Person(
                dataName[i],
                dataDesc[i],
                dataPhoto[i]
            )
            listHero.add(dataPerson)
        }
        return listHero
    }

    // state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }
    //./ end state

    private fun showRecyclerList() {
        binding.rvPerson.layoutManager = LinearLayoutManager(this)
        val listPersonAdapter = PersonAdapter(list)
        binding.rvPerson.adapter = listPersonAdapter

        listPersonAdapter.setOnItemClickCallback(object : PersonAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Person) {
                showSelectedPerson(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        binding.rvPerson.layoutManager = GridLayoutManager(this, 2)
        val gridPersonAdapter = GridPersonAdapter(list)
        binding.rvPerson.adapter = gridPersonAdapter

        gridPersonAdapter.setOnItemClickCallback(object : GridPersonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Person) {
                showSelectedPerson(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        binding.rvPerson.layoutManager = LinearLayoutManager(this)
        val cardViewPersonAdapter = CardViewPersonAdapter(list)
        binding.rvPerson.adapter = cardViewPersonAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
                title = "Mode List"
            }
            R.id.action_grid -> {
                showRecyclerGrid()
                title = "Mode Grid"
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
                title = "Mode CardView"
            }
        }
        Log.d("setMode: ", selectedMode.toString() + " --- " + R.id.action_cardview)
        mode = selectedMode
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}













