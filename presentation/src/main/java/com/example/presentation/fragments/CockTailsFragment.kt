package com.example.presentation.fragments

import android.R
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.Status
import com.example.presentation.adapter.CockTailAdapter
import com.example.presentation.databinding.FragmentCockTailsBinding
import com.example.presentation.viemodel.CockTailViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class CockTailsFragment : Fragment() {
    private var _binding: FragmentCockTailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CockTailViewModel by viewModel()
    private val cockTailAdapter = CockTailAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var shimmerView : ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCockTailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            subscribeToObservers()
        },3000)


        shimmerView = binding.shimmerView
        progressBar = binding.progressBar
        recyclerView = binding.cockTailsRecyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.adapter = cockTailAdapter


        viewModel.getCockTails(cocktail = "Cocktail")

    }

    private fun subscribeToObservers() {
        viewModel.cockTailStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    shimmerView.stopShimmer()
                    shimmerView.visibility = View.GONE
                    it.let {
                        cockTailAdapter.differ.submitList(it.data)
                    }
                }
                Status.LOADING -> {
                    shimmerView.startShimmer()
                    shimmerView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    shimmerView.stopShimmer()
                    shimmerView.visibility = View.GONE
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}