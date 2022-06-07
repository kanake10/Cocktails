package com.example.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.Constants.DEFAULT_CALL
import com.example.core.Constants.NON_ALCOHOLIC_CALL
import com.example.core.Constants.ORDINARY_DRINK_CALL
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCockTailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()

        recyclerView = binding.cockTailsRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = cockTailAdapter

        /**
         * fetch cocktails
         */
        viewModel.getCockTails(DEFAULT_CALL)

    }

    /**
     * delay the shimmer effect for 3 secs
     */
    companion object {
        const val delay = 3000
    }

    private fun subscribeToObservers() {
        viewModel.cockTailStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.let {
                        cockTailAdapter.submitList(it.data)
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
