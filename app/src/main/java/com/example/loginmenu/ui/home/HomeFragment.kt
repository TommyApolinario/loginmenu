package com.example.loginmenu.ui.home

import com.example.loginmenu.ui.home.HomeViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.loginmenu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textHome
        homeViewModel!!.text.observe(viewLifecycleOwner) { s -> textView.text = s }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}