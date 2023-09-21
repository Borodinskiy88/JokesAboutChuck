package ru.borodinskiy.aleksei.jokesaboutchuck.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentCreateJokeBinding

@AndroidEntryPoint
class CreateJokeFragment : Fragment() {

    private lateinit var binding: FragmentCreateJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateJokeBinding.inflate(inflater, container, false)


        return binding.root
    }


}