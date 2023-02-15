package com.aguadaserra.app.util.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

/**
 * Aplica as alteracoes dentro do value do liveData informado e em seguida dispara as alterações.
 */
inline fun <X : MutableLiveData<Y>, Y> X.setValueAndNotify(block: Y.() -> Unit) {
    value?.block()
    postValue(value)
}

/**
 * Chama a funcao informada sempre que houver alteracoes no valor do liveData.
 * Retorna o liveData original, ao contrario da Transformations.map que transforma o valor.
 */
fun <X> LiveData<X>.onValueChange(block: (X) -> Unit): LiveData<X> {
    val result = MediatorLiveData<X>()
    result.addSource(this) { block(it); result.value = it; }
    return result
}

fun <T> MutableLiveData<T>.postIfDifferent(value: T) {
    if (this.value != value) {
        this.postValue(value)
    }
}

fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever {
        observer.invoke(it)
        removeObserver(observer)
    }
}