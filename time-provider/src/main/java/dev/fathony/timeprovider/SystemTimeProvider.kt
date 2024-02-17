package dev.fathony.timeprovider

import dev.fathony.timeprovider.contract.TimeProvider
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemTimeProvider
@Inject constructor() : TimeProvider {

    override fun getTime(): Long {
        return Calendar.getInstance().timeInMillis
    }
}
