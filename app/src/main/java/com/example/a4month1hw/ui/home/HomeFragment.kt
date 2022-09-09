package com.example.a4month1hw.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a4month1hw.R
import com.example.a4month1hw.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var adapter = NewsAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }

        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable("news") as News
            adapter.addItem(news)
        }

        binding.recyclerview.adapter = adapter
        adapter.onLongClick = { pos ->
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete item")
            alertDialog.setMessage("Delete item")
                .setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                    adapter.delete(pos)
                    adapter.notifyItemRemoved(pos)
                }).setNegativeButton("no", DialogInterface.OnClickListener { dialog, which ->

            })
            alertDialog.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}