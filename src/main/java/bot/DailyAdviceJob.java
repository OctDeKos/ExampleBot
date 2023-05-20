package bot;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyAdviceJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DailyAdviceBot bot = new DailyAdviceBot();
        bot.sendDailyAdvice();
    }
}
