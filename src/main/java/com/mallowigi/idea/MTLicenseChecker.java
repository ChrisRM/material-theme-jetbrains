/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.LicensingFacade;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.cert.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SuppressWarnings({
  "UseOfObsoleteDateTimeApi",
  "HardcodedLineSeparator",
  "StringConcatenation",
  "OverlyBroadCatchBlock",
  "OverlyBroadThrowsClause",
  "ProhibitedExceptionThrown",
  "ProhibitedExceptionDeclared"})
public final class MTLicenseChecker {

  private static final String PRODUCT_CODE = "PMATERIALUI";
  private static final String KEY_PREFIX = "key:";
  private static final String STAMP_PREFIX = "stamp:";
  private static final String EVAL_PREFIX = "eval:";
  private static final long SECOND = 1000;
  private static final long MINUTE = 60 * SECOND;
  private static final long HOUR = 60 * MINUTE;
  private static final long TIMESTAMP_VALIDITY_PERIOD_MS = HOUR;

  // region CERTIFICATE
  private static final String[] ROOT_CERTIFICATES = {
    "-----BEGIN CERTIFICATE-----\n" +
      "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\n" +
      "-----END CERTIFICATE-----\n",
    "-----BEGIN CERTIFICATE-----\n" +
      "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\n" +
      "7tlDFnvaRnQukqSG+VqNWg==\n" +
      "-----END CERTIFICATE-----"
  };
  // endregion

  /**
   * License Details
   */
  private static final LicenseDetails licenseDetails = new LicenseDetails();

  private MTLicenseChecker() {
    extractLicenseInformation();
  }

  public static MTLicenseChecker getInstance() {
    return ServiceManager.getService(MTLicenseChecker.class);
  }

  /**
   * Extract License Information from the server
   */
  public static void extractLicenseInformation() {
    final LicensingFacade facade = LicensingFacade.getInstance();
    if (facade == null) {
      return;
    }
    final String cstamp = facade.getConfirmationStamp(PRODUCT_CODE);
    if (cstamp == null) {
      return;
    }
    if (cstamp.startsWith(KEY_PREFIX)) {
      extractFromKey(cstamp.substring(KEY_PREFIX.length()));
    }
    if (cstamp.startsWith(STAMP_PREFIX)) {
      extractFromStamp(cstamp.substring(STAMP_PREFIX.length()));
    }
    if (cstamp.startsWith(EVAL_PREFIX)) {
      extractFromEval(cstamp.substring(EVAL_PREFIX.length()));
    }
  }

  /**
   * Extract license information from key (the license is obtained via JetBrainsAccount or entered as an activation code)
   */
  private static void extractFromKey(final String key) {
    licenseDetails.setLicenseType(LicenseType.LICENSED);

    final String[] licenseParts = key.split("-");
    if (licenseParts.length != 4) {
      licenseDetails.invalidate();
      return; // invalid format
    }

    final String licensePartBase64 = licenseParts[1];
    final String signatureBase64 = licenseParts[2];
    final String certBase64 = licenseParts[3];

    try {
      final byte[] licenseBytes = verifySignature(licensePartBase64, signatureBase64, certBase64);
      if (licenseBytes == null) {
        licenseDetails.invalidate();
        return;
      }

      // Extract license info from the json
      extractInfo(licenseBytes);
    } catch (final Throwable e) {
      // do not print debug info
      licenseDetails.invalidate();
    }
  }

  /**
   * Extract license information from stamp (licensed via ticket obtained from JetBrains Floating License Server)
   */
  private static void extractFromStamp(final String serverStamp) {
    licenseDetails.setLicenseType(LicenseType.FLOATING);
    try {
      final String[] parts = serverStamp.split(":");
      final Base64.Decoder base64 = Base64.getMimeDecoder();

      final String machineId = verifyStampSignature(parts, base64);
      if (machineId != null) {
        licenseDetails.setMachineId(machineId);
      }
    } catch (final Throwable ignored) {
      // consider serverStamp invalid
      licenseDetails.invalidate();
    }
  }

  /**
   * Extract evaluation information
   */
  private static void extractFromEval(final String expirationTime) {
    licenseDetails.setLicenseType(LicenseType.EVALUATION);

    try {
      final Date now = new Date();
      final Date expiration = new Date(Long.parseLong(expirationTime));
      licenseDetails.setPaidUpTo(expirationTime);
    } catch (final NumberFormatException e) {
      licenseDetails.invalidate();
    }
  }

