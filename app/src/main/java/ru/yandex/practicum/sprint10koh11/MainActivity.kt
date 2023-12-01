package ru.yandex.practicum.sprint10koh11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yandex.practicum.sprint10koh11.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val chatMessages = mutableListOf<ChatItem>()
    private val chatAdapter = ChatAdapter(chatMessages)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = "Test 1"

        binding.inputText.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                binding.footerAction.setImageResource(R.drawable.ic_audio)
            } else {
                binding.footerAction.setImageResource(R.drawable.ic_send)
            }
        }

        binding.footerAction.setOnClickListener {
            val messageText: String? = binding.inputText.text?.toString()
            if (!messageText.isNullOrBlank()) {
                val newChatMessage = ChatItem.ChatMessage(
                    text = messageText,
                    dateTime = Date(),
                    isRead = false,
                    isSent = false,
                    isMine = true
                )
                if (chatMessages.isEmpty()) {
                    chatMessages.add(0, ChatItem.ChatMessagesDivider("Today"))
                    chatAdapter.notifyItemInserted(0)
                }
                chatMessages.add(0, newChatMessage)
                chatAdapter.notifyItemInserted(0)
                binding.chatMessages.smoothScrollToPosition(0)
                binding.inputText.setText("")

                binding.root.postDelayed({
                    chatMessages.add(
                        0, ChatItem.ChatMessage(
                            text = "ok, $messageText",
                            dateTime = Date(),
                            isRead = false,
                            isSent = false,
                            isMine = false
                        )
                    )
                    chatAdapter.notifyItemInserted(0)
                    binding.chatMessages.smoothScrollToPosition(0)
                }, 1000)
            }
        }

        binding.chatMessages.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                true
            )
            adapter = chatAdapter
        }
    }
}

sealed interface ChatItem {
    data class ChatMessage(
        val text: String,
        val dateTime: Date,
        val isRead: Boolean,
        val isSent: Boolean,
        val isMine: Boolean,
    ) : ChatItem

    data class ChatMessagesDivider(
        val title: String
    ) : ChatItem
}