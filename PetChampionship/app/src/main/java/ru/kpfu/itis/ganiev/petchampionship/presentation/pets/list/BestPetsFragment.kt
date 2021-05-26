package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentBestPetsBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.app.databinding.FragmentProfileBinding

class BestPetsFragment : Fragment() {
    private var _binding: FragmentBestPetsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBestPetsBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }
}