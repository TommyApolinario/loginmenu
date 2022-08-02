package com.example.loginmenu.ui.gallery

import com.example.loginmenu.ui.gallery.GalleryViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.loginmenu.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private var galleryViewModel: GalleryViewModel? = null
    private var binding: FragmentGalleryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textGallery
        galleryViewModel!!.text.observe(viewLifecycleOwner) { s -> textView.text = s }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}