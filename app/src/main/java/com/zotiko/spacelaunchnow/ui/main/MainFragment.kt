package com.zotiko.spacelaunchnow.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zotiko.spacelaunchnow.databinding.MainFragmentBinding
import com.zotiko.spacelaunchnow.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private var listener: OnFragmentInteractionListener? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var fragmentBinding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            Timber.d("Launch Event : $it")
            it?.let { render(it) }
        })
        viewModel.fetchLaunchEvents()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun render(viewState: UpComingLaunchContract.ViewState) {

        when (viewState.isLoading) {
            true -> fragmentBinding.pageLoadingIndicator.visibility = View.VISIBLE
            false -> fragmentBinding.pageLoadingIndicator.visibility = View.GONE
        }
        if (viewState.errorState != null) {
            showError(viewState.errorState.message.getText(context!!).toString())
        }
    }

    private fun showError(errorMessage: String) {
        Snackbar
            .make(
                fragmentBinding.root,
                errorMessage,
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

    interface OnFragmentInteractionListener
    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
