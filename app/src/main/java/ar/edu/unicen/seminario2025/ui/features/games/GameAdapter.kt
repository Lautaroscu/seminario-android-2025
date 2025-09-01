package ar.edu.unicen.seminario2025.ui.features.games

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario2025.R
import ar.edu.unicen.seminario2025.databinding.ActivityGamesBinding
import ar.edu.unicen.seminario2025.databinding.GameBinding
import ar.edu.unicen.seminario2025.ddl.models.games.GameDTO
import com.bumptech.glide.Glide

class GamesAdapter(
    private var games: List<GameDTO>
) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    class GameViewHolder(
      private val gameBinding: GameBinding
    ) : RecyclerView.ViewHolder(gameBinding.root) {
        fun bind(game : GameDTO) {
            gameBinding.tvName.text = game.name
            gameBinding.tvReleased.text = game.released
            Glide.with(gameBinding.root)
                .load(game.backgroundImage)
                .into(gameBinding.gameImg)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GameBinding.inflate(layoutInflater , parent , false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int = games.size
}
