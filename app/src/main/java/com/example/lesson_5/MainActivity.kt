package com.example.lesson_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val themePreferences = ThemePreferences(this)
        val themeState = themePreferences.getThemeState()
        if (themeState == "dark") {
            setTheme(R.style.Theme_Lesson_5)
        } else {
            setTheme(R.style.Theme_Lesson_moon)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu()
    }

    private fun bottomNavMenu() {
        bottom_navigation_menu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.firstFragment -> {
                    loadFragment(FirstFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.secondFragment -> {
                    loadFragment(SecondFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.thirdFragment -> {
                    loadFragment(ThirdFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fourthFragment -> {
                    loadFragment(FourthFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.fifthFragment -> {
                    loadFragment(FifthFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentTransaction, fragment)
            fragmentTransaction.commit()
        }
    }
}