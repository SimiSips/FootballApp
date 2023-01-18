package com.simphiwe.footballapp.common

sealed class UIState {
    class Success<T>(val result: T) : UIState()
    class Failure(val errorText: String) : UIState()
    object Loading : UIState()
    object Empty : UIState()
}
