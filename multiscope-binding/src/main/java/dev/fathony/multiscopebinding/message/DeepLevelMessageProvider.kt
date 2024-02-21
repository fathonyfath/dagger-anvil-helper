package dev.fathony.multiscopebinding.message

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLevelMessageProvider @Inject constructor() : MessageProvider {

    override fun provideMessage(): String {
        return "Deep level message"
    }
}