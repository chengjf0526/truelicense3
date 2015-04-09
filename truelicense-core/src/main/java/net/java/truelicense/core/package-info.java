/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

/**
 * Provides life cycle management for license keys.
 * License keys are small binary objects which can be stored in a file, a
 * preferences node, the heap or any custom implementation of the
 * {@link net.java.truelicense.core.io.Store} interface.
 * License keys pass the following life cycle phases:
 * <ol>
 * <li>{@linkplain net.java.truelicense.core.LicenseVendorManager#create Creation},
 * <li>{@linkplain net.java.truelicense.core.LicenseConsumerManager#install installation},
 * <li>{@linkplain net.java.truelicense.core.LicenseConsumerManager#view viewing},
 * <li>{@linkplain net.java.truelicense.core.LicenseConsumerManager#verify verification} and
 * <li>{@linkplain net.java.truelicense.core.LicenseConsumerManager#uninstall uninstallation}
 * </ol>
 * <p>
 * These operations are defined in the
 * {@link net.java.truelicense.core.LicenseVendorManager} and the
 * {@link net.java.truelicense.core.LicenseConsumerManager} interfaces.
 * A Free Trial Period (FTP) is
 * {@linkplain net.java.truelicense.core.LicenseConsumerContext#ftpManager configurable}
 * with a license consumer manager in order to ease the conversion of prospects
 * to customers.
 * License consumer managers can also get
 * {@linkplain net.java.truelicense.core.LicenseConsumerContext#chainedManager chained}
 * in order to unlock application features based on the purchased license keys.
 * <p>
 * A license bean is an instance of the
 * {@link net.java.truelicense.core.License} class.
 * This class follows the Java Bean pattern and defines common properties for
 * the licensing subject, issue date, issuer, holder, consumer type/amount,
 * validity period and custom data.
 * These properties get
 * {@linkplain net.java.truelicense.core.LicenseInitialization#initialize initialized}
 * and
 * {@linkplain net.java.truelicense.core.LicenseValidation#validate validated}
 * whenever a license key gets created.
 * They also get validated whenever a license key gets installed or verified.
 * <p>
 * A license key gets created from a license bean by applying the following
 * function composition:
 * <pre>
 * {@code encrypt(compress(encode(sign(encode(validate(initialize(duplicate(licenseBean))))))))}
 * </pre>
 * <p>
 * When installing or verifying a license key, a duplicate of the original
 * license bean gets derived by applying the following (almost
 * inverse) function composition (note that there is no initialization and
 * hence no duplication required):
 * <pre>
 * {@code validate(decode(authenticate(decode(decompress(decrypt(licenseKey))))))}
 * </pre>
 * <p>
 * The validation step is skipped when a license key is just viewed.
 * Encryption is done via JCA with a configurable password based encryption
 * algorithm.
 * Authentication is done via JCA with a configurable key store type and
 * signature algorithm.
 * Encoding is done with {@link java.beans.XMLEncoder} for simple,
 * schema-less, yet resilient long term archival.
 * <p>
 * TrueLicense 2 retains full compatibility with TrueLicense 1 (V1) format
 * license keys plus defines a new V2 format for enhanced encryption and
 * compression.
 * Applications select the class
 * {@link net.java.truelicense.core.V1LicenseManagementContext} or
 * {@link net.java.truelicense.core.V2XmlLicenseManagementContext} as
 * their starting point for configuring a license vendor or consumer manager.
 * These classes can get subclassed to customize special aspects, such as
 * performing additional license validation steps, providing an authoritative
 * clock et al.
 * However, TrueLicense 2 has been designed so that typical applications don't
 * need to subclass or implement any class or interface of its API.
 * <p>
 * For security, passwords are represented as an instance of the
 * {@link net.java.truelicense.obfuscate.ObfuscatedString} class.
 * Other security critical string (algorithms, paths, etc.) are annotatable
 * with {@literal @}{@link net.java.truelicense.obfuscate.Obfuscate} and
 * processible with the TrueLicense Maven Plugin for simple, reliable
 * obfuscation of constant string values in the byte code for license
 * applications.
 * <p>
 * <strong>Warning:</strong>
 * To protect against reverse engineering and modification of your
 * application, it is strictly necessary to generally process <em>all</em> byte
 * code which is comprised in your deployment unit with a tool for byte code
 * obfuscation.
 * TrueLicense does not provide general byte code obfuscation because there
 * exist good quality third party tools, e.g. ProGuard.
 *
 * @author Christian Schlichtherle
 */
@javax.annotation.ParametersAreNonnullByDefault @javax.annotation.Nonnull
package net.java.truelicense.core;
