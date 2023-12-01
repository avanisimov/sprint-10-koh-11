package ru.yandex.practicum.sprint10koh11

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.IllegalStateException


class ChatAdapter(
    val items: List<ChatItem>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MINE -> ChatMessageMineViewHolder(parent)
            VIEW_TYPE_YOU -> ChatMessageYouViewHolder(parent)
            VIEW_TYPE_DIVIDER -> ChatMessagesDividerViewHolder(parent)
            else -> throw IllegalStateException("There is no ViewHolder for viewType=$viewType")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is ChatItem.ChatMessage -> if (item.isMine) {
                VIEW_TYPE_MINE
            } else {
                VIEW_TYPE_YOU
            }

            is ChatItem.ChatMessagesDivider -> VIEW_TYPE_DIVIDER
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ChatMessageMineViewHolder -> {
                holder.bind(items[position] as ChatItem.ChatMessage)
            }

            is ChatMessageYouViewHolder -> {
                holder.bind(items[position] as ChatItem.ChatMessage)
            }

            is ChatMessagesDividerViewHolder -> {
                holder.bind(items[position] as ChatItem.ChatMessagesDivider)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_MINE = 1
        const val VIEW_TYPE_YOU = 2
        const val VIEW_TYPE_DIVIDER = 3
    }
}