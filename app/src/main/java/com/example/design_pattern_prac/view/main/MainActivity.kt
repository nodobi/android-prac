package com.example.design_pattern_prac.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.design_pattern_prac.R
import com.example.design_pattern_prac.adapter.ImageAdapter
import com.example.design_pattern_prac.data.ImageData
import com.example.design_pattern_prac.view.main.presenter.MainContract
import com.example.design_pattern_prac.view.main.presenter.MainPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MainContract.View{

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        imageAdapter = ImageAdapter(this)
        recyclerView.adapter = imageAdapter

        presenter = MainPresenter().apply {
            view = this@MainActivity
            imageData = ImageData
            adapterView = imageAdapter
            adapterModel = imageAdapter
        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }

        presenter.loadItems(this, false)
    }

    override fun showToast(title: String) {
        Toast.makeText(this, "show $title", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            presenter.loadItems(this@MainActivity, true)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}