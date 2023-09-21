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
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentListJokesBinding
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes
import ru.borodinskiy.aleksei.jokesaboutchuck.viewmodel.JokesViewModel

@AndroidEntryPoint
class ListJokesFragment : Fragment () {

    private lateinit var binding: FragmentListJokesBinding
    private val viewModel: JokesViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListJokesBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = JokesAdapter(object : OnInteractionListener {
            override fun onShowDetail(jokes: Jokes) {
                TODO("Not yet implemented")
            }

        })

        recyclerView.adapter = adapter

        viewModel.allJokes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.getButton.setOnClickListener {
            viewModel.getJoke().observe(viewLifecycleOwner) {}
        }

        return binding.root
    }
}