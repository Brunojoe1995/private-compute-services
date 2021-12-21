/*
 * Copyright 2021 Google LLC
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

package com.google.android.libraries.pcc.policies.federatedcompute

/** Safecomms FederatedCompute policy. */
val SafecommsPolicy_FederatedCompute =
  flavoredPolicies(
    name = "SafecommsPolicy_FederatedCompute",
    policyType = MonitorOrImproveUserExperienceWithFederatedCompute,
  ) {
    description =
      """
      To improve phishing detection for messaging apps.

      ALLOWED EGRESSES: FederatedCompute.
      ALLOWED USAGES: Federated analytics, federated learning.
    """.trimIndent()

    flavors(Flavor.ASI_PROD) { minRoundSize(minRoundSize = 1000, minSecAggRoundSize = 0) }
    consentRequiredForCollectionOrStorage(Consent.UsageAndDiagnosticsCheckbox)
    presubmitReviewRequired(OwnersApprovalOnly)
    checkpointMaxTtlDays(720)

    target(SAFECOMMS_ENTITY_GENERATED_DTD, maxAge = Duration.ofDays(14)) {
      retention(StorageMedium.RAM, encryptionRequired = false)
      retention(StorageMedium.DISK, encryptionRequired = false)

      "timestampMillis" {
        conditionalUsage("truncatedToDays", UsageType.ANY)
        rawUsage(UsageType.JOIN)
      }
      "isContact" { rawUsage(UsageType.ANY) }
      "isFirstMessageFromSender" { rawUsage(UsageType.ANY) }
      "hasUrl" { rawUsage(UsageType.ANY) }
      "hasEmail" { rawUsage(UsageType.ANY) }
      "hasPhoneNumber" { rawUsage(UsageType.ANY) }
      "hasCurrencySymbol" { rawUsage(UsageType.ANY) }
      "packageName" {
        conditionalUsage("top2000PackageNamesWith2000Wau", UsageType.ANY)
        rawUsage(UsageType.JOIN)
      }
      "messageLength" { rawUsage(UsageType.ANY) }
      "featureInteraction" { rawUsage(UsageType.ANY) }
      "userInteraction" { rawUsage(UsageType.ANY) }
      "modelVersion" { rawUsage(UsageType.ANY) }
    }

    target(SAFECOMMS_CONVERSATION_ENTITY_GENERATED_DTD, maxAge = Duration.ofDays(14)) {
      retention(StorageMedium.RAM, encryptionRequired = false)
      retention(StorageMedium.DISK, encryptionRequired = false)

      "id" { rawUsage(UsageType.JOIN) }
      "timestampMillis" {
        conditionalUsage("truncatedToDays", UsageType.ANY)
        rawUsage(UsageType.JOIN)
      }
      "packageName" {
        conditionalUsage("top2000PackageNamesWith2000Wau", UsageType.ANY)
        rawUsage(UsageType.JOIN)
      }
      "riskType" { rawUsage(UsageType.ANY) }
      "verdict" { rawUsage(UsageType.ANY) }
      "lastMessageSource" { rawUsage(UsageType.ANY) }
      "predictedValue" { rawUsage(UsageType.ANY) }
      "modelVersion" { rawUsage(UsageType.ANY) }
    }

    target(SAFECOMMS_UI_EVENT_GENERATED_DTD, maxAge = Duration.ofDays(14)) {
      retention(StorageMedium.RAM, encryptionRequired = false)
      retention(StorageMedium.DISK, encryptionRequired = false)

      "timestampInMillis" {
        conditionalUsage("truncatedToDays", UsageType.ANY)
        rawUsage(UsageType.JOIN)
      }
      "conversationEntityId" { rawUsage(UsageType.ANY) }
      "uiScreen" { rawUsage(UsageType.ANY) }
      "uiEvent" { rawUsage(UsageType.ANY) }
    }
  }