package ru.kpfu.itis.ganiev.petchampionship.presentation.voting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gargabesorter.utils.ViewModelProviderFactory
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentProfileBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentVotingBinding

class VotingFragment : Fragment() {

    lateinit var viewModelProvider: ViewModelProviderFactory

    private var _binding: FragmentVotingBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.screenComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVotingBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }
}