package ru.yandex.practicum.sprint10koh11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.yandex.practicum.sprint10koh11.databinding.VChatMessageMineBinding
import ru.yandex.practicum.sprint10koh11.databinding.VChatMessageYouBinding
import java.text.SimpleDateFormat

class ChatMessageYouViewHolder(
    parent: ViewGroup,
    val binding: VChatMessageYouBinding = VChatMessageYouBinding.inflate(
        LayoutInflater.from(parent.context)
    )
) : ViewHolder(
    binding.root
) {

    private val formatter = SimpleDateFormat("HH:mm")

    fun bind(item: ChatItem.ChatMessage) {
        binding.messageText.text = item.text
        binding.messageDate.text = formatter.format(item.dateTime)
    }
}