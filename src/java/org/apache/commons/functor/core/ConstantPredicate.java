/* 
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons-sandbox//functor/src/java/org/apache/commons/functor/core/Attic/ConstantPredicate.java,v 1.3 2003/11/24 20:31:20 rwaldhoff Exp $
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived 
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.commons.functor.core;

import java.io.Serializable;

import org.apache.commons.functor.BinaryPredicate;
import org.apache.commons.functor.Predicate;
import org.apache.commons.functor.UnaryPredicate;

/**
 * {@link #test Tests} to a constant value.
 *
 * @version $Revision: 1.3 $ $Date: 2003/11/24 20:31:20 $
 * @author Rodney Waldhoff
 */
public final class ConstantPredicate implements Predicate, UnaryPredicate, BinaryPredicate, Serializable {

    // constructor
    // ------------------------------------------------------------------------
    /**
     * @param value my constant value
     */
    public ConstantPredicate(boolean value) {
        this.value = value;
    }
 
    // predicate interface
    // ------------------------------------------------------------------------

    /**
     * Returns my constant value.
     * @return my constnat value
     */
    public boolean test() {
        return value;
    }

    /**
     * Returns my constant value.
     * @param obj ignored
     * @return my constnat value
     */
    public boolean test(Object obj) {
        return value;
    }

    /**
     * Returns my constant value.
     * @param left ignored
     * @param right ignored
     * @return my constnat value
     */
    public boolean test(Object left, Object right) {
        return value;
    }

    public boolean equals(Object that) {
        if(that instanceof ConstantPredicate) {
            return equals((ConstantPredicate)that);
        } else {
            return false;
        }
    }
    
    /**
     * Returns <code>true</code> iff
     * <i>that</i> is a non-<code>null</code>
     * <code>ConstantPredicate</code> whose constant
     * value is equal to mine.
     * 
     * @param that the predicate to compare to
     * @return <code>true</code> iff <i>that</i> is a 
     *         non-<code>null</code> 
     *         <code>ConstantPredicate</code> 
     *         whose constant value is equal to mine.
     */
    public boolean equals(ConstantPredicate that) {
        return (null != that && this.value == that.value);
    }
    
    public int hashCode() {
        return (value ? 1231 : 1237) ^ ("ConstantPredicate".hashCode());
    }
    
    public String toString() {
        return "ConstantPredicate<" + value + ">";
    }
    
    // static methods
    // ------------------------------------------------------------------------

    /** 
     * Get a <code>ConstantPredicate</code> that always
     * returns <code>true</code>
     * @return a <code>ConstantPredicate</code> that always
     *         returns <code>true</code>
     */
    public static ConstantPredicate trueInstance() {
        return TRUE_PREDICATE;
    }

    /** 
     * Get a <code>ConstantPredicate</code> that always
     * returns <code>false</code>
     * @return a <code>ConstantPredicate</code> that always
     *         returns <code>false</code>
     */
    public static ConstantPredicate falseInstance() {
        return FALSE_PREDICATE;
    }
    
    /** 
     * Get a <code>ConstantPredicate</code> that always
     * returns <i>value</i>
     * @param value the constant value
     * @return a <code>ConstantPredicate</code> that always
     *         returns <i>value</i>
     */
    public static ConstantPredicate instance(boolean value) {
        return value ? TRUE_PREDICATE : FALSE_PREDICATE;
    }
    
    // attributes
    // ------------------------------------------------------------------------
    private boolean value;

    // static attributes
    // ------------------------------------------------------------------------
    private static final ConstantPredicate TRUE_PREDICATE = new ConstantPredicate(true);
    private static final ConstantPredicate FALSE_PREDICATE = new ConstantPredicate(false);
}
