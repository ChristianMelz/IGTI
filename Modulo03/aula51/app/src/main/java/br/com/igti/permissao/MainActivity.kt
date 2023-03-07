package br.com.igti.permissao

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val thisActivity = this
    private val CALL_PHONE_RESULT_CODE = 111
    private val PHONE_NUMBER = "48984584948"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chamar(view: String) {
        if (ContextCompat.checkSelfPermission(
                thisActivity, android.Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)) {
                createMoreInfoDiaolog()
            } else {
                requestCallPermission()
            }
        } else {
            chamar(PHONE_NUMBER)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when(requestCode){
                CALL_PHONE_RESULT_CODE ->
                    if(grantResults.isEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED){
                        createNeverAskAgainDiaolog()
                    }else{
                        Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show()
                    }
            }
        }

    private fun createNeverAskAgainDiaolog() {
        AlertDialog.Builder(thisActivity).apply {
            setMessage(R.string.never_ask_again_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.go_to_settings){ _, _ -> goToAppDetailsSettings()}
            setNegativeButton(R.string.no){d, _ -> d.dismiss()
            }
        }.show()
    }

    private fun createMoreInfoDiaolog() {
        AlertDialog.Builder(thisActivity).apply {
            setMessage(R.string.more_info_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.yes){ _, _ -> requestCallPermission()}
            setNegativeButton(R.string.no){d, _ -> d.dismiss()
            }
        }.show()
    }

    private fun requestCallPermission() {
        ActivityCompat.requestPermissions(
            thisActivity,
            arrayOf(android.Manifest.permission.CALL_PHONE),
            CALL_PHONE_RESULT_CODE
        )
    }

    private fun goToAppDetailsSettings(){
        val appSettings =  Intent().apply {
            action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", thisActivity.packageName, null)
        }
        startActivity(appSettings)
    }

    private fun makeCall(number: String){
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel $number"))
        startActivity(callIntent)
    }

}

