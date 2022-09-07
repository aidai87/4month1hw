package com.example.a4month1hw.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.a4month1hw.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {

            if (binding.editText.text != null) {
                val text = binding.editText.text
                val news = News( text.toString(), System.currentTimeMillis())
                val bundle = bundleOf("news" to news)
                parentFragmentManager.setFragmentResult("rk_news", bundle)
                findNavController().navigateUp()
            }
        }
    }

}