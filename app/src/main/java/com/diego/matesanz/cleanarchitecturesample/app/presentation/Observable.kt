package com.diego.matesanz.cleanarchitecturesample.app.presentation

class Observable<T> {

    private var observers = emptyList<(T) -> Unit>()

    fun addObserver(observer: (T) -> Unit) {
        observers += observer
    }

    fun clearObservers() {
        observers = emptyList()
    }

    fun callObservers(newValue: T) {
        for (observer in observers) {
            observer(newValue)
        }
    }
}
