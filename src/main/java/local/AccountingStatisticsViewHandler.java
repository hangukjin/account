package local;

import local.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AccountingStatisticsViewHandler {

    @Autowired
    private AccountingStatisticsRepository accountingStatisticsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCompleted_then_CREATE_OR_UPDATE (@Payload OrderReceived orderReceived) {
        try {
            if (orderReceived.isMe()) {

                String yearMonth = orderReceived.getTimestamp().substring(0, 8);

                AccountingStatistics accountingStatistics;
                if (accountingStatisticsRepository.findById(yearMonth).isPresent()) {
                    accountingStatistics = accountingStatisticsRepository.findById(yearMonth).get();
                    accountingStatistics.setOrderCount(accountingStatistics.getOrderCount() + 1);
                    accountingStatistics.setSalesSum(accountingStatistics.getSalesSum() + orderReceived.getPrice());
                    accountingStatistics.setSalesQty(accountingStatistics.getSalesQty() + orderReceived.getQty());
                    accountingStatisticsRepository.save(accountingStatistics);
                } else {
                    accountingStatistics = new AccountingStatistics();
                    accountingStatistics.setYearmonth(yearMonth);
                    accountingStatistics.setOrderCount((double) 1);
                    accountingStatistics.setSalesSum((double) orderReceived.getPrice());
                    accountingStatistics.setSalesQty((double) orderReceived.getQty());
                    accountingStatisticsRepository.save(accountingStatistics);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderSelected_then_CREATE_OR_UPDATE (@Payload OrderSelected orderSelected) {
        try {
            if (orderSelected.isMe()) {

                String yearMonth = orderSelected.getTimestamp().substring(0, 8);

                AccountingStatistics accountingStatistics;
                if (accountingStatisticsRepository.findById(yearMonth).isPresent()) {
                    accountingStatistics = accountingStatisticsRepository.findById(yearMonth).get();
                    accountingStatistics.setSalesTotalSum(accountingStatistics.getSalesTotalSum() + orderSelected.getPrice());
                    accountingStatisticsRepository.save(accountingStatistics);
                } else {
                    accountingStatistics = new AccountingStatistics();

                    accountingStatistics.setYearmonth(yearMonth);
                    accountingStatistics.setOrderCount((double) 0);
                    accountingStatistics.setSalesSum((double) 0);
                    accountingStatistics.setSalesQty((double) 0);

                    accountingStatistics.setSalesTotalSum(accountingStatistics.getSalesTotalSum() + orderSelected.getPrice());
                    accountingStatisticsRepository.save(accountingStatistics);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}