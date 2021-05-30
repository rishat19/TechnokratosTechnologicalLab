package ru.kpfu.itis.ganiev.petchampionship.presentation.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.ActivityAuthBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list.BestPetsFragmentDirections
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class AuthActivity : AppCompatActivity(), Router {

    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    lateinit var viewModel: AuthorizationViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ApplicationDelegate.screenComponent().create(this).inject(this)
        setNavigationController()
        setViewModel()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        ).get(AuthorizationViewModel::class.java)
    }

    private fun setNavigationController() {
        navController = findNavController(R.id.hostAuthFragment)
        binding.bnvMenu.setupWithNavController(navController)
        binding.bnvMenu.setOnNavigationItemReselectedListener { }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signInFragment -> hideBottomNavigation()
                R.id.signUpFragment -> hideBottomNavigation()
                R.id.authenticationFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        binding.bnvMenu.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.bnvMenu.visibility = View.VISIBLE
    }

    override fun navigateToAuthentication() {
        navController.navigate(R.id.authenticationFragment)
    }

    override fun navigateToProfile() {
        navController.navigate(R.id.profileFragment)
    }

    override fun navigateBestPetsToPetsDetails(petId: String) {
        val action = BestPetsFragmentDirections.actionBestPetsFragmentToPetDetailsFragment(petId)
        navController.navigate(action)
    }

    override fun back() {
        navController.navigateUp()
    }

    override fun navigateToAddPet() {
        val action = BestPetsFragmentDirections.actionBestPetsFragmentToAddPetFragment()
        navController.navigate(action)
    }

    override fun navigateToSignInFragment() {
        navController.navigate(R.id.signInFragment)
    }

    override fun navigateToSignUpFragment() {
        navController.navigate(R.id.signUpFragment)
    }
}
