

import android.app.Activity
import android.util.Patterns
import android.view.View
import android.view.View.*
import android.view.animation.CycleInterpolator
import android.webkit.ValueCallback
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.*
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.example.loginwithkoin.R

import com.tapadoo.alerter.Alerter
import com.tapadoo.alerter.OnHideAlertListener

import java.util.*
import java.text.SimpleDateFormat
import java.util.regex.Pattern


fun Date.toMMddYYYY(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    return dateFormat.format(this)
}

fun String.fromMMddYYY(): Date {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

    try {

        return dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return Date()
}

/*
return true for not blank
 */
fun EditText.blankValidation(message: String? = null): Boolean {
    return if (text.toString().isBlank()) {
        if (message.isNullOrBlank())
            error = "error"
        else
            error = message
        false
    } else {
        error = null
        true
    }
}


/*
return true for not blank
 */
fun EditText.minValidation(minChars: Int, minError: String? = null, message: String? = null): Boolean {
    return if (text.toString().isBlank()) {
        error = if (message.isNullOrBlank())
           "Error"
        else
            message
        false
    } else if (text.toString().length < minChars) {
        error = if (minError == null) {
            "Error"
        } else {
            minError
        }
        return false
    } else {
        error = null
        true
    }
}

/*
return true for not blank
 */
fun EditText.emailValidation(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS

    return if (text.toString().isBlank()) {
        error = "Error"
        false
    } else if (!pattern.matcher(text.toString()).matches()) {
        error = "Error"
        false
    } else {
        error = null
        true
    }
}


/*
return true for not blank
 */
fun EditText.phoneValidation(): Boolean {

    return if (text.toString().isBlank()) {
        error = "Error"
        false
    } else if (text.toString().length != 8) {
        error = "Error"
        false
    } else {
        error = null
        true
    }
}

fun EditText.passwordValidation(): Boolean {
    val pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")

    if (text.toString().isBlank()) {
        error = "Error"
        return false
    }
    return if (!pattern.matcher(text.toString()).matches()) {
        error = "Error"
        false
    } else {
        error = null
        true
    }


}

fun AppCompatActivity.confirmDialog(
    title: String,
    message: String,
    positive: String = "yes",
    nagative: String = "no",
    execution: () -> Unit
) {
    MaterialDialog(this)
        .show {
            //setTheme(R.style.Custom_Dialog)
            positiveButton(R.string.ok)
            lifecycleOwner(this@confirmDialog)
            title(text = title)
            message(text = message)
            negativeButton(text = nagative, click = object : DialogCallback {
                override fun invoke(p1: MaterialDialog) {
                    p1.dismiss()
                }

            })
            positiveButton(text = positive, click = object : DialogCallback {
                override fun invoke(p1: MaterialDialog) {
                    p1.dismiss()
                    execution.invoke()
                }

            })

        }.setActionButtonEnabled(WhichButton.POSITIVE, true)
}

fun Activity.alertMessage(@StringRes message: Int, @StringRes title: Int? = null) {
    val create = Alerter.create(this)
    if (title != null)
        create.setTitle(title)
    create.setText(message)

    create.setIconColorFilter(0) // Optional - Removes white tint
        .show()
}


fun Activity.alertMessage(message: String, title: String? = null) {
    val create = Alerter.create(this)
    if (title != null)
        create.setTitle(title)
    create.setText(message)

    create.setIconColorFilter(0) // Optional - Removes white tint
        .show()
}

fun Activity.alertMessage(message: String, callback: () -> Unit, title: String? = null) {
    val create = Alerter.create(this)
    if (title != null)
        create.setTitle(title)
    create.setText(message)

    create.setIconColorFilter(0) // Optional - Removes white tint
        .setOnHideListener(object : OnHideAlertListener {
            override fun onHide() {
                callback.invoke()
            }

        })
        .show()
}


fun View.gone() {
    visibility = GONE
}

fun View.inVisible() {
    visibility = INVISIBLE
}

fun View.visible() {
    visibility = VISIBLE
}

fun AppCompatActivity.materialDialog(message: String) {

    MaterialDialog(this)
        .show {
            positiveButton(R.string.ok)
            lifecycleOwner(this@materialDialog)
            title(text = "Alert")
            message(text = message)
        }.setActionButtonEnabled(WhichButton.POSITIVE, true)
}

fun View.Shake() {
    animate()
        .translationX(-15f).translationX(15f)
        .setDuration(30)
        .setInterpolator(CycleInterpolator((150 / 30).toFloat()))
        .setDuration(150)
        .start()
}