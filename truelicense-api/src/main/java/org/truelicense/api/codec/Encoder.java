/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.api.codec;

/**
 * Encodes a given object graph.
 *
 * @author Christian Schlichtherle
 */
public interface Encoder {

    /**
     * Encodes the given object graph.
     *
     * @param obj the object graph.
     */
    void encode(Object obj) throws Exception;
}
