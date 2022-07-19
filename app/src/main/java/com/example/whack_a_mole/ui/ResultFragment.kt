package com.example.whack_a_mole.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.whack_a_mole.App
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.ResultFragmentBinding
import com.example.whack_a_mole.presentation.ResultViewModel
import javax.inject.Inject

class ResultFragment : Fragment() {
    @Inject
    lateinit var viewModel: ResultViewModel

    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = getScore() ?: return

        binding.score.text = score.toString()

        binding.bestScore.text = viewModel.getBestScore().toString()

        binding.menuButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, StartFragment())
                .commit()
        }

        binding.playAgainButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, GameFragment())
                .addToBackStack("GameFragment")
                .commit()
        }
    }

    private fun getScore(): Int? =
        arguments?.getInt(SCORE)

    companion object {
        fun newInstance(score: Int): ResultFragment =
            ResultFragment().apply {
                arguments = bundleOf(SCORE to score)
            }

        private const val SCORE = "SCORE"
    }

}