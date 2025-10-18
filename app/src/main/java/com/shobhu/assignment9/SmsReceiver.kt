package com.shobhu.assignment9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundle: Bundle? = intent.extras
        try {
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<*>
                for (pdu in pdus) {
                    val sms = SmsMessage.createFromPdu(pdu as ByteArray)
                    val sender = sms.displayOriginatingAddress
                    val messageBody = sms.messageBody

                    Toast.makeText(context, "New message from $sender", Toast.LENGTH_SHORT).show()

                    // Show alert in MainActivity if it's open
                    if (context is MainActivity) {
                        context.showAlert(sender, messageBody)
                    } else {
                        // Launch MainActivity if not open
                        val activityIntent = Intent(context, MainActivity::class.java)
                        activityIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        activityIntent.putExtra("sender", sender)
                        activityIntent.putExtra("message", messageBody)
                        context.startActivity(activityIntent)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
