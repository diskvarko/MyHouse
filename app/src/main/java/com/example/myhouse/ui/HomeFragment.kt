package com.example.myhouse.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myhouse.R
import com.example.myhouse.databinding.FragmentHomeBinding
import com.example.myhouse.ui.cameras.CamerasListFragment
import com.example.myhouse.ui.doors.DoorsListFragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var demoCollectionPagerAdapter: PagerAdapter? = null
    private var viewPager: ViewPager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        demoCollectionPagerAdapter =
            PagerAdapter(childFragmentManager, requireContext())
        viewPager = view.findViewById(R.id.pager)
        viewPager?.adapter = demoCollectionPagerAdapter
        binding.tabLayout.setupWithViewPager(viewPager)
    }

    override fun onDestroyView() {
        demoCollectionPagerAdapter = null
        viewPager = null
        _binding = null
        super.onDestroyView()
    }
}

class PagerAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(item: Int): Fragment {
        when (item) {
            HomeAdapterItems.CAMERAS.itemNum -> return CamerasListFragment.newInstance()
            HomeAdapterItems.DOORS.itemNum -> return DoorsListFragment.newInstance()
        }
        return CamerasListFragment();
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            HomeAdapterItems.CAMERAS.itemNum -> return context.getString(HomeAdapterItems.CAMERAS.title)
            HomeAdapterItems.DOORS.itemNum -> return context.getString(
                HomeAdapterItems.DOORS.title
            )
        }
        return ""
    }
}

