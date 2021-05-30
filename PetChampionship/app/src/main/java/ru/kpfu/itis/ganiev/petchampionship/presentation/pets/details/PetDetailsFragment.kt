package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.squareup.picasso.Picasso
import org.joda.time.format.DateTimeFormat
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentPetDetailsBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

class PetDetailsFragment : Fragment() {

    private var _binding: FragmentPetDetailsBinding? = null
    private val binding: FragmentPetDetailsBinding get() = _binding!!

    @Inject
    lateinit var viewModel: PetDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.screenComponent().create(activity as Router).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListeners()
        listenViewModel()
    }

    private fun initViewListeners() {
        with(binding) {
            btnBack.setOnClickListener {
                viewModel.back()
            }
        }
    }

    private fun listenViewModel() {
        arguments?.let {
            viewModel.getPet(PetDetailsFragmentArgs.fromBundle(it).petId)
                .observe(viewLifecycleOwner) {
                    bindPet(it)
                }
        }
    }

    private fun bindPet(item: Pet) {
        with(binding) {
            tvName.text = item.name
            tvDate.text = item.publicationDate.toString(DateTimeFormat.shortDate())
            tvNomination.text = item.nomination.name
            tvPositive.text = "${item.positiveVotes}"
            tvNegative.text = "${item.negativeVotes}"
            item.imageUrl?.let {
                Picasso.get().load(it).into(ivAvatar)
            }
        }
    }


}
