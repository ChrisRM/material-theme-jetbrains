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

package com.mallowigi.idea.license;

import org.jetbrains.annotations.NonNls;

/**
 * Data holding class for license info
 */
@SuppressWarnings({"StaticMethodOnlyUsedInOneClass",
  "FieldCanBeLocal",
  "unused"})
final class LicenseDetails {
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

  private String id;
  private String name;
  private String paidUpTo;
  private String machineId;
  private MTLicenseChecker.LicenseType licenseType = MTLicenseChecker.LicenseType.FREE;
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

  void setLicenseType(final MTLicenseChecker.LicenseType licenseType) {
    this.licenseType = licenseType;
  }

  MTLicenseChecker.LicenseType getLicenseType() {
    return licenseType;
  }
}
