package com.zotiko.spacelaunchnow.ui.upcominglaunches.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.zotiko.spacelaunchnow.databinding.FragmentDetailBinding
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO

class DetailFragment : Fragment() {

    private lateinit var fragmentBinding: FragmentDetailBinding

    private lateinit var selectedData: LaunchEventDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val safeArgs: DetailFragmentArgs by navArgs()
        selectedData = safeArgs.selectedData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentDetailBinding.inflate(inflater, container, false)
        fragmentBinding.uiData = selectedData
        return fragmentBinding.root
    }
}
