package com.zotiko.spacelaunchnow.ui.upcominglaunches.list

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zotiko.spacelaunchnow.databinding.FragmentMainBinding
import com.zotiko.spacelaunchnow.di.modules.ViewModelFactory
import com.zotiko.spacelaunchnow.ui.extension.startWithFade
import com.zotiko.spacelaunchnow.ui.upcominglaunches.UpComingLaunchContract
import com.zotiko.spacelaunchnow.ui.upcominglaunches.UpComingLaunchesViewModel
import com.zotiko.spacelaunchnow.ui.upcominglaunches.list.adapter.LaunchEventListAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import javax.inject.Inject

class LaunchEventListFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var viewModel: UpComingLaunchesViewModel

    private lateinit var fragmentBinding: FragmentMainBinding

    private lateinit var dataListAdapterLaunch: LaunchEventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(UpComingLaunchesViewModel::class.java)
        viewModel.fetchLaunchEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        dataListAdapterLaunch = LaunchEventListAdapter {
            val action =
                LaunchEventListFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        setUpRecyclerView()
        return fragmentBinding.root
    }

    private fun setUpRecyclerView() {
        fragmentBinding.launchEventRecyclerView.adapter = dataListAdapterLaunch
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            uiViewModel = viewModel
        }
        (imv_space_background.drawable as AnimationDrawable).startWithFade()
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            Timber.d("Launch Event : $it")
            it?.let { render(it) }
        })
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

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding.launchEventRecyclerView.adapter = null
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

        if (viewState.activityData.isNotEmpty()) {
            dataListAdapterLaunch.submitList(viewState.activityData)
        }
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(fragmentBinding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    interface OnFragmentInteractionListener

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
