/*
 * Copyright 2003,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.functor.core.comparator;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.adapter.RightBoundPredicate;

/**
 * A {@link BinaryPredicate BinaryPredicate} that {@link #test tests}
 * <code>true</code> iff the left argument is equal to the
 * right argument under the specified {@link Comparator}.
 * When no (or a <code>null</code> <code>Comparator</code> is specified,
 * a {@link Comparable Comparable} <code>Comparator</code> is used.
 * 
 * @see org.apache.commons.functor.core.IsEqual
 * 
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 * 
 */
public final class IsEquivalent implements BinaryPredicate, Serializable {
    /**
     * Construct an <code>IsEquivalent</code> {@link BinaryPredicate predicate}
     * for {@link Comparable Comparable}s.
     */
    public IsEquivalent() {
        this(null);
    }

    /**
     * Construct an <code>IsEquivalent</code> {@link BinaryPredicate predicate}
     * for the given {@link Comparator Comparator}.
     * 
     * @param comparator the {@link Comparator Comparator}, when <code>null</code>,
     *        a <code>Comparator</code> for {@link Comparable Comparable}s will
     *        be used.
     */
    public IsEquivalent(Comparator comparator) {
        this.comparator = null == comparator ? ComparableComparator.instance() : comparator;
    }
    
    /**
     * Return <code>true</code> iff the <i>left</i> parameter is 
     * equal to the <i>right</i> parameter under my current
     * {@link Comparator Comparator}.
     */
    public boolean test(Object left, Object right) {
        return comparator.compare(left,right) == 0;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object that) {
        if(that instanceof IsEquivalent) {
            return equals((IsEquivalent)that);
        } else {
            return false;
        }
    }

    /**
     * @see #equals(Object)
     */
    public boolean equals(IsEquivalent that) {
        return null != that && 
            null == comparator ? null == that.comparator : comparator.equals(that.comparator);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int hash = "IsEquivalent".hashCode();
        // by construction, comparator is never null
        hash ^= comparator.hashCode();
        return hash;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "IsEquivalent<" + comparator + ">";
    }

    public static final IsEquivalent instance() {
        return COMPARABLE_INSTANCE;
    }

    public static final UnaryPredicate instance(Comparable right) {
        return RightBoundPredicate.bind(instance(),right);
    }
    
    private Comparator comparator = null;
    private static final IsEquivalent COMPARABLE_INSTANCE = new IsEquivalent();
}