package android.leo.hometest

import android.leo.hometest.extensions.splitKeyword
import org.junit.Assert
import org.junit.Test

class KeywordSpitTest {

    @Test
    fun isSplitCorrect() {
        Assert.assertEquals("xiaomi",   "xiaomi".splitKeyword())
        Assert.assertEquals("bitis\nhunter",   "bitis hunter".splitKeyword())
        Assert.assertEquals("nguyễn\nnhật ánh",   "nguyễn nhật ánh".splitKeyword())
        Assert.assertEquals("anh chính là\nthanh xuân của em",   "anh chính là thanh xuân của em".splitKeyword())
        Assert.assertEquals("tai nghe\nbluetooth",   "tai nghe bluetooth".splitKeyword())
    }
}