  /**
   * Checks if the product is licensed independently of the license info
   */
  @SuppressWarnings("SimplifiableIfStatement")
  public static boolean isLicensed() {
    final LicensingFacade facade = LicensingFacade.getInstance();
    if (facade == null) {
      return false;
    }
    final String cstamp = facade.getConfirmationStamp(PRODUCT_CODE);
    if (cstamp == null) {
      return false;
    }
    if (cstamp.startsWith(KEY_PREFIX)) {
      // the license is obtained via JetBrainsAccount or entered as an activation code
      return isKeyValid(cstamp.substring(KEY_PREFIX.length()));
    }
    if (cstamp.startsWith(STAMP_PREFIX)) {
      // licensed via ticket obtained from JetBrains Floating License Server
      return isLicenseServerStampValid(cstamp.substring(STAMP_PREFIX.length()));
    }
    if (cstamp.startsWith(EVAL_PREFIX)) {
      return isEvaluationValid(cstamp.substring(EVAL_PREFIX.length()));
    }
    return false;
  }

  /**
   * Triggers the Register Dialog
   */
  public static void requestLicense(final String message) {
    // ensure the dialog is appeared from UI thread and in a non-modal context
    ApplicationManager.getApplication().invokeLater(() -> showRegisterDialog(message), ModalityState.NON_MODAL);
  }

  /**
   * Display License information
   */
  @SuppressWarnings("FeatureEnvy")
  public static String getLicensedInfo() {
    final LicensingFacade facade = LicensingFacade.getInstance();
    if (facade == null) {
      return "";
    }

    final String licensedToMessage = licenseDetails.getName();
    final Date licenseExpirationDate = facade.getExpirationDate(PRODUCT_CODE);
    final Date now = new Date();

    if (licenseDetails.getLicenseType() == LicenseType.LICENSED || licenseDetails.getLicenseType() == LicenseType.FLOATING) {
      assert licensedToMessage != null;
      assert licenseExpirationDate != null;
      return MaterialThemeBundle.message("MTHomeForm.licensedLabel.licensedText", licensedToMessage, licenseExpirationDate);
    } else if (licenseDetails.getLicenseType() == LicenseType.EVALUATION) {
      assert licenseExpirationDate != null;
      final long days = ChronoUnit.DAYS.between(now.toInstant(), licenseExpirationDate.toInstant());
      return MaterialThemeBundle.message("MTHomeForm.licensedLabel.evaluation", days);
    } else {
      return MaterialThemeBundle.message("MTHomeForm.licensedLabel.text");
    }
  }

  /**
   * Show the register dialog
   */
  private static void showRegisterDialog(final String message) {
    final ActionManager actionManager = ActionManager.getInstance();
    // first, assume we are running inside the opensource version
    AnAction registerAction = actionManager.getAction("RegisterPlugins");
    if (registerAction == null) {
      // assume running inside commercial IDE distribution
      registerAction = actionManager.getAction("Register");
    }
    if (registerAction != null) {
      registerAction.actionPerformed(AnActionEvent.createFromDataContext("", new Presentation(), asDataContext(message)));
      final int answer = Messages.showYesNoDialog(MaterialThemeBundle.message("restartIde"), MaterialThemeBundle.message("restartNow"),
        Messages.getQuestionIcon());
      if (answer == Messages.YES) {
        MTUiUtils.restartIde();
      }
    }
  }

  /**
   * Parse the json containing the license info and save in the license Details
   */
  private static void extractInfo(final byte... licenseBytes) {
    final String licenseString = new String(licenseBytes, StandardCharsets.UTF_8);
    final LinkedTreeMap json = new Gson().fromJson(licenseString, LinkedTreeMap.class);
    if (json == null) {
      return;
    }

    licenseDetails.setId((String) json.get(LicenseDetails.LICENSE_ID));
    licenseDetails.setName((String) json.get(LicenseDetails.LICENSEE_NAME));

    final Object productsJson = json.get(LicenseDetails.PRODUCTS);
    if (productsJson instanceof ArrayList) {
      final Iterable products = (Iterable) productsJson;
      for (final Object p : products) {
        if (p instanceof LinkedTreeMap) {
          final LinkedTreeMap product = (LinkedTreeMap) p;
          if (product.get(LicenseDetails.CODE).equals(PRODUCT_CODE)) {
            licenseDetails.setPaidUpTo((String) product.get(LicenseDetails.PAID_UP_TO));
          }
        }
      }
    }
  }

