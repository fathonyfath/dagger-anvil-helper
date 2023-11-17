package dev.fathony.featurea

import android.util.Log
import dev.fathony.featurea.contract.RandomNumberGenerator
import dev.fathony.timeprovider.contract.TimeProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class DefaultRandomNumberGenerator
@Inject constructor(
    private val timeProvider: TimeProvider
) : RandomNumberGenerator {

    init {
        Log.d("RandomNumberGenerator", "init()")
    }

    override fun provide(): Long {
        return Random(timeProvider.getTime()).nextLong()
    }
}