package com.akansha.digitalsurgery.screens.common.model

enum class FavouriteState {
    ADDED,
    REMOVED
}

fun getFavouriteState(isFavourite: Boolean): FavouriteState {
    return if (isFavourite) {
        FavouriteState.ADDED
    } else {
        FavouriteState.REMOVED
    }
}
