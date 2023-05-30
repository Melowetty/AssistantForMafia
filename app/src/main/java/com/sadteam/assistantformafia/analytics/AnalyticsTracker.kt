package com.sadteam.assistantformafia.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsTracker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun trackEvent(eventName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, null)
    }
}