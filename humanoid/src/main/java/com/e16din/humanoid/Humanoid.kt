package com.e16din.humanoid

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

enum class LogType { D, I, W, E }

const val TAG_DEBUG = "Humanoid"

private const val LOG_PART_SIZE = 4000

private const val DEFAULT_INDENT_SPACES = 4

private var enabled = true

fun enableHumanoidLogs() {
    enabled = true
}

fun disableHumanoidLogs() {
    enabled = false
}

fun Any?.logD(tag: String = "${TAG_DEBUG}D") = this.toString().log(tag, LogType.D, false)
fun Any?.logI(tag: String = "${TAG_DEBUG}I") = this.toString().log(tag, LogType.I, false)
fun Any?.logW(tag: String = "${TAG_DEBUG}W") = this.toString().log(tag, LogType.W, false)
fun Any?.logE(tag: String = "${TAG_DEBUG}E") = this.toString().log(tag, LogType.E, false)
fun Any?.logH(tag: String = "${TAG_DEBUG}H") = this.toString().log(tag, LogType.I, true)


private fun logLink(tag: String = "${TAG_DEBUG}H") {
    val stackTrace = Thread.currentThread().stackTrace

    "fun ${stackTrace[4].methodName} called".logI(tag)
    printLinkToString("logLink", tag, 2)
}

fun logLink() {
    logLink("${TAG_DEBUG}H")
}

private fun String.log(
    tag: String = TAG_DEBUG,
    type: LogType = LogType.D,
    withLink: Boolean = true,
): String? {
    if (!enabled) return null // else {

    when (type) {
        LogType.D -> Log.d(tag, this)
        LogType.I -> Log.i(tag, this)
        LogType.W -> Log.w(tag, this)
        LogType.E -> Log.e(tag, this)
    }

    if (withLink) {
        logLink()
    }

    return this
}

private fun printLinkToString(methodName: String, tag: String, methodIndex: Int = 0) {
    val stackTrace = Thread.currentThread().stackTrace

    val currentIndex = stackTrace.indexOfFirst {
        it.methodName == methodName
    }
    val e = stackTrace[currentIndex + methodIndex]

    val link = "at ${e.className}.${e.methodName}(${e.fileName}:${e.lineNumber})"
    link.logD(tag)
}

// todo: use or remove
fun String.logJson(
    tag: String = TAG_DEBUG,
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

fun logLong(message: String, tag: String = TAG_DEBUG, type: LogType = LogType.D) {
    if (!enabled) return

    if (message.length > LOG_PART_SIZE) {
        val nextPart = message.substring(0, LOG_PART_SIZE)
        nextPart.log(tag, type)

        logLong(tag, message.substring(LOG_PART_SIZE), type)

    } else {
        message.log(tag, type)
    }
}
