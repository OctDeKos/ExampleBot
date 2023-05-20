package org.example;
/*
import bot.MySportsBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MySportsBot());
    }
}
*/
import bot.DailyAdviceJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(DailyAdviceJob.class)
                .withIdentity("dailyAdviceJob")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyAdviceTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(9, 0))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
