package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.common.cached
import org.testng.annotations.Test
import kotlin.test.assertEquals

class CachedSequenceTest {

    @Test
    fun iterate() = assertEquals((1..10).asSequence().toList(), (1..10).asSequence().constrainOnce().cached().toList())

    @Test
    fun drop() = assertEquals((6..10).asSequence().toList(), (1..10).asSequence().constrainOnce().cached().drop(5).toList())

    @Test
    fun dropMany() = assertEquals((7..10).asSequence().toList(), (1..10).asSequence().constrainOnce().cached().drop(1).drop(2).drop(3).toList())
}