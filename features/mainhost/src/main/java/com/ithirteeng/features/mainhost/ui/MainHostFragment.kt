package com.ithirteeng.features.mainhost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ithirteeng.design.LOCAL_ROUTER
import com.ithirteeng.features.mainhost.R
import com.ithirteeng.features.mainhost.databinding.FragmentMainHostBinding
import com.ithirteeng.features.mainhost.presentation.MainHostFragmentViewModel
import com.ithirteeng.features.mainhost.utils.SectionType
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainHostFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentMainHostBinding

    private val viewModel: MainHostFragmentViewModel by viewModel()

    companion object {
        val providerMainHostScreen = FragmentScreen { MainHostFragment() }
    }

    private val navigationHolder: NavigatorHolder by inject(named(LOCAL_ROUTER))

    private val navigator by lazy {
        AppNavigator(requireActivity(), R.id.mainHostContainer, childFragmentManager)
    }

    private var itemSelectedByUser = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main_host, container, false)
        binding = FragmentMainHostBinding.bind(layout)

        viewModel.navigateToMainScreen()

        addBackPressedListener {
            viewModel.exit()
            selectItem()
        }
        binding.bottomNavBar.setOnNavigationItemSelectedListener(this)


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (itemSelectedByUser) {
            when (item.itemId) {
                R.id.main_section ->
                    viewModel.navigateToMainScreen()
                R.id.compilation_section -> viewModel.navigateToCompilationScreen()

                R.id.collections_section -> viewModel.navigateToCollectionsScreen()

                else -> viewModel.navigateToProfileScreen()

            }
        }
        itemSelectedByUser = true
        return true
    }

    private fun selectItem() {
        itemSelectedByUser = false
        when (viewModel.getCurrentSectionType()) {
            SectionType.MAIN -> binding.bottomNavBar.selectedItemId = R.id.main_section
            SectionType.COMPILATION -> binding.bottomNavBar.selectedItemId =
                R.id.compilation_section
            SectionType.COLLECTIONS -> binding.bottomNavBar.selectedItemId =
                R.id.collections_section
            else -> binding.bottomNavBar.selectedItemId = R.id.profile_section
        }
    }

    private fun Fragment.addBackPressedListener(
        enabledCallback: Boolean = true,
        action: OnBackPressedCallback.() -> Unit,
    ): OnBackPressedCallback {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(enabledCallback) {
            override fun handleOnBackPressed() {
                action.invoke(this)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return callback
    }
}

