package com.example.mvvmrecipeapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.databinding.FragmentRecipeDetailBinding
import com.example.mvvmrecipeapp.ui.base.UiState
import com.example.mvvmrecipeapp.utils.setVisibility
import com.example.mvvmrecipeapp.utils.fromHtml
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding?= null
    private val binding get() = _binding!!
    private val args: RecipeDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

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
        setObserve()
    }

    private fun initToolbar(){
        binding.toolbar.ivBack.setVisibility(true)
        binding.toolbar.txtTitle.text = "Detail Recipe Page"
        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setObserve(){
        viewModel.getRecipeById(args.recipeId.toString())
        lifecycleScope.launchWhenStarted {
            viewModel.getRecipeStateFlow.collect{
                when(it){
                    is UiState.Loading ->{
                        binding.progress.setVisibility(true)
                        binding.parent.setVisibility(false)
                    }
                    is UiState.Success ->{
                        binding.progress.setVisibility(false)
                        binding.parent.setVisibility(true)

                        populateRecipe(it.data[0])
                    }
                    is UiState.Error ->{
                        binding.progress.setVisibility(false)
                        binding.parent.setVisibility(false)

                    }
                }
            }
        }
    }

    private fun populateRecipe(recipe: RecipeNetworkEntity){
        Glide.with(requireActivity())
            .load(recipe.featuredImage)
            .into(binding.igvFood)

        binding.tvTitle.text = recipe.title.fromHtml()
        binding.tvDate.text = "Updated ${recipe.dateAdded} by Osama"
        binding.tvId.text = recipe.rating.toString().fromHtml()

        val ingredient = recipe.ingredients!!.joinToString("<br>")
        binding.tvDesc.text = ingredient.fromHtml()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}