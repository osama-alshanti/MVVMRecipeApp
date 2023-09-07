package com.example.mvvmrecipeapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.databinding.FragmentRecipeDetailBinding
import com.example.mvvmrecipeapp.utils.setVisibility


class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding?= null
    private val binding get() = _binding!!
    private val args: RecipeDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
    }

    private fun initToolbar(){
        binding.toolbar.ivBack.setVisibility(true)
        binding.toolbar.txtTitle.text = "Detail Recipe Page"
        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}