  private static boolean isKeyValid(final String key) {
    // Always ask for new information, we cant rely on the static field
    final String[] licenseParts = key.split("-");
    if (licenseParts.length != 4) {
      return false; // invalid format
    }

    @NonNls final String licenseId = licenseParts[0];
    final String licensePartBase64 = licenseParts[1];
    final String signatureBase64 = licenseParts[2];
    final String certBase64 = licenseParts[3];

    try {
      final byte[] licenseBytes = verifySignature(licensePartBase64, signatureBase64, certBase64);
      if (licenseBytes == null) {
        return false;
      }
      // Optional additional check: the licenseId corresponds to the licenseId encoded in the signed license data
      // The following is a 'least-effort' code. It would be more accurate to parse json and then find there the value of the attribute
      // "licenseId"
      final String licenseData = new String(licenseBytes, StandardCharsets.UTF_8);
      return licenseData.contains("\"licenseId\":\"" + licenseId + "\"");
    } catch (final Throwable e) {
      e.printStackTrace(); // For debug purposes only. Normally you will not want to print exception's trace to console
    }
    return false;
  }

  private static boolean isLicenseServerStampValid(final String serverStamp) {
    // Always ask for new information, we cant rely on the static field
    try {
      final String[] parts = serverStamp.split(":");
      final Base64.Decoder base64 = Base64.getMimeDecoder();

      @NonNls final String expectedMachineId = parts[0];
      final long timeStamp = Long.parseLong(parts[1]);
      final String machineId = parts[2];
      final String signatureType = parts[3];
      final byte[] signatureBytes = base64.decode(parts[4].getBytes(StandardCharsets.UTF_8));
      final byte[] certBytes = base64.decode(parts[5].getBytes(StandardCharsets.UTF_8));
      final Collection<byte[]> intermediate = new ArrayList<>(10);
      for (int idx = 6; idx < parts.length; idx++) {
        intermediate.add(base64.decode(parts[idx].getBytes(StandardCharsets.UTF_8)));
      }

      final Signature sig = Signature.getInstance(signatureType);

      // the last parameter of 'createCertificate()' set to 'true' causes the certificate to be checked for
      // expiration. Expired certificates from a license server cannot be trusted
      sig.initVerify(createCertificate(certBytes, intermediate, true));

      sig.update((timeStamp + ":" + machineId).getBytes(StandardCharsets.UTF_8));
      if (sig.verify(signatureBytes)) {
        // machineId must match the machineId from the server reply and
        // server reply should be relatively 'fresh'
        return expectedMachineId.equals(machineId) && Math.abs(System.currentTimeMillis() - timeStamp) < TIMESTAMP_VALIDITY_PERIOD_MS;
      }
    } catch (final Throwable ignored) {
      // consider serverStamp invalid
    }
    return false;
  }

