package com.fechenko.missumobile

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}