/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.it.v2.xml

import org.truelicense.it.core.LicenseConsumerPerformance

/** @author Christian Schlichtherle */
object V2XmlLicenseConsumerPerformance
extends LicenseConsumerPerformance with V2XmlTestContext {
  def main(args: Array[String]) = call ()
}
