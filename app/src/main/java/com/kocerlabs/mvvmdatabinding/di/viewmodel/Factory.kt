package com.kocerlabs.mvvmdatabinding.di.viewmodel

interface Factory<T> {

    fun create(): T
}