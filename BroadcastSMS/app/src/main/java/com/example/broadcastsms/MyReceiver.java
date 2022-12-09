package com.example.broadcastsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class MyReceiver extends BroadcastReceiver {
    @RequiresApi
    @Override
    public void onReceive(Context context, Intent intent)
        {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        //recuperar extras que estao dentro da intent
        //A classe bundle recupera todos os extras, independente do tipo
        Bundle extras = intent.getExtras();
        //Extrair somente o protocolo PDU (Protocol Data Unit) dos extras
        //o PDU é utilizado pra envio de mensagens SMS
        Object [] pdus = (Object[]) extras.get("pdus");
        SmsMessage [] sms = new SmsMessage[pdus.length];
        String conteudoSMS = "";

        for(int i =0; i<sms.length; i++)
            {
            sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i], extras.getString("format"));
            //concatenar o conteudo do mensagem SMS
            conteudoSMS += sms[i].getMessageBody();
            }
            Toast.makeText(context, conteudoSMS, Toast.LENGTH_LONG).show();
        }
}