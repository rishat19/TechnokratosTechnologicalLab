package ru.kpfu.itis.ganiev.petchampionship.presentation.addpet

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.ApplicationDelegate
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Nomination
import ru.kpfu.itis.ganiev.petchampionship.domain.model.Pet
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.FragmentAddPetBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.router.Router
import javax.inject.Inject

const val IMAGE_REQUEST = 19219

class AddPetFragment : Fragment() {


    private var _binding: FragmentAddPetBinding? = null
    private val binding get() = _binding!!

    lateinit var spinnerAdapter: ArrayAdapter<Nomination>

    @Inject
    lateinit var viewModel: AddPetViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.screenComponent().create(activity as Router).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPetBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        listenViewModel()
        initViewListeners()
    }

    private fun initViewListeners() {
        with(binding) {
            btnCreate.setOnClickListener {
                if (isInputCorrect()) {
                    viewModel.addPet(
                        Pet(
                            name = etName.text.toString(),
                            nomination = spinner.selectedItem as Nomination,
                            publicationDate = DateTime(),
                            image = (ivAvatar.drawable as BitmapDrawable).bitmap
                        )
                    )
                }
            }
            btnBack.setOnClickListener {
                viewModel.onBackPressed()
            }
            ivAvatar.setOnClickListener {
                setImage()
            }
        }
    }

    private fun setImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(intent, IMAGE_REQUEST)
        } else {
            Toast.makeText(requireContext(), "Не удалось найти галерею", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST) {
            val uri = data?.data
            uri?.let {
                val stream = requireContext().contentResolver.openInputStream(uri)
                val selectedImage = BitmapFactory.decodeStream(stream)
                binding.ivAvatar.setImageBitmap(selectedImage)
            }
        }
    }

    private fun isInputCorrect(): Boolean {
        with(binding) {
            if (etName.text.isNullOrEmpty()) {
                tiName.error =
                    getString(ru.kpfu.itis.ganiev.petchampionship.presentation.common.R.string.enter_name)
                return false
            }
        }
        return true
    }

    private fun listenViewModel() {
        viewModel.getNominations().observe(viewLifecycleOwner) {
            spinnerAdapter.clear()
            spinnerAdapter.addAll(it)
        }
    }

    private fun initSpinner() {
        spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item)
        binding.spinner.adapter = spinnerAdapter
    }


}