  private static boolean isEvaluationValid(final String expirationTime) {
    // Always ask for new information, we cant rely on the static field
    try {
      final Date now = new Date();
      final Date expiration = new Date(Long.parseLong(expirationTime));
      return now.before(expiration);
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  /**
   * This creates a DataContext providing additional information for the license UI
   * The "Register*" actions show the registration dialog and expect to find this additional data in the DataContext passed to the action
   * - productCode: the product corresponding to the passed productCode will be pre-selected in the opened dialog
   * - message: optional message explaining the reason why the dialog has been shown
   */
  @NotNull
  private static DataContext asDataContext(@Nullable final String message) {
    return dataId -> {
      switch (dataId) {
        // the same code as registered in plugin.xml, 'product-descriptor' tag
        case "register.product-descriptor.code":
          return PRODUCT_CODE;

        // optional message to be shown in the registration dialog that appears
        case "register.message":
          return message;

        default:
          return null;
      }
    };
  }

  /**
   * Verify the license signature
   *
   * @param licensePartBase64 License
   * @param signatureBase64   Signature
   * @param certBase64        Certificate
   * @return the license in bytes
   * @throws Exception if the decoding fails
   */
  private static byte @Nullable [] verifySignature(final String licensePartBase64, final String signatureBase64, final String certBase64)
    throws Exception {
    final Signature sig = Signature.getInstance("SHA1withRSA");
    // the last parameter of 'createCertificate()' set to 'false' switches off certificate expiration checks.
    // This might be the case if the key is at the same time a perpetual fallback license for older IDE versions.
    // Here it is only important that the key was signed with an authentic JetBrains certificate.
    sig.initVerify(createCertificate(
      Base64.getMimeDecoder().decode(certBase64.getBytes(StandardCharsets.UTF_8)), Collections.emptySet(), false
    ));
    final byte[] licenseBytes = Base64.getMimeDecoder().decode(licensePartBase64.getBytes(StandardCharsets.UTF_8));
    sig.update(licenseBytes);
    if (!sig.verify(Base64.getMimeDecoder().decode(signatureBase64.getBytes(StandardCharsets.UTF_8)))) {
      return null;
    }
    return licenseBytes;
  }

  /**
   * Verify the stamp signature
   */
  private static @Nullable String verifyStampSignature(final String[] parts, final Base64.Decoder base64) throws Exception {
    final long timeStamp = Long.parseLong(parts[1]);
    final String machineId = parts[2];
    final String signatureType = parts[3];
    final byte[] signatureBytes = base64.decode(parts[4].getBytes(StandardCharsets.UTF_8));
    final byte[] certBytes = base64.decode(parts[5].getBytes(StandardCharsets.UTF_8));
    final Collection<byte[]> intermediate = new ArrayList<>(10);
    for (int idx = 6; idx < parts.length; idx++) {
      intermediate.add(base64.decode(parts[idx].getBytes(StandardCharsets.UTF_8)));
    }

    final Signature sig = Signature.getInstance(signatureType);

    // the last parameter of 'createCertificate()' set to 'true' causes the certificate to be checked for
    // expiration. Expired certificates from a license server cannot be trusted
    sig.initVerify(createCertificate(certBytes, intermediate, true));

    sig.update((timeStamp + ":" + machineId).getBytes(StandardCharsets.UTF_8));
    if (!sig.verify(signatureBytes)) {
      return null;
    }
    return machineId;
  }

  /**
   * Create X509 Certificate to use the licensing server
   */
  @SuppressWarnings({"ObjectAllocationInLoop",
    "TypeMayBeWeakened"})
  @NotNull
  private static X509Certificate createCertificate(final byte[] certBytes,
                                                   final Collection<byte[]> intermediateCertsBytes,
                                                   final boolean checkValidityAtCurrentDate) throws Exception {
    final CertificateFactory x509factory = CertificateFactory.getInstance("X.509");
    final X509Certificate cert = (X509Certificate) x509factory.generateCertificate(new ByteArrayInputStream(certBytes));

    final Collection<Certificate> allCerts = new HashSet<>(10);
    allCerts.add(cert);
    for (final byte[] bytes : intermediateCertsBytes) {
      allCerts.add(x509factory.generateCertificate(new ByteArrayInputStream(bytes)));
    }

    try {
      // Create the selector that specifies the starting certificate
      final X509CertSelector selector = new X509CertSelector();
      selector.setCertificate(cert);
      // Configure the PKIX certificate builder algorithm parameters
      final Set<TrustAnchor> trustAchors = new HashSet<>(10);
      for (final String rc : ROOT_CERTIFICATES) {
        trustAchors.add(new TrustAnchor(
          (X509Certificate) x509factory.generateCertificate(new ByteArrayInputStream(rc.getBytes(StandardCharsets.UTF_8))), null
        ));
      }

      final PKIXBuilderParameters pkixParams = new PKIXBuilderParameters(trustAchors, selector);
      pkixParams.setRevocationEnabled(false);
      if (!checkValidityAtCurrentDate) {
        // deliberately check validity on the start date of cert validity period, so that we do not depend on
        // the actual moment when the check is performed
        pkixParams.setDate(cert.getNotBefore());
      }
      pkixParams.addCertStore(
        CertStore.getInstance("Collection", new CollectionCertStoreParameters(allCerts))
      );
      // Build and verify the certification chain
      final CertPath path = CertPathBuilder.getInstance("PKIX").build(pkixParams).getCertPath();
      if (path != null) {
        CertPathValidator.getInstance("PKIX").validate(path, pkixParams);
        return cert;
      }
    } catch (final Exception e) {
      // debug the reason here
    }
    throw new Exception("Certificate used to sign the license is not signed by JetBrains root certificate");
  }

  /**
   * Data holding class for license info
   */
  @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
  private static final class LicenseDetails {
    @NonNls
    static final String LICENSE_ID = "licenseId";
    @NonNls
    static final String LICENSEE_NAME = "licenseeName";
    @NonNls
    static final String PRODUCTS = "products";
    @NonNls
    static final String CODE = "code";
    @NonNls
    static final String PAID_UP_TO = "paidUpTo";

    private String id = null;
    private String name = null;
    private String paidUpTo = null;
    private String machineId = null;
    private LicenseType licenseType = LicenseType.FREE;
    private boolean isValid = true;

    LicenseDetails() {
    }

    void setId(final String id) {
      this.id = id;
    }

    String getName() {
      return name;
    }

    void setName(final String name) {
      this.name = name;
    }

    void setPaidUpTo(final String paidUpTo) {
      this.paidUpTo = paidUpTo;
    }

    void invalidate() {
      isValid = false;
    }

    void setMachineId(final String machineId) {
      this.machineId = machineId;
    }

    void setLicenseType(final LicenseType licenseType) {
      this.licenseType = licenseType;
    }

    LicenseType getLicenseType() {
      return licenseType;
    }
  }

  /**
   * Types of license
   */
  enum LicenseType {
    LICENSED,
    FLOATING,
    EVALUATION,
    FREE
  }
}
