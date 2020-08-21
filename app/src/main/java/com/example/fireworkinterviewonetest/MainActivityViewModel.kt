package com.example.fireworkinterviewonetest

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val relatedItemsService: RelatedItemsService
) : ViewModel() {

    private val uiModel = MutableLiveData<UiModel>()

    fun getUiModel() = uiModel as LiveData<UiModel>

    fun search(hashTag: String) {
        viewModelScope.launch {
            if (hashTag.isEmpty()) {
                TODO("Validate response")
                return@launch
            }

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

            var id = 0
            val itemsListForUi = items.map { mapItem ->
                HashTagItem(id++, mapItem.key, mapItem.value)
            }

            val sortedItemsListForUi = itemsListForUi.sortedByDescending { it.relatedness }
            sortedItemsListForUi.forEach { item ->
                Log.d("Item", "${item.name} -> ${item.relatedness}")
            }

            uiModel.postValue(UiModel(sortedItemsListForUi))
        }
    }
}

data class UiModel(val relatedItems: List<HashTagItem>)

data class HashTagItem(val id: Int, val name: String, val relatedness: Int)

