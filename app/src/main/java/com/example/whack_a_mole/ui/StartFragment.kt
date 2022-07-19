package com.example.whack_a_mole.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whack_a_mole.App
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.StartFragmentBinding
import com.example.whack_a_mole.presentation.StartViewModel
import javax.inject.Inject

class StartFragment : Fragment() {
    @Inject
    lateinit var viewModel: StartViewModel

    private lateinit var binding: StartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = StartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bestScoreValue.text = viewModel.getBestScore().toString()

        binding.playButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, GameFragment())
                .addToBackStack("GameFragment")
                .commit()
        }
    }

}