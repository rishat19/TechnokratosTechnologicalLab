package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.voting

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentVotingBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list.PetsViewModel
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class VotingFragment : Fragment() {

    @Inject
    lateinit var viewModel: PetsViewModel

    private var _binding: FragmentVotingBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: VotingAdapter

    lateinit var spinnerAdapter: ArrayAdapter<Nomination>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.screenComponent().create(activity as Router).inject(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter()
        initSpinner()
    }

    private fun createAdapter() {
        adapter = VotingAdapter() { id, isPositive ->
            viewModel.vote(id, isPositive)
            Toast.makeText(requireContext(), "Вы проголосовали", Toast.LENGTH_SHORT).show()
        }
        binding.rvPets.adapter = adapter
        binding.rvPets.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvPets.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onStart() {
        super.onStart()
        listenViewModel()
        listenLiveData()
    }

    private fun initSpinner() {
        spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerAdapter.getItem(position)?.let {
                    viewModel.findWithNomination(it.id)
                }
            }

        }
    }

    private fun listenViewModel() {
        viewModel.getPets().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.getNominations().observe(viewLifecycleOwner) {
            spinnerAdapter.clear()
            spinnerAdapter.addAll(it)
        }
    }

    private fun listenLiveData() {
        viewModel.getNominations()
    }
}
