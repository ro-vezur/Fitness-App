package com.example.fitnessapp.extensions.modifier

import android.R.attr.name
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import com.example.fitnessapp.clickDebounce

internal interface MultipleClickCutter {
    fun executeEvent(event: () -> Unit)

    companion object
}

internal fun MultipleClickCutter.Companion.get(): MultipleClickCutter = MultipleClickCutterImpl()

private class MultipleClickCutterImpl: MultipleClickCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    var lastEventTImeMs: Long = 0

    override fun executeEvent(event: () -> Unit) {
        if((now - lastEventTImeMs) >= clickDebounce) {
            event()
        }
        lastEventTImeMs = now
    }

}