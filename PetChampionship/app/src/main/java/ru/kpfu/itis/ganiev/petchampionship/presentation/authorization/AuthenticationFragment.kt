package ru.kpfu.itis.ganiev.petchampionship.presentation.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentAuthenticationBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class AuthenticationFragment : Fragment() {

    private var _binding: FragmentAuthenticationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.screenComponent().create(activity as Router).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthenticationBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun initView() {
        with(binding) {
            btnSignUp.setOnClickListener {
                router.navigateToSignUpFragment()
            }
            btnLogIn.setOnClickListener {
                router.navigateToSignInFragment()
            }
        }
    }
}
