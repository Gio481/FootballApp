package com.example.footballapp.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.footballapp.util.BindingInflater

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var viewModel: VM? = null
    abstract val inflater: BindingInflater<VB>
    abstract fun getViewModelClass(): Class<VM>?
    abstract fun onBindViewModel(viewModel: VM)

    private fun providerViewModel(): VM? =
        if (getViewModelClass() != null) ViewModelProvider(this)[getViewModelClass()!!] else null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = this.providerViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel ?: return)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}