package dev.fathony.multiscopebinding.message

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurfaceLevelMessageProvider @Inject constructor() : MessageProvider {

    override fun provideMessage(): String {
        return "Surface level message"
    }
}