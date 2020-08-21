package com.example.fireworkinterviewonetest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val relatedItemsService: RelatedItemsService
) : ViewModel() {

    private val uiModel = MutableLiveData<UiModel>()

    fun search(hashTag: String) {
        viewModelScope.launch {
            val relatedItemsResponse = kotlin.runCatching {
                relatedItemsService.getRelatedItemsResponse(hashTag)
            }

            val items = relatedItemsResponse.getOrElse { emptyMap() }

            if (relatedItemsResponse.isFailure) {
                TODO("Emit response error to UI")
                return@launch
            }

            if (items.isEmpty()) {
                TODO("Show a suggestion to the user for a different search?")
            }

            val itemsListForUi = items.map { mapItem ->
                Item(mapItem.key, mapItem.value)
            }

            val sortedItemsListForUi = itemsListForUi.sortedByDescending { it.relatedness }
            sortedItemsListForUi.forEach { item ->
                Log.d("Item", "${item.name} -> ${item.relatedness}")
            }

            uiModel.postValue(UiModel(sortedItemsListForUi))
        }
    }
}

data class UiModel(private val relatedItems: List<Item>)

data class Item(val name: String, val relatedness: Int)

