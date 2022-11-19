package com.example.food.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.food.R
import com.example.food.databinding.ActivityMainBinding
import com.example.food.databinding.TabButtonBinding
import com.example.food.view.adapter.PagerAdapter
import com.example.food.view.fragment.DrinkFragment
import com.example.food.view.fragment.FruitFragment
import com.example.food.view.fragment.NoodleFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var toggle: ActionBarDrawerToggle

    private var pressedTime: Long = 0

    val drinkFragment = DrinkFragment()

    val fruitFragment = FruitFragment()

    val noodleFragment = NoodleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setNavigationView()
            setViewPager()
            setToolbar()
        }
    }

    private fun setNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener {
            val intent = Intent()
            when (it.itemId) {
                R.id.menu_drink -> {
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.kurly.com/categories/914002")
                    startActivity(intent)
                }
                R.id.menu_fruit -> {
                    intent.action = Intent.ACTION_VIEW
                    intent.data =
                        Uri.parse("https://www.kurly.com/categories/908008?filters=&page=1")
                    startActivity(intent)
                }
                R.id.menu_noodle -> {
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.kurly.com/categories/913001")
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(this)
        val titles = mutableListOf(
            resources.getString(R.string.menu_drink),
            resources.getString(R.string.menu_fruit),
            resources.getString(R.string.menu_noodle)
        )
        pagerAdapter.setFragment(drinkFragment)
        pagerAdapter.setFragment(fruitFragment)
        pagerAdapter.setFragment(noodleFragment)

        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = setTabView(titles[position])
        }.attach()
    }

    private fun setTabView(title: String): View {
        val tabBinding = TabButtonBinding.inflate(layoutInflater)
        tabBinding.tvIcon.text = title

        when (title) {
            resources.getString(R.string.menu_drink) -> tabBinding.ivIcon.setImageResource(R.drawable.drink)
            resources.getString(R.string.menu_fruit) -> tabBinding.ivIcon.setImageResource(R.drawable.fruit)
            resources.getString(R.string.menu_noodle) -> tabBinding.ivIcon.setImageResource(R.drawable.noodle)
        }
        return tabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        when (item.itemId) {
            R.id.menu_drink -> {
                if (!item.isChecked) binding.ivFood.setImageResource(R.drawable.menu_drink)
            }
            R.id.menu_fruit -> {
                if (!item.isChecked) binding.ivFood.setImageResource(R.drawable.menu_fruit)
            }
            R.id.menu_noodle -> {
                if (!item.isChecked) binding.ivFood.setImageResource(R.drawable.menu_noodle)
            }
            R.id.menu_default -> {
                if (!item.isChecked) binding.ivFood.setImageResource(R.drawable.menu_food)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - pressedTime > 2000) {
                Toast.makeText(
                    this, resources.getString(R.string.toast_key_down), Toast.LENGTH_SHORT
                ).show()
                pressedTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}