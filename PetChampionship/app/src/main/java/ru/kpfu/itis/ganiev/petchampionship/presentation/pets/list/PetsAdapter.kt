package ru.kpfu.itis.ganiev.petchampionship.presentation.pets.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.kpfu.itis.ganiev.petchampionship.presentation.common.databinding.ItemBestPetBinding
import ru.kpfu.itis.ganiev.petchampionship.presentation.pets.model.BestPetItem

class PetsAdapter(
    private val itemClick: (String) -> Unit
) :
    ListAdapter<BestPetItem, PetsHolder>(object : DiffUtil.ItemCallback<BestPetItem>() {
        override fun areItemsTheSame(oldItem: BestPetItem, newItem: BestPetItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BestPetItem, newItem: BestPetItem): Boolean =
            oldItem == newItem

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsHolder =
        PetsHolder(
            ItemBestPetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick
        )

    override fun onBindViewHolder(holder: PetsHolder, position: Int) =
        holder.bind(getItem(position))
}

class PetsHolder(
    private val binding: ItemBestPetBinding,
    private val itemClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BestPetItem) {
        with(binding) {
            root.setOnClickListener { itemClick.invoke(item.id) }
            tvName.text = item.name
            tvDate.text = item.publicationDate
            tvNomination.text = item.nominationName
            tvPositive.text = item.positiveVotes
            tvNegative.text = item.negativeVotes
            item.imageUrl?.let {
                Picasso.get().load(it).into(ivAvatar)
            }
        }
    }
}
