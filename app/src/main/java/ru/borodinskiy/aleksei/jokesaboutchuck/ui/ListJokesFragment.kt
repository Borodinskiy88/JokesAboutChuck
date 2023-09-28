package ru.borodinskiy.aleksei.jokesaboutchuck.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.jokesaboutchuck.R
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
                val bundle = bundleOf(
                    Pair("jokeId", jokes.jokeId)
                )
                findNavController().navigate(R.id.action_listJokesFragment_to_singleJokeFragment, bundle)
            }

        })

        recyclerView.adapter = adapter

        viewModel.allJokes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.getButton.setOnClickListener {
            if (checkForInternet(requireContext())) {
                viewModel.getJoke().observe(viewLifecycleOwner) {}
            } else {
                Toast.makeText(
                    requireContext(),
                    "Чаку Норрису нужен интернет!!!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
        }

        binding.createButton.setOnClickListener {
            findNavController().navigate(R.id.action_listJokesFragment_to_createJokeFragment)
        }

        return binding.root
    }


    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}