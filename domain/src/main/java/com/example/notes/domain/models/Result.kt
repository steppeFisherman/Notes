package com.example.notes.domain.models

import androidx.lifecycle.LiveData

sealed class Result {
    data class Success(val notesDomain: LiveData<List<NoteDomain>>) : Result()
    data class Fail(val errorType: ErrorType) : Result()
}
