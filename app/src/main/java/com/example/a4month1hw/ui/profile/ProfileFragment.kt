package com.example.a4month1hw.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a4month1hw.Prefs
import com.example.a4month1hw.R
import com.example.a4month1hw.databinding.ActivityMainBinding
import com.example.a4month1hw.databinding.FragmentProfileBinding
import com.example.a4month1hw.glide.GlideYu


class ProfileFragment : Fragment() {
    private lateinit var prefs: Prefs
    private lateinit var binding: FragmentProfileBinding
    var launchForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode === AppCompatActivity.RESULT_OK) {
            val image = result.data?.data
            prefs.saveImageView(image.toString())
            binding.image.GlideYu(image.toString())
            binding.image.setImageURI(image)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = Prefs(requireContext())
        binding.image.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launchForResult.launch(intent)
        }
        binding.etSave.setText(prefs.isEditText());
        if (prefs.isImageView() != null){
            binding.image.GlideYu(prefs.isImageView()!!)
        }


    }

    override fun onDestroy() {
        prefs.saveEditText(binding.etSave.text.toString());
        super.onDestroy()
    }
}