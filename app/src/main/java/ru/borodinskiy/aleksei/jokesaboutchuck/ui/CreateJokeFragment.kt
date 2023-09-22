package ru.borodinskiy.aleksei.jokesaboutchuck.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.jokesaboutchuck.R
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentCreateJokeBinding
import ru.borodinskiy.aleksei.jokesaboutchuck.viewmodel.JokesViewModel
import java.util.UUID

@AndroidEntryPoint
class CreateJokeFragment : Fragment() {

    private lateinit var binding: FragmentCreateJokeBinding
    private val viewModel: JokesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateJokeBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener {
            if (binding.jokeText.text.toString().isBlank()) {
                    Snackbar.make(
                        binding.root,
                        R.string.necessary_fill,
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
            }
            val id = UUID.randomUUID()

            viewModel.changeJoke(
                binding.jokeText.text.toString(),
                id.toString()
            )
            viewModel.save()
            findNavController().navigate(R.id.action_createJokeFragment_to_listJokesFragment)
        }

        return binding.root
    }

}