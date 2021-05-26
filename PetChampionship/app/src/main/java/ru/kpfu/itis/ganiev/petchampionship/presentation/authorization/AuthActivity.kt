package ru.kpfu.itis.ganiev.petchampionship.presentation.authorization

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.R
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.ActivityAuthBinding
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    lateinit var viewModel: AuthorizationViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.screenComponent().inject(this)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        setNavigationController()
        initLiveDataListeners()
    }

    private fun setViewModel(){
        viewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        ).get(AuthorizationViewModel::class.java)
    }

    private fun setNavigationController(){
        navController = findNavController(R.id.hostAuthFragment)
        binding.bnvMenu.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("RISHAT", (destination.id == R.id.authenticationFragment).toString())
            when (destination.id) {
                R.id.signInFragment -> hideBottomNavigation()
                R.id.signUpFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation(){
        binding.bnvMenu.visibility = View.GONE
    }

    private fun showBottomNavigation(){
        binding.bnvMenu.visibility = View.VISIBLE
    }

    private fun initLiveDataListeners() {
        viewModel.isUserAuthenticated().observe(this) {
            Log.d("RISHAT", "IS AUTHENTICATED: $it")
            if (it) {
                onAuthenticated()
            } else {
                navigateToAuthentication()
            }
        }
    }

    private fun navigateToAuthentication() {
        findNavController(R.id.hostAuthFragment).navigate(R.id.authenticationFragment)
    }

    private fun onAuthenticated() {
        navController.navigate(R.id.profileFragment)
    }
}