package com.example.myhouse.ui.doors

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhouse.R
import com.example.myhouse.databinding.FragmentListBinding
import com.example.myhouse.ui.DialogText
import com.example.myhouse.ui.recyclerUtils.SwipeHelper
import org.koin.androidx.viewmodel.ext.android.viewModel


class DoorsListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: DoorsListViewModel by viewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentListBinding.bind(view)
        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = DoorsAdapter()
        }
        val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(binding.recyclerView) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                val buttons: List<UnderlayButton>
                val favoriteButton = favoriteButton(position)
                val changeNameButton = changeNameButton(position)
                buttons = listOf(favoriteButton, changeNameButton)
                return buttons
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        viewModel.doorsList.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as DoorsAdapter).setDoors(it)
        }
        binding.swiperefreshLayout.setOnRefreshListener {
            viewModel.updateList()
        }
        viewModel.closeLoadingRefresh.observe(viewLifecycleOwner) {
            binding.swiperefreshLayout.isRefreshing = false
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressLayout.isVisible = it
            binding.progress.isVisible = it
            binding.recyclerView.isVisible = !it
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
            }, PADDING, resources.getDrawable(R.drawable.star)
        )
    }

    private fun changeNameButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext(),
            android.R.color.white,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    val dialog = DialogText(listener = object : DialogText.NoticeDialogListener {
                        override fun onSaveName(text: String) {
                            viewModel.editName(position, text)
                        }
                    })
                    dialog.show(parentFragmentManager, "TAG")
                }
            }, PADDING, resources.getDrawable(R.drawable.edit)
        )
    }

    companion object {
        private const val PADDING = 20.0f
        fun newInstance() = DoorsListFragment()
    }

}