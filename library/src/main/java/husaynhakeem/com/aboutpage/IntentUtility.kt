package husaynhakeem.com.aboutpage

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log


private val TAG = "About Page"
private val EMAIL_URI_PREFIX = "mailto:"

fun sendEmail(context: Context, emailAddress: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse(EMAIL_URI_PREFIX + emailAddress)))
    } catch (e: ActivityNotFoundException) {
        Log.e(TAG, "No available application found to handle email sending")
        e.printStackTrace()
    }
}

fun openWebPage(context: Context, webPageUrl: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webPageUrl)))
    } catch (e: ActivityNotFoundException) {
        Log.e(TAG, "No available application found to handle webpage opening")
        e.printStackTrace()
    }
}

fun openFacebookPage(context: Context, facebookUsername: String) {
    var facebookUri = Uri.parse("http://m.facebook.com/" + facebookUsername)
    try {
        val applicationInfo = context.packageManager.getApplicationInfo("com.facebook.katana", 0)
        if (applicationInfo.enabled)
            facebookUri = Uri.parse("fb://facewebmodal/f?href=" + facebookUsername)
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e(TAG, "Facebook application uninstalled on current device")
        e.printStackTrace()
    }
    context.startActivity(Intent(Intent.ACTION_VIEW, facebookUri))
}

fun openTwitter(context: Context, twitterId: String) {
    var twitterUri = Uri.parse("http://twitter.com/intent/user?screen_name=" + twitterId)
    try {
        val applicationInfo = context.packageManager.getApplicationInfo("com.twitter.android", 0)
        if (applicationInfo.enabled)
            twitterUri = Uri.parse("twitter://user?screen_name=" + twitterId)
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e(TAG, "Twitter application uninstalled on current device")
        e.printStackTrace()
    }
    context.startActivity(Intent(Intent.ACTION_VIEW, twitterUri))
}