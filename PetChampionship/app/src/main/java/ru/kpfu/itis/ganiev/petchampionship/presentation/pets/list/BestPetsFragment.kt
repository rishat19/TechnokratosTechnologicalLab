package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentBestPetsBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class BestPetsFragment : Fragment() {
    private var _binding: FragmentBestPetsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: PetsViewModel

    lateinit var adapter: PetsAdapter

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
        _binding = FragmentBestPetsBinding.inflate(
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
        initViewListeners()
    }

    private fun createAdapter() {
        adapter = PetsAdapter() {
            viewModel.itemClicked(it)
        }
        binding.rvPets.adapter = adapter
        binding.rvPets.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initViewListeners() {
        with(binding) {
            cbThisWeek.setOnCheckedChangeListener { _, isChecked ->
                viewModel.findThisWeek(isChecked)
            }
            fbAddPet.setOnClickListener {
                viewModel.addPetClicked()
            }
            swipe.setOnRefreshListener {
                viewModel.refreshData()
                swipe.isRefreshing = false
            }
        }
    }

    private fun initSpinner() {
        spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
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

    override fun onStart() {
        super.onStart()
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.isLoading().observe(viewLifecycleOwner) {
            binding.pbIsLoaded.isVisible = it
        }
        viewModel.getPets().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.getNominations().observe(viewLifecycleOwner) {
            spinnerAdapter.clear()
            spinnerAdapter.addAll(it)
        }
    }
}
