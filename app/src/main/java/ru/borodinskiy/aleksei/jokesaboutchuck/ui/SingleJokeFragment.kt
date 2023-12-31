package ru.borodinskiy.aleksei.jokesaboutchuck.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.jokesaboutchuck.adapter.JokesAdapter
import ru.borodinskiy.aleksei.jokesaboutchuck.adapter.OnInteractionListener
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentSingleJokeBinding
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes
import ru.borodinskiy.aleksei.jokesaboutchuck.viewmodel.JokesViewModel

@AndroidEntryPoint
class SingleJokeFragment : Fragment() {

    companion object {
        const val JOKE_ID = "jokeId"
    }

    private lateinit var binding: FragmentSingleJokeBinding
    private val viewModel: JokesViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleJokeBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = JokesAdapter(object : OnInteractionListener {
            override fun onShowDetail(jokes: Jokes) {
                return
            }

        })

        recyclerView.adapter = adapter

        val jokeId = arguments?.getInt(JOKE_ID)

        if (jokeId != null) {
            viewModel.getJokeById(jokeId).observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        return binding.root
    }


}