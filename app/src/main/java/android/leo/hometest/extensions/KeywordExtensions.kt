package android.leo.hometest.extensions

private const val MIN_DIFF = 1000

fun String.splitKeyword(): String {
    val newStr = makeCorrect(this)
    return if (newStr.isEmpty() || isOneWord(newStr))
        newStr
    else
        splitWord(StringBuilder(newStr))
}

fun splitWord(sb: StringBuilder): String {
    var p = 0
    val length = sb.length
    var min = MIN_DIFF

    for (i in 0 until length) {
        val ch = sb[i]
        if (ch == ' ') {
            val l2 = length - i - 1
            val diff = Math.abs(l2 - i)
            if (diff <= min) {
                p = i
                if (diff == 0) {
                    break
                }
                min = diff
            }
        }
    }
    sb.setCharAt(p, '\n')
    return sb.toString()
}

fun isOneWord(s: String): Boolean {
    val chars = s.toCharArray()
    for (ch in chars) {
        if (ch == ' ') {
            return false
        }
    }
    return true
}

fun makeCorrect(s: String): String {
    val newStr = s.trim { it <= ' ' }
    return newStr.replace("[\\s]+".toRegex(), " ")
}