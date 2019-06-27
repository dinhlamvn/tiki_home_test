package android.leo.hometest.base

import android.leo.hometest.alert.Alert
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseActivity : AppCompatActivity(), Alert {

    private var mProgress : KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        onInitUI()
    }

    override fun onDestroy() {
        mProgress = null
        super.onDestroy()
    }

    abstract fun getLayoutResource() : Int

    abstract fun getLogTag() : String

    abstract fun onInitUI()

    override fun toast(message: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress(message: String) {
        try {
            mProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(message)
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
            if (!isFinishing) {
                mProgress?.show()
            }
        } catch (e : Exception) {
            Log.d(getLogTag(), "Error when show progress")
        }
    }

    override fun hideProgress() {
        if (isFinishing) return
        mProgress?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            mProgress = null
        }
    }
}