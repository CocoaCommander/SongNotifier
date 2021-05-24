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
const val CARD_DATA_KEY = "CARD_DATA_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var randomDexNum: Int = Random.nextInt(1, 899)

    private val cardApp: CardApplication by lazy { application as CardApplication }
    // private val dataRepository by lazy { cardApp.dataRepository }
    private val refreshCardManager: RefreshCardManager by lazy { cardApp.refreshCardManager }
    private val cardManager: CardManager by lazy { cardApp.cardManager}

    private lateinit var receivedCard: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

            // val cardManager = cardApp.cardManager
            if (cardManager.currCard != null) {
                tvCardName.text = cardManager.currCard?.name
                ivCardArt.load(cardManager.currCard?.images?.large)
            }

            btnRefresh.setOnClickListener {
                refreshCardManager.initCardRefresh()
                loadCards(cardManager.currCard, binding)
            }

            switchPeriodicRefresh.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    refreshCardManager.initCardRefresh()
                    loadCards(cardManager.currCard, binding)
                    refreshCardManager.startPeriodicCardRefresh()
                } else {
                    refreshCardManager.stopPeriodicCardRefresh()
                }
            }


        }
    }

    private fun loadCards(card: Data?, binding: ActivityMainBinding) {
        lifecycleScope.launch {
            runCatching {
                Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_SHORT).show()
                with(binding) {
                    // val tempData = intent.getParcelableExtra<Data>(CARD_DATA_KEY)
                    tvCardName.text = card?.name
                    ivCardArt.load(card?.images?.large)
                }
            }.onFailure {
                Toast.makeText(this@MainActivity, "Load failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}