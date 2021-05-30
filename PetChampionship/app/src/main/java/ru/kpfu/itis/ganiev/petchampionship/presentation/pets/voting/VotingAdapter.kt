package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.voting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.ItemVotingPetBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.model.BestPetItem

class VotingAdapter(
    private val itemVote: (petId: String, isPositive: Boolean) -> Unit
) :
    ListAdapter<BestPetItem, PetsHolder>(object : DiffUtil.ItemCallback<BestPetItem>() {
        override fun areItemsTheSame(oldItem: BestPetItem, newItem: BestPetItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BestPetItem, newItem: BestPetItem): Boolean =
            oldItem == newItem

    }) {

    private var lastPetsHolder: PetsHolder? = null

    private val itemClicked: (PetsHolder) -> Unit = {
        lastPetsHolder?.changeButtonsVisibility(false)
        lastPetsHolder = it
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsHolder =
        PetsHolder(
            ItemVotingPetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClicked,
            itemVote
        )


    override fun onBindViewHolder(holder: PetsHolder, position: Int) =
        holder.bind(getItem(position))
}

class PetsHolder(
    private val binding: ItemVotingPetBinding,
    private val itemClicked: (PetsHolder) -> Unit,
    private val itemVote: (petId: String, isPositive: Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BestPetItem) {
        with(binding) {
            changeButtonsVisibility(false)
            root.setOnClickListener {
                itemClicked.invoke(this@PetsHolder)
                changeButtonsVisibility(true)
            }
            btnPlus.setOnClickListener {
                itemVote.invoke(item.id, true)
            }
            btnMinus.setOnClickListener {
                itemVote.invoke(item.id, false)
            }
            item.imageUrl?.let {
                Picasso.get().load(it).into(ivAvatar)
            }
        }
    }

    fun changeButtonsVisibility(isVisible: Boolean) {
        with(binding) {
            btnPlus.isVisible = isVisible
            btnMinus.isVisible = isVisible
        }
    }
}
