package com.wpi.android.basketballcounter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MatchListFragment"

class MatchListFragment : Fragment()  {

    private lateinit var matchRecyclerView: RecyclerView
    private var adapter: MatchAdapter? = MatchAdapter(emptyList())

    private val matchListViewModel: MatchListViewModel by lazy {
        ViewModelProviders.of(this).get(MatchListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_list, container, false)
        matchRecyclerView =
            view.findViewById(R.id.match_recycler_view) as RecyclerView
        matchRecyclerView.layoutManager = LinearLayoutManager(context)
        matchRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchListViewModel.matchListLiveData.observe(
            viewLifecycleOwner,
            Observer { matchs ->
                matchs?.let {
                    Log.i(TAG, "Got matchs ${matchs.size}")
                    updateUI(matchs)
                }
            })
    }

    private fun updateUI(games: List<Game>) {
        adapter = MatchAdapter(games)
        matchRecyclerView.adapter = adapter
    }

    private inner class MatchHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var game: Game

        private val titleTextView: TextView = itemView.findViewById(R.id.match_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.match_date)
        private val teamATextView: TextView = itemView.findViewById(R.id.team_A)
        private val teamBTextView: TextView = itemView.findViewById(R.id.team_B)
        private val scoreATextView: TextView = itemView.findViewById(R.id.score_A)
        private val scoreBTextView: TextView = itemView.findViewById(R.id.score_B)
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(game: Game) {
            this.game = game
            titleTextView.text = this.game.title
            dateTextView.text = this.game.date.toString()
            teamATextView.text = this.game.teamA
            teamBTextView.text = this.game.teamB
            scoreATextView.text = this.game.score_A.toString()
            scoreBTextView.text = this.game.score_B.toString()
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${game.title} clicked!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private inner class MatchAdapter(var games: List<Game>) : RecyclerView.Adapter<MatchHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : MatchHolder {
            val view = layoutInflater.inflate(R.layout.list_item_match, parent, false)
            return MatchHolder(view)
        }

        override fun onBindViewHolder(holder: MatchHolder, position: Int) {
            val match = games[position]
            holder.bind(match)
        }

        override fun getItemCount() = games.size
    }

    companion object {
        fun newInstance(): MatchListFragment {
            return MatchListFragment()
        }
    }
}