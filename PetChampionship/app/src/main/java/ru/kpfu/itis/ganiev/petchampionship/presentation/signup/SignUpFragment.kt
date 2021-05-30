package ru.kpfu.itis.ganiev.petchampionship.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentSignUpBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var userViewModel: SignUpViewModel

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        ApplicationDelegate.screenComponent().create(activity as Router).inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initView()
        initViewModelListeners()
    }

    private fun initView() {
        with(binding) {
            btnSignUp.setOnClickListener {
                userViewModel.signUp(
                    etEmail.text.toString(),
                    etName.text.toString(),
                    etPassword.text.toString()
                )
            }
            btnBack.setOnClickListener {
                userViewModel.backPressed()
            }
        }
    }

    private fun initViewModelListeners() {
        userViewModel.getSignUpErrorLiveData().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}
