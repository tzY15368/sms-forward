package com.example.apptest2;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class BotOperator {
    private static TelegramBot bot = new TelegramBot("");;


    public static void sendMsg(long chatID, String msg){
        System.out.println("sending:"+msg);
        SendResponse resp = bot.execute(new SendMessage(chatID,msg));
        System.out.println(resp);
    }

}
