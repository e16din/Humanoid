package com.e16din.humanoid

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Humanoid

enum class LogType { D, I, W, E }

const val TAG_HUMANOID = "Humanoid"

private const val LOG_PART_SIZE = 4000
private const val DEFAULT_INDENT_SPACES = 4

var defaultLogType = LogType.D
var linkLogType = LogType.D
var enabled = true

fun Any?.logH(tag: String = "${TAG_HUMANOID}H", logType: LogType = defaultLogType) {
    this.toString().log(tag, logType)
    logLink()
}

fun logLink(tag: String = "${TAG_HUMANOID}H") {
    getLinkElement()?.let {
        val link =
            "Go to: ${it.className}.${it.methodName}(${it.fileName}:${it.lineNumber})"
        link.log(tag, linkLogType)
    }
}

private fun getLinkElement(): StackTraceElement? {
    val stackTrace = Thread.currentThread().stackTrace
    val currentFunctionIndex = stackTrace.indexOfFirst {
        it.methodName.contains("logLink") // NOTE: this function name
    }
    val humanoidClassName = Humanoid::class.simpleName.toString()
    stackTrace.forEachIndexed { index, it ->
        if (index > currentFunctionIndex
            && !it.fileName.startsWith(humanoidClassName)
        ) {
            return it
        }
    }
    throw IllegalStateException()
}

private fun String.log(
    tag: String = TAG_HUMANOID,
    type: LogType
): String? {
    if (!enabled) return null // else {

    when (type) {
        LogType.D -> Log.d(tag, this)
        LogType.I -> Log.i(tag, this)
        LogType.W -> Log.w(tag, this)
        LogType.E -> Log.e(tag, this)
    }

    return this
}

// todo: use or remove
fun String.logJson(
    tag: String = TAG_HUMANOID,
    type: LogType = LogType.D,
    indentSpaces: Int = DEFAULT_INDENT_SPACES
) {

    if (!enabled) return // else {

    val message = this

    if (message.isNotEmpty() && message.startsWith("{")) {
        val jsonObject: JSONObject
        try {
            jsonObject = JSONObject(message)
            val logStr = jsonObject.toString(indentSpaces)

            logLong(logStr, tag, type)
        } catch (e: JSONException) {
            e.printStackTrace()

            logLong(message, tag, LogType.E)
        }

    } else if (message.isNotEmpty() && message.startsWith("[")) {
        val jsonArray: JSONArray
        try {
            jsonArray = JSONArray(message)

            "[".log(tag, type)
            (0 until jsonArray.length())
                .map { jsonArray.get(it) as JSONObject }
                .forEach { logLong(it.toString(indentSpaces), tag, type) }
            "]".log(tag, type)

        } catch (e: JSONException) {
            e.printStackTrace()

            logLong(message, tag, LogType.E)
        }

    } else { // finally
        logLong(message, tag, type)
    }
}

fun logLong(message: String, tag: String = TAG_HUMANOID, type: LogType = LogType.D) {
    if (!enabled) return

    if (message.length > LOG_PART_SIZE) {
        val nextPart = message.substring(0, LOG_PART_SIZE)
        nextPart.log(tag, type)

        logLong(tag, message.substring(LOG_PART_SIZE), type)

    } else {
        message.log(tag, type)
    }
}
