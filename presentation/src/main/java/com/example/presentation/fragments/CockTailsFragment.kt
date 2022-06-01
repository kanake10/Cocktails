package com.example.presentation.fragments

import android.R
import android.os.Bundle
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
import org.koin.androidx.viewmodel.ext.android.viewModel


class CockTailsFragment : Fragment() {
    private var _binding: FragmentCockTailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CockTailViewModel by viewModel()
    private val cockTailAdapter = CockTailAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

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
        subscribeToObservers()

        progressBar = binding.progressBar
        recyclerView = binding.cockTailsRecyclerview
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.adapter = cockTailAdapter
        recyclerView.adapter = cockTailAdapter

        viewModel.getCockTails(cocktail = "Cocktail")

    }

    private fun subscribeToObservers() {
        viewModel.cockTailStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.isVisible = false
                    it.let {
                        cockTailAdapter.differ.submitList(it.data)
                    }
                }
                Status.LOADING -> {
                    progressBar.isVisible = true
                }
                Status.ERROR -> {
                    progressBar.isVisible = false
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}