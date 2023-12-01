package ru.yandex.practicum.sprint10koh11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.practicum.sprint10koh11.databinding.VChatMessageDividerBinding
import ru.yandex.practicum.sprint10koh11.databinding.VChatMessageYouBinding
import java.text.SimpleDateFormat


class ChatMessagesDividerViewHolder(
    parent: ViewGroup,
    val binding: VChatMessageDividerBinding = VChatMessageDividerBinding.inflate(
        LayoutInflater.from(parent.context)
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    private val formatter = SimpleDateFormat("HH:mm")

    fun bind(item: ChatItem.ChatMessagesDivider) {
        binding.title.text = item.title
    }
}