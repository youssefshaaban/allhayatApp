package app.alhyatt.center.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager


import app.alhyatt.center.R
import app.alhyatt.center.base.BaseFragment
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.databinding.FragmentHomeBinding
import app.alhyatt.center.ui.filter.FilterActivity
import app.alhyatt.center.ui.home.HomeViewModel
import app.alhyatt.center.ui.home.SpecilistHomeAdapter
import app.alhyatt.center.utilis.AppUtils
import app.alhyatt.center.utilis.SavePrefs
import app.alhyatt.center.utilis.ambuklance


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=getViewDataBinding()
        binding.recyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter= SpecilistHomeAdapter()
        binding.contentSearch.setOnClickListener {
            startActivity(FilterActivity.getIntent(binding.root.context))
        }
        binding.search.setOnClickListener {  startActivity(FilterActivity.getIntent(binding.root.context))}
        SavePrefs(binding.root.context,User::class.java).load()?.let {
            binding.userName.text=it.name

        }
        binding.call.setOnClickListener {
            AppUtils.openDual(binding.root.context, ambuklance)
        }
    }
//
//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }
}