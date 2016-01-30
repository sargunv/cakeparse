package me.sargunvohra.lib.common

import java.util.*

/**
 * A caching sequence which stores the values from the source sequence as they are generated. The Next time those values
 * are requested, they are returned from the cache instead of requested from the original sequence.
 *
 * @param T the type of the members of this sequence.
 */
class CachedSequence<out T> private constructor(
        private val source: Iterator<T>,
        private val cache: ArrayList<T>,
        private val startAt: Int
) : Sequence<T> {

    /**
     * @constructor construct a new CachedSequence based on the [source] sequence.
     *
     * @param source the source of elements for this sequence.
     */
    constructor(source: Sequence<T>) : this(source.iterator(), ArrayList(), 0)

    /**
     * @return an iterator over the elements of this sequence.
     */
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

    /**
     * @return a new CachedSequence with the first [n] elements dropped.
     *
     * @param n the number of elements to drop.
     */
    fun drop(n: Int) = CachedSequence(source, cache, startAt + n)
}

/**
 * @return a new [CachedSequence] based on this input sequence. Can be used to undo a [constrainOnce]
 */
fun <T> Sequence<T>.cached() = CachedSequence(this)