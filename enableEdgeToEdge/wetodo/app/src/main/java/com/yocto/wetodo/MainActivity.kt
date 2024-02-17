package com.yocto.wetodo

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.yocto.wetodo.calendar.CalendarFragment
import com.yocto.wetodo.databinding.ActivityMainBinding
import com.yocto.wetodo.theme.updateTheme
import com.yocto.wetodo.todo.TodoFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var drawerLayout: DrawerLayout private set
    private lateinit var navigationView: NavigationView

    private lateinit var fragmentContainerView: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView

    private var colorPrimary: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        updateTheme(this)

        super.onCreate(savedInstanceState)

        initResource()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawerLayout()
        initNavigationView()
        initFragmentContainerView()
        initBottomNavigationView()
    }

    fun updateStatusBarColor(@ColorInt statusBarColor: Int) {
        drawerLayout.setStatusBarBackgroundColor(statusBarColor)
    }

    private fun initResource() {
        val typedValue = TypedValue()
        val theme = theme
        theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true)
        colorPrimary = typedValue.data;
    }

    private fun initFragmentContainerView() {
        this.fragmentContainerView = binding.appBarMain.fragmentContainerView
    }

    private fun initBottomNavigationView() {
        this.bottomNavigationView = binding.appBarMain.bottomNavView

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_todo -> {
                    navigationTodo()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_calendar -> {
                    navigationCalendar()
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
        
        bottomNavigationView.selectedItemId = R.id.navigation_calendar
    }

    private fun navigationTodo() {
        if (fragmentContainerView.getFragment<Fragment?>() is TodoFragment) {
            return
        }

        val todoFragment = TodoFragment.newInstance()

        supportFragmentManager.commit {
            setReorderingAllowed(true)

            replace(R.id.fragment_container_view, todoFragment)
        }
    }

    private fun navigationCalendar() {
        if (fragmentContainerView.getFragment<Fragment?>() is CalendarFragment) {
            return
        }

        val calendarFragment = CalendarFragment.newInstance()

        supportFragmentManager.commit {
            setReorderingAllowed(true)

            replace(R.id.fragment_container_view, calendarFragment)
        }
    }

    private fun initDrawerLayout() {
        this.drawerLayout = binding.drawerLayout
    }

    private fun initNavigationView() {
        this.navigationView = binding.navView

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Log.i("CHEOK", "home menu item in drawer pressed")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            Log.i("CHEOK", "home button pressed")
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }
}