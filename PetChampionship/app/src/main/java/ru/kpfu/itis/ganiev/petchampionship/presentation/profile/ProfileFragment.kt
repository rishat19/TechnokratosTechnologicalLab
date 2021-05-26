package ru.kpfu.itis.ganiev.petchampionship.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.domain.model.User
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var userViewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.screenComponent().inject(this)
        userViewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        ).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initLiveDataListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnSignOut.setOnClickListener {
                userViewModel.signOut()
            }
            btnUpdate.setOnClickListener {
                userViewModel.updateName(binding.etName.text.toString())
            }
        }
    }

    private fun initLiveDataListeners() {
        userViewModel.getCurrentUser().observe(viewLifecycleOwner) {
            it?.let { user ->
                bindUser(user)
            }

        }
    }

    private fun bindUser(user: User) {
        with(binding) {
            etEmail.setText(user.email)
            etName.setText(user.name)
        }
    }

}