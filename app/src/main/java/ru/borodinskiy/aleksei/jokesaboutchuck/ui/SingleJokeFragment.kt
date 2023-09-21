package ru.borodinskiy.aleksei.jokesaboutchuck.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentListJokesBinding
import ru.borodinskiy.aleksei.jokesaboutchuck.databinding.FragmentSingleJokeBinding

@AndroidEntryPoint
class SingleJokeFragment : Fragment() {

    private lateinit var binding: FragmentSingleJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleJokeBinding.inflate(inflater, container, false)




        return binding.root
    }


}