package com.ithirteeng.features.mainhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.design.LOCAL_ROUTER
import com.ithirteeng.features.main.MainFragment
import com.ithirteeng.features.mainhost.databinding.FragmentMainHostBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainHostFragment : Fragment() {

    private lateinit var binding: FragmentMainHostBinding

    companion object {
        val providerMainHostScreen = FragmentScreen { MainHostFragment() }
    }

    private val router: Router by inject(named(LOCAL_ROUTER))
    private val navigationHolder: NavigatorHolder by inject(named(LOCAL_ROUTER))
    private val navigator by lazy {
        AppNavigator(requireActivity(), R.id.mainHostContainer)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main_host, container, false)
        binding = FragmentMainHostBinding.bind(layout)

        router.newRootScreen(MainFragment.provideMainScreen)
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
}