package com.diego.matesanz.cleanarchitecturesample.app.presentation

sealed class ScreenState<out T> {

    data object Loading : ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}
