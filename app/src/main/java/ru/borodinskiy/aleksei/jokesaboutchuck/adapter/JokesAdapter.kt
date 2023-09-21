package ru.borodinskiy.aleksei.jokesaboutchuck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.CardJokeBinding
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes

interface OnInteractionListener {
    fun onShowDetail(jokes: Jokes)
}

class JokesAdapter(
    private val onInteractionListener: OnInteractionListener
) :
    ListAdapter<Jokes, JokesAdapter.JokesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {

        return JokesViewHolder(
            CardJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onInteractionListener
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class JokesViewHolder(
        private val binding: CardJokeBinding,
        private val onInteractionListener: OnInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(jokes: Jokes) {

            binding.apply {

                value.text = jokes.value

                showButton.setOnClickListener {

                    onInteractionListener.onShowDetail(jokes)
                }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Jokes>() {
            override fun areItemsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
                return oldItem == newItem
            }
        }
    }
}