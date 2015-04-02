/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package net.java.truelicense.core

import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.Matchers._

/**
 * @author Christian Schlichtherle
 */
@RunWith(classOf[JUnitRunner])
class LicenseValidationExceptionTest extends WordSpec {

  "A LicenseValidationException" when {
    "created with a localized message" should {
      val message = "test"
      val ex = new LicenseValidationException(message)

      "return the localized message upon a call to getLocalizedMessage()" in {
        ex.getLocalizedMessage should be (message)
      }

      "return the localized message upon a call to getMessage()" in {
        ex.getMessage should be (message)
      }

      "be serializable" in {
        val ex2 = Duplicate viaSerialization ex
        ex2.getLocalizedMessage should be (message)
        ex2.getMessage should be (message)
      }
    }
  }
}