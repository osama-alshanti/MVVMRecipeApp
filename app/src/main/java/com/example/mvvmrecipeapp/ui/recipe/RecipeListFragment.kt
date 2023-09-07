package com.example.mvvmrecipeapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.utils.setVisibility
import com.example.mvvmrecipeapp.databinding.FragmentRecipeListBinding
import com.example.mvvmrecipeapp.ui.base.UiState
import com.example.mvvmrecipeapp.ui.detail.ClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeListFragment : Fragment(), ClickListener {

    private var _binding: FragmentRecipeListBinding?= null
    private val binding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        setUp()
        setObserver()

    }

    private fun initToolbar(){
        binding.toolbar.txtTitle.text = "Recipes Page"
    }
    private fun setUp(){
        recipeAdapter = RecipeAdapter(ArrayList(), this)
        binding.rvRecipes.adapter = recipeAdapter
    }

    private fun setObserver(){
        recipeViewModel.getRecipe("1", "")
        lifecycleScope.launchWhenStarted {
            recipeViewModel.recipeStateFlow.collect{
                when(it){
                    is UiState.Loading -> {
                        binding.progress.setVisibility(true)
                        binding.rvRecipes.setVisibility(false)
                    }
                    is UiState.Success ->{
                        binding.progress.setVisibility(false)
                        binding.rvRecipes.setVisibility(true)

                        recipeAdapter.setData(it.data)
                    }
                    is UiState.Error ->{
                        binding.progress.setVisibility(false)
                        binding.rvRecipes.setVisibility(false)

                    }
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val TAG = "RecipeListFragment"
    }

    override fun onRecipeItemClicked(id: Int) {
        val bundle = bundleOf("recipeId" to id)
        val action = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(id)
        findNavController().navigate(action)
    }
}