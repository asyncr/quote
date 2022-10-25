package com.example.quote.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.quote.R
import com.example.quote.R.id
import com.example.quote.databinding.ActivityMainBinding
import com.example.quote.presentation.ui.fragments.add.AddQuote
import com.example.quote.presentation.ui.fragments.delete.DeleteQuote
import com.example.quote.presentation.ui.fragments.home.Home
import com.example.quote.presentation.ui.fragments.list.ListQuote
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var drawer_layout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        navigationAnimation(binding)
    }

    private fun navigationAnimation(binding: ActivityMainBinding) {
        with(binding) {
            drawer_layout = drawerLayout
            id.host_fragment.let {
                navController = Navigation.findNavController(this@MainActivity, it)
            }
            toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
            navigationView.setupWithNavController(navController)
            navigationView.setNavigationItemSelectedListener { item ->
                var (idI, title) = item.itemId to item.title.toString()
                when (idI) {
                    id.nav_home -> replacementFragment(Home())
                    id.nav_add -> replacementFragment(AddQuote())
                    id.nav_filter -> replacementFragment(ListQuote())
                    id.nav_del -> replacementFragment(DeleteQuote())
                }
                binding.toolbar.title = title
                true
            }
            navigationView.isSelected
            setupDrawerAnimation(this)
        }
    }

    private fun setupDrawerAnimation(binding: ActivityMainBinding) {
        drawer_layout?.setScrimColor(Color.TRANSPARENT)
        drawer_layout?.elevation ?: 0f
        drawer_layout?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val slideX = drawerView.width * slideOffset
                binding.container.apply {
                    translationX = slideX
                    scaleY = 1 - (slideOffset / 10)
                    val icon = when {
                        slideOffset > 0.5 -> getDrawable(R.drawable.ic_arrow)
                        else -> getDrawable(R.drawable.ic_menu)
                    }
                    binding.toolbar.navigationIcon = icon
                }
            }

            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        drawer_layout?.closeDrawer(GravityCompat.START)
    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, drawer_layout)

    private fun replacementFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(id.host_fragment, fragment).commit()
        drawer_layout?.closeDrawer(GravityCompat.START)
    }

}
