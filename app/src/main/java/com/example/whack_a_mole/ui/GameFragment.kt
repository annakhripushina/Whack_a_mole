package com.example.whack_a_mole.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import com.example.whack_a_mole.App
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.GameFragmentBinding
import com.example.whack_a_mole.presentation.GameViewModel
import javax.inject.Inject
import kotlin.random.Random


class GameFragment : Fragment() {
    @Inject
    lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding
    private lateinit var timer: CountDownTimer
    private var score = 0
    private lateinit var tableLayout : TableLayout
    private val arrayImages: Array<Array<FrameLayout?>> = Array(3) { arrayOfNulls(3) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tableLayout = binding.tableLayout
        binding.score.text = getString(R.string.score, score.toString())
        binding.timer.text = getString(R.string.time, String.format("%02d", 30))

        timer = object : CountDownTimer(30000, 500) {

            override fun onTick(millisUntilFinished: Long) {
                setGameView(millisUntilFinished)
            }

            override fun onFinish() {
                viewModel.setBestScore(score)
                val fragment = ResultFragment.newInstance(score)
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            }
        }.start()
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    private fun setGameView(millisUntilFinished: Long) {
        val randomRow = Random.nextInt(HOLE_ROWS)
        val randomColumn = Random.nextInt(HOLE_COLUMNS)

        binding.timer.text =
            getString(R.string.time, String.format("%02d", millisUntilFinished / 1000))

        for (i in 0 until HOLE_ROWS) {
            for (j in 0 until HOLE_COLUMNS) {
                val frameLayout = FrameLayout(requireContext())
                val imageView = ImageView(requireContext())
                val params = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
                )

                params.setMargins(10, 10, 10, 10)
                frameLayout.layoutParams = params
                frameLayout.setBackgroundResource(R.drawable.grass)

                if (i == randomRow && j == randomColumn) {
                    imageView.setImageResource(R.drawable.mole)
                    imageView.setOnClickListener {
                        score++
                        binding.score.text = getString(R.string.score, score.toString())
                        imageView.setImageResource(R.drawable.whack)
                        imageView.isEnabled = false
                    }
                    imageView.shakeView()
                }

                frameLayout.addView(imageView)
                arrayImages[i][j] = frameLayout
            }
        }

        tableLayout.removeAllViewsInLayout()

        for (i in 0 until HOLE_ROWS) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
            )
            tableRow.gravity = Gravity.CENTER
            for (j in 0 until HOLE_COLUMNS) {
                tableRow.addView(arrayImages[i][j], j)
            }

            tableLayout.addView(tableRow, i)
        }
    }

    private fun View.shakeView() {
        ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 30F, 0F, 30F).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 500
            start()
        }
    }

    companion object {
        const val HOLE_ROWS = 3
        const val HOLE_COLUMNS = 3
    }
}