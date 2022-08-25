package com.example.a4month1hw.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.a4month1hw.Prefs
import com.example.a4month1hw.databinding.FragmentBoardBinding


class BoardFragment : Fragment(), BoardAdapter.FinishBoard {
    private lateinit var binding: FragmentBoardBinding
    private val data :ArrayList<Board> =  ArrayList()
    private lateinit var boardAdapter:BoardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data.apply {
            add(Board("Привет", "Как дела?", "https://proprikol.ru/wp-content/uploads/2020/08/kartinki-informatika-17.jpg"))
            add(Board("World", "What's up?", "https://proprikol.ru/wp-content/uploads/2020/08/kartinki-informatika-17.jpg"))
            add(Board("Hello", "How are you?","https://proprikol.ru/wp-content/uploads/2020/08/kartinki-informatika-17.jpg"))
        }
        boardAdapter = BoardAdapter(data, this)
        binding.viewPager.adapter = boardAdapter
        binding.springDotsIndicator.setViewPager2(binding.viewPager)
        boardAdapter.setFinishBoard(this);

        binding.skipped.setOnClickListener(){
            navigate()
        }
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               requireActivity().finish()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.skipped.visibility = View.GONE
                } else  {
                    binding.skipped.visibility = View.VISIBLE
                }}
        })
    }

    fun navigate() {
        val prefs = Prefs(requireContext())
        prefs.saveBoardState()
        val navController = Navigation.findNavController(
            requireActivity(),
            com.example.a4month1hw.R.id.nav_host_fragment_activity_main
        )
        navController.navigateUp()
    }
    override fun clickm() {
navigate()
    }

}
