package com.example.myhouse.ui.doors

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhouse.R
import com.example.myhouse.data.database.DoorsEntity
import com.example.myhouse.data.database.mapToDoorsEntity
import com.example.myhouse.databinding.FragmentListBinding
import com.example.myhouse.ui.DialogText
import com.example.myhouse.ui.recyclerUtils.SwipeHelper
import org.koin.androidx.viewmodel.ext.android.viewModel


class DoorsListFragment : Fragment(R.layout.fragment_list), DialogText.NoticeDialogListener {

    private val viewModel: DoorsListViewModel by viewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentListBinding.bind(view)
        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = DoorsAdapter()
        }
        viewModel.doorsList.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as DoorsAdapter).setDoors(it)
            val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(binding.recyclerView) {
                override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                    var buttons: List<UnderlayButton>
                    val favoriteButton = favoriteButton(it[position].mapToDoorsEntity())
                    val changeNameButton = changeNameButton(it[position].mapToDoorsEntity())
                    buttons = listOf(favoriteButton, changeNameButton)
                    return buttons
                }
            })

            itemTouchHelper.attachToRecyclerView(binding.recyclerView)
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

    private fun favoriteButton(entity: DoorsEntity): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext(),
            android.R.color.white,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    entity.favorites = !entity.favorites
                    viewModel.onFavoriteClick(entity)
                }
            }, resources.getDrawable(R.drawable.star)
        )
    }

    private fun changeNameButton(entity: DoorsEntity): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext(),
            android.R.color.white,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    val dialog = DialogText(listener = object : DialogText.NoticeDialogListener {
                        override fun onSaveName(text: String) {
                            Log.d("info11", entity.name)
                            entity.name = text
                            viewModel.editName(entity)
                            Log.d("info11", entity.name)
                        }
                    })
                    dialog.show(parentFragmentManager, "TAG")
                }
            }, resources.getDrawable(R.drawable.edit)
        )
    }

    companion object {
        fun newInstance() = DoorsListFragment()
    }

    override fun onSaveName(text: String) {

    }
}