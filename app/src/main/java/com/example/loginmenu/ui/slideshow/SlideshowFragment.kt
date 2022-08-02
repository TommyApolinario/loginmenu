package com.example.loginmenu.ui.slideshow

import com.example.loginmenu.ui.slideshow.SlideshowViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.loginmenu.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {
    private var slideshowViewModel: SlideshowViewModel? = null
    private var binding: FragmentSlideshowBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
        binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textSlideshow
        slideshowViewModel!!.text.observe(viewLifecycleOwner) { s -> textView.text = s }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}