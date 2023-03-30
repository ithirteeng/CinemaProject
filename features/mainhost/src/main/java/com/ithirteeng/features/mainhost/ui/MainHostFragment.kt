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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main_host, container, false)
        binding = FragmentMainHostBinding.bind(layout)

        viewModel.navigateToMainScreen()

        addBackPressedListener {
            viewModel.exit()
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
        return when (item.itemId) {
            R.id.main_section -> {
                viewModel.navigateToMainScreen()
                true
            }
            R.id.compilation_section -> {
                viewModel.navigateToCompilationScreen()
                true
            }
            R.id.collections_section -> {
                viewModel.navigateToCollectionsScreen()
                true
            }
            else -> {
                viewModel.navigateToProfileScreen()
                true
            }
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

