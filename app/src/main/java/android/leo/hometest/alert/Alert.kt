package android.leo.hometest.alert

interface Alert {

    fun toast(message : String)

    fun showProgress(message: String)

    fun hideProgress()
}