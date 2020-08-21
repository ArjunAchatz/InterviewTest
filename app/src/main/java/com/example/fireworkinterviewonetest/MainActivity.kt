package com.example.fireworkinterviewonetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_button.setOnClickListener {
            viewModel.search(hashtag_search_edittext.text.toString())
        }

        hashtags.adapter = HashtagItemsAdapter()

        viewModel.getUiModel().observe(this, Observer(::renderItems))
    }

    private fun renderItems(uiModel: UiModel) {
        (hashtags.adapter as HashtagItemsAdapter).submitList(uiModel.relatedItems)
    }
}