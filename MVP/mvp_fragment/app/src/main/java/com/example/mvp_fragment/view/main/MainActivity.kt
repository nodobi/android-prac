package com.example.mvp_fragment.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.R
import com.example.mvp_fragment.databinding.ActivityMainBinding
import com.example.mvp_fragment.view.main.presenter.MainContract
import com.example.mvp_fragment.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var binding: ActivityMainBinding
    private lateinit var mPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPresenter = MainPresenter().apply {
            view = this@MainActivity
        }

        binding.btmNavigation.run {
            setOnItemSelectedListener(mPresenter.onNavItemSelectFunc)
            selectedItemId = R.id.item_home
        }
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }
}