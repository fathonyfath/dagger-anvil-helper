package dev.fathony.di

interface DaggerComponent<T : Any> {

    fun inject(target: T)
}