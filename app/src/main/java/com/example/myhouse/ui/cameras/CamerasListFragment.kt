package com.example.myhouse.ui.cameras

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhouse.R
import com.example.myhouse.databinding.FragmentListBinding
import com.example.myhouse.ui.recyclerUtils.SwipeHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CamerasListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: CamerasListViewModel by viewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentListBinding.bind(view)

        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = CamerasAdapter()
        }
        val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(binding.recyclerView) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val buttons: List<UnderlayButton>
                val favoriteButton = favoriteButton(position)
                buttons = listOf(favoriteButton)
                return buttons
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        viewModel.camerasList.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as CamerasAdapter).setList(it)
        }
        binding.swiperefreshLayout.setOnRefreshListener {
            viewModel.updateListFromNetwork()
        }
        viewModel.closeLoadingRefresh.observe(viewLifecycleOwner) {
            binding.swiperefreshLayout.isRefreshing = false
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressLayout.visibility = View.VISIBLE
            } else {
                binding.progressLayout.visibility = View.GONE
            }
        }
    }

    private fun favoriteButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext(),
            android.R.color.white,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    viewModel.onFavoriteClick(position)
                }
            }, 50.0f, resources.getDrawable(R.drawable.star)
        )
    }

    companion object {
        fun newInstance() = CamerasListFragment()
    }
}