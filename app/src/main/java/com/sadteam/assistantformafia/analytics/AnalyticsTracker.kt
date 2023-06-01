package com.sadteam.assistantformafia.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.yandex.metrica.YandexMetrica
import javax.inject.Inject

class AnalyticsTracker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun trackEvent(eventName: String, params: Map<String, Any> = mapOf()) {
        YandexMetrica.reportEvent(eventName, params)
        val bundle = Bundle().apply {
            for ((key, value) in params) {
                this.putString(key, value.toString())
            }
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}