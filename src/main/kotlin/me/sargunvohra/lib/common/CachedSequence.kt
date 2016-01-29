package me.sargunvohra.lib.common

import java.util.*

class CachedSequence<out T> private constructor(
        private val source: Iterator<T>,
        private val cache: ArrayList<T>,
        private val startAt: Int
) : Sequence<T> {

    constructor(source: Sequence<T>) : this(source.iterator(), ArrayList(), 0)

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var pos = startAt

            override fun hasNext() = pos < cache.size || source.hasNext()

            override fun next(): T {
                while (pos >= cache.size)
                    cache.add(source.next())
                return cache[pos++]
            }
        }
    }

    fun drop(n: Int) = CachedSequence(source, cache, startAt + n)
}

fun <T> Sequence<T>.cached() = CachedSequence(this)