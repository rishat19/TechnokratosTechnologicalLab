package ru.kpfu.itis.ganiev.petchampionship.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentSignInBinding

import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var userViewModel: SignInViewModel

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        ApplicationDelegate.screenComponent().inject(this)
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        ).get(SignInViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initView()
        initViewModelListeners()
    }

    private fun initView() {
        with(binding) {
            btnSignIn.setOnClickListener {
                val user = userViewModel.authorize(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
    }

    private fun initViewModelListeners() {
        userViewModel.getSignInErrorLiveData().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}