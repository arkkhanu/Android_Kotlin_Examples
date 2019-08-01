package com.example.firebasechatapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebasechatapp.fragment.MyAccountFragment
import com.example.firebasechatapp.fragment.PeopleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(PeopleFragment())

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_people -> {
                    replaceFragment(PeopleFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_my_account -> {
                    replaceFragment(MyAccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(framgment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout,framgment)
            .commit()
    }

}
