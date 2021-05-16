package edu.uw.ryanl32.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.ryanl32.httpjsonparser.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var randomDexNum: Int = Random.nextInt(1, 899)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

        }
    }
}