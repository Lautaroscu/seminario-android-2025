package ar.edu.unicen.seminario2025.ui.features.games
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unicen.seminario2025.databinding.ActivityGamesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach



@AndroidEntryPoint
class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding
    private val viewModel by viewModels<GamesViewModel>()
    private lateinit var adapter: GamesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflás el binding
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.games.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.games.onEach { games ->
            binding.recyclerView.adapter = GamesAdapter(games)

        }.launchIn(lifecycleScope)
}
    }
