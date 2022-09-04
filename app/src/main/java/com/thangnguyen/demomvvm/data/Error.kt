package com.thangnguyen.demomvvm.data

sealed class Error : Exception() {
    object NoValue : Error()
}