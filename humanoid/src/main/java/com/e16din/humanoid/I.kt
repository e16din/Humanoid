package com.e16din.humanoid

import android.view.View

object I {

    private const val DEF_MESSAGE = "I"

    private var humanoidMessage = DEF_MESSAGE

    fun create(message: String = ""): I {
        humanoidMessage += " create"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun that(message: String = ""): I {
        humanoidMessage += " that"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun send(message: String = ""): I {
        humanoidMessage += " send"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }


    fun load(message: String = ""): I {
        humanoidMessage += " load"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun received(message: String = ""): I {
        humanoidMessage += " received"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun message(message: String = ""): I {
        humanoidMessage += " message:"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun print(message: String = ""): I {
        humanoidMessage += " print"
        if (message.isNotEmpty()) humanoidMessage += " $message"
        return this
    }

    fun say(): I {
        humanoidMessage += " say"
        return this
    }

    fun load(): I {
        humanoidMessage += " load"
        return this
    }

    fun send(): I {
        humanoidMessage += " send"
        return this
    }

    fun print(): I {
        humanoidMessage += " print"
        return this
    }

    fun call(): I {
        humanoidMessage += " call"
        return this
    }

    fun invoke(): I {
        humanoidMessage += " invoke"
        return this
    }

    fun click(): I {
        humanoidMessage += " click"
        return this
    }

    fun touch(): I {
        humanoidMessage += " touch"
        return this
    }

    fun select(): I {
        humanoidMessage += " select"
        return this
    }

    fun check(): I {
        humanoidMessage += " check"
        return this
    }

    fun open(): I {
        humanoidMessage += " open"
        return this
    }

    fun close(): I {
        humanoidMessage += " close"
        return this
    }

    fun am(msg: String = ""): I {
        humanoidMessage += " am"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun humanoid(msg: String = ""): I {
        if (humanoidMessage == DEF_MESSAGE) humanoidMessage = ""

        humanoidMessage += " Humanoid"

        if (msg.isNotEmpty()) humanoidMessage += " $msg"

        return this
    }

    fun empty(msg: String = ""): I {
        humanoidMessage = msg
        return this
    }

    fun addString(msg: String = ""): I {
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun and(msg: String = ""): I {
        if (humanoidMessage == DEF_MESSAGE) humanoidMessage = ""

        humanoidMessage += " and"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun to(msg: String = ""): I {
        humanoidMessage += " to"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun on(msg: String = ""): I {
        humanoidMessage += " on"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun the(msg: String = ""): I {
        humanoidMessage += " the"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun a(msg: String = ""): I {
        humanoidMessage += " a"
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        return this
    }

    fun an(msg: String = ""): I {
        if (msg.isNotEmpty()) humanoidMessage += " $msg"
        humanoidMessage += " an"
        return this
    }

    fun button(vButton: View): I {
        view(vButton)
        humanoidMessage += " button"
        return this
    }

    fun view(v: View): I {
        val name = v.resources.getResourceEntryName(v.id)
        humanoidMessage += " $name"
        return this
    }

    fun logH(tag: String) {
        humanoidMessage.logI(tag)

        printLinkToString("logH", tag, 1)
        humanoidMessage = DEF_MESSAGE
    }

    fun logH(): I { // use instead default value to print right link
        val tag = "Humanoid"
        humanoidMessage.logI(tag)

        printLinkToString("logH", tag, 1)
        humanoidMessage = DEF_MESSAGE
        return this
    }

    fun printLinkToString(methodName: String, tag: String, methodIndex: Int = 0) {
        val stackTrace = Thread.currentThread().stackTrace

        val currentIndex = stackTrace.indexOfFirst {
            it.methodName == methodName
        }
        val e = stackTrace[currentIndex + methodIndex]

        val link = "at ${e.className}.${e.methodName}(${e.fileName}:${e.lineNumber})"
        link.logD(tag)
    }

    fun <T> withArray(items: Array<T>): I {
        humanoidMessage += "[\n"
        items.forEach {
            humanoidMessage += "    $it,\n"
        }
        humanoidMessage += "]"
        return this
    }

    fun <T> withArray(items: List<T>): I {
        humanoidMessage += "[\n"
        items.forEach {
            humanoidMessage += "    $it,\n"
        }
        humanoidMessage += "]"
        return this
    }
}

fun Any.and(msg: String = ""): I {
    return I.and(msg)
}
