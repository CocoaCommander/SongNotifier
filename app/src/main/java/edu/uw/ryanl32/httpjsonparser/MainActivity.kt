package edu.uw.ryanl32.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.uw.ryanl32.httpjsonparser.DataClasses.Card
import edu.uw.ryanl32.httpjsonparser.DataClasses.Data
import edu.uw.ryanl32.httpjsonparser.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

const val API_KEY = "68d85771-fdbd-46d4-98be-5806d0356607"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var randomDexNum: Int = Random.nextInt(1, 899)

    private val cardApp: CardApplication by lazy { application as CardApplication }
    private val dataRepository by lazy { cardApp.dataRepository }

    private lateinit var receivedCard: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

            // val cardManager = cardApp.cardManager

            btnRefresh.setOnClickListener {
                loadCards(randomDexNum, binding)
                randomDexNum = Random.nextInt(1, 899)
            }
        }
    }

    private fun loadCards(dexNum: Int, binding: ActivityMainBinding) {
        lifecycleScope.launch {
            runCatching {
                Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_SHORT).show()
                val card: Card = dataRepository.getCard("nationalPokedexNumbers:[${dexNum} TO ${dexNum}]")
                receivedCard = card.data[Random.nextInt(0, card.data.size)]
                with(binding) {
                    tvCardName.text = receivedCard.name
                    ivCardArt.load(receivedCard.images.large)
                }
            }.onFailure {
                Toast.makeText(this@MainActivity, "Load failